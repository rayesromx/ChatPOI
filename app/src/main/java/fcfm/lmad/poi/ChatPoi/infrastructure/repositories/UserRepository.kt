package fcfm.lmad.poi.ChatPoi.infrastructure.repositories

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import fcfm.lmad.poi.ChatPoi.domain.IRepository
import fcfm.lmad.poi.ChatPoi.domain.entities.User

class UserRepository: FireBaseRepository<User>("Users") {
    override fun getValue(item: DataSnapshot) = item.getValue(User::class.java)

    fun getCurrentLoggedUser(listener: IRepository.IRepositoryListener<User?>){
        val firebaseUser = FirebaseAuth.getInstance().currentUser
        getById(firebaseUser!!.uid,object: IRepository.IRepositoryListener<User?>{
            override fun onSuccess(data: User?) {
                listener.onSuccess(data)
            }
            override fun onError(error: String) {
                listener.onError(error)
            }
        })
    }
}