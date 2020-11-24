package fcfm.lmad.poi.ChatPoi.domain.interactors.user

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack

class SearchUserByUsername:ISearchUserByUsernameUseCase {
    override fun execute(input: String, listener: IBaseUseCaseCallBack<List<User>>) {
        val firebaseUser = FirebaseAuth.getInstance().currentUser!!.uid
        val queryUsers = FirebaseDatabase.getInstance().reference.child("Users")
                .orderByChild("search")//.startAt(username).endAt(username + "\uf8ff")

        queryUsers.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val users = ArrayList<User>()
                for(item in snapshot.children)
                {
                    val user = item.getValue(User::class.java)
                    if(user!!.uid != firebaseUser && user.search.contains(input) )
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