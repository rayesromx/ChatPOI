package fcfm.lmad.poi.ChatPoi.domain.interactors.user

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import fcfm.lmad.poi.ChatPoi.domain.entities.User

class RetrieveUserDataInteractor:IRetrieveUserDataInteractor {
    override fun retrieveUser(
        userId: String,
        listener: IRetrieveUserDataInteractor.IRetrieveUserDataInteractorCallback
    ) {
        val reference = FirebaseDatabase.getInstance().reference
            .child("Users").child(userId)
        reference.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(User::class.java)
                listener.onSuccess(user!!)
            }

            override fun onCancelled(error: DatabaseError) {
                listener.onError(error.message)
            }

        })
    }
}