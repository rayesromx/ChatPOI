package fcfm.lmad.poi.ChatPoi.domain.interactors.userInteractor

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import fcfm.lmad.poi.ChatPoi.domain.entities.User

class OnUserLoggedInInteractor:IOnUserLoggedInInteractor {
    override fun refreshUserData(listener: IOnUserLoggedInInteractor.IOnUserLoggedInCallback){
        var user: User? = null
        var firebaseUser = FirebaseAuth.getInstance().currentUser
        var refUsers = FirebaseDatabase.getInstance().reference.child("Users").child(firebaseUser!!.uid)

        refUsers!!.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                user = snapshot.getValue(User::class.java)
                listener.onLoggedInSuccess(user)
            }

            override fun onCancelled(error: DatabaseError) {
                listener.onLoggedInError(error.message)
            }
        })
    }
}