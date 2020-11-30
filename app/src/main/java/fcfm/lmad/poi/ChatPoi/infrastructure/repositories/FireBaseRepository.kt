package fcfm.lmad.poi.ChatPoi.infrastructure.repositories

import com.google.firebase.database.*
import fcfm.lmad.poi.ChatPoi.domain.IRepository
import fcfm.lmad.poi.ChatPoi.domain.entities.BaseEntity

abstract class FireBaseRepository<T>(
        protected val fireBaseTableName:String
): IRepository<T> where T: BaseEntity {
    protected var dbReference: DatabaseReference = FirebaseDatabase.getInstance().reference

    abstract fun getValue(item:DataSnapshot): T?
    fun canModelBeRetrieved(model: T) : Boolean = true
    protected fun getTableRef() = dbReference.child(fireBaseTableName)
    protected final fun getFirsTableChild(id:String) = getTableRef().child(id)

    override fun getAll(listener: IRepository.IRepositoryListener<List<T>>) {
        getTableRef().addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val models = ArrayList<T>()
                for (model in snapshot.children){
                    val m = getValue(model)!!
                    models.add(m)
                }
                listener.onSuccess(models)
            }
            override fun onCancelled(error: DatabaseError) {
                listener.onError(error.message)
            }
        })
    }

    override fun getById(id:String, listener: IRepository.IRepositoryListener<T>) {
        getFirsTableChild(id).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val model = getValue(snapshot)
                listener.onSuccess(model!!)
            }
            override fun onCancelled(error: DatabaseError) {
                listener.onError(error.message)
            }
        })
    }

    override fun getByCustomParam(paramKey: String, paramValue: String, listener: IRepository.IRepositoryListener<List<T>>) {
        getByCustomParam(getTableRef(),paramKey,paramValue,listener)
    }

    protected fun getByCustomParam(dbRef:DatabaseReference, paramKey: String, paramValue: String, listener: IRepository.IRepositoryListener<List<T>>) {
        dbRef.orderByChild(paramKey).equalTo(paramValue).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val models = ArrayList<T>()
                if(snapshot.exists()){
                    for (model in snapshot.children){
                        val m = getValue(model)
                        if(canModelBeRetrieved(m!!))
                            models.add(m)
                    }
                }
                listener.onSuccess(models)
            }
            override fun onCancelled(error: DatabaseError) {
                listener.onError(error.message)
            }
        })
    }

    override fun save(model: T, listener: IRepository.IRepositoryListener<String>) {
        if(model.uid.isBlank())
            model.uid = dbReference.push().key!!

        saveData( getFirsTableChild(model.uid),model,listener)
    }

    protected fun save(dbRef: DatabaseReference, model: T, listener: IRepository.IRepositoryListener<String>) {
        saveData(dbRef,model,listener)
    }

    private fun saveData(dbRef: DatabaseReference, model: T, listener: IRepository.IRepositoryListener<String>) {
        dbRef.setValue(model.getHastMap())
            .addOnCompleteListener {
                if (it.isSuccessful)
                    listener.onSuccess(model.uid)
                else
                    listener.onError(it.exception?.message!!)
            }
    }
}