package fcfm.lmad.poi.ChatPoi.infrastructure.repositories

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import fcfm.lmad.poi.ChatPoi.domain.IRepository
import fcfm.lmad.poi.ChatPoi.domain.entities.Message

class MessageRepository: FireBaseRepository<Message>("Messages") {
    override fun getValue(item: DataSnapshot) = item.getValue(Message::class.java)

    override fun save(model: Message, listener: IRepository.IRepositoryListener<String>) {
        if(model.uid.isBlank())
            model.uid = dbReference.push().key!!

        getFirsTableChild(model.uid).setValue(model.getHastMap())
            .addOnCompleteListener {
                if (it.isSuccessful){
                    listener.onSuccess(model.uid)
                }
                else
                    listener.onError(it.exception?.message!!)
            }
    }
}




