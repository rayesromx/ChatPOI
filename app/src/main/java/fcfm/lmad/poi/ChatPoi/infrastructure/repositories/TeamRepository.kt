package fcfm.lmad.poi.ChatPoi.infrastructure.repositories

import com.google.firebase.database.DataSnapshot
import fcfm.lmad.poi.ChatPoi.domain.IRepository
import fcfm.lmad.poi.ChatPoi.domain.entities.Team

class TeamRepository: FireBaseRepository<Team>("Teams") {
    override fun getValue(item: DataSnapshot) = item.getValue(Team::class.java)
    override fun save(model: Team, listener: IRepository.IRepositoryListener<String>) {
        if(model.parent.isBlank())
            super.save(model, listener)
        else{
            model.uid = dbReference.push().key!!
            getFirsTableChild(model.parent)
                    .child(model.uid).setValue(model.getHastMap())
                    .addOnCompleteListener {
                        if (!it.isSuccessful)
                            listener.onError(it.exception?.message!!)
                    }
        }
    }

}
