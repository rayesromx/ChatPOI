package fcfm.lmad.poi.ChatPoi.domain.interactors.userInteractor

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import fcfm.lmad.poi.ChatPoi.domain.entities.User

class ListUsersInteractor :IListUsersInteractor{
    override fun retrieveAllUsers(listener: IListUsersInteractor.IListUsersCallback) {
        var firebaseUser = FirebaseAuth.getInstance().currentUser!!.uid
        val refUsers = FirebaseDatabase.getInstance().reference.child("Users")
        refUsers.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var users = ArrayList<User>()
                for(item in snapshot.children)
                {
                    var user = item.getValue(User::class.java)
                    if(!user!!.uid.equals(firebaseUser))
                        users.add(user!!)
                }
                listener.onSuccess(users)
            }
            override fun onCancelled(error: DatabaseError) {
                listener.onError(error.message)
            }
        })
    }

    override fun searchUser(searchedUser: String, listener: IListUsersInteractor.IListUsersCallback) {
        var firebaseUser = FirebaseAuth.getInstance().currentUser!!.uid
        val queryUsers = FirebaseDatabase.getInstance().reference.child("Users")
            .orderByChild("search")//.startAt(username).endAt(username + "\uf8ff")

        queryUsers.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var users = ArrayList<User>()
                for(item in snapshot.children)
                {
                    var user = item.getValue(User::class.java)
                    if(!user!!.uid.equals(firebaseUser) && user!!.search.contains(searchedUser) )
                        users.add(user!!)
                }
                listener.onSuccess(users)
            }

            override fun onCancelled(error: DatabaseError) {
                listener.onError(error.message)
            }
        })
    }
}