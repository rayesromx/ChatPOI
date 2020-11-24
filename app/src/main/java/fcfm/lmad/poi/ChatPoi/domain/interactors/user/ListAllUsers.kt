package fcfm.lmad.poi.ChatPoi.domain.interactors.user

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack

class ListAllUsers :IListAllUsersUseCase{
    override fun execute(listener: IBaseUseCaseCallBack<List<User>>) {
        val firebaseUser = FirebaseAuth.getInstance().currentUser!!.uid
        val refUsers = FirebaseDatabase.getInstance().reference.child("Users")
        refUsers.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val users = ArrayList<User>()
                for(item in snapshot.children)
                {
                    val user = item.getValue(User::class.java)
                    if(user!!.uid != firebaseUser)
                        users.add(user)
                }
                listener.onSuccess(users)
            }
            override fun onCancelled(error: DatabaseError) {
                listener.onError(error.message)
            }
        })
    }
}