package fcfm.lmad.poi.ChatPoi.domain.interactors.chat

import android.renderscript.Sampler
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import fcfm.lmad.poi.ChatPoi.domain.entities.ChatList
import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.domain.interactors.user.IListUsersInteractor
import fcfm.lmad.poi.ChatPoi.domain.interactors.user.ListUsersInteractor

class RetrieveChatUserListInteractor: IRetrieveChatUserListInteractor {

    lateinit var userChatList: ArrayList<ChatList>
    lateinit var userList: ArrayList<User>

    override fun getChatuserList(
        listener: IRetrieveChatUserListInteractor.IRetrieveChatUserListICallback
    ) {

        var interactor = ListUsersInteractor()
        interactor.retrieveAllUsers(object: IListUsersInteractor.IListUsersCallback{
            override fun onSuccess(data: List<User>?) {

                val currentUser = FirebaseAuth.getInstance().currentUser
                var ref = FirebaseDatabase.getInstance().reference.child("ChatList").child(currentUser!!.uid)
                ref!!.addValueEventListener(object:ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        userChatList = ArrayList()
                        for(item in snapshot.children){
                            val chat  = item.getValue(ChatList::class.java)
                            userChatList.add(chat!!)
                        }

                        userList = ArrayList()
                        for(user in data!!){
                            for(chat in userChatList!!)
                            {
                                if(user!!.uid == chat.id)
                                    userList.add(user!!)
                            }
                        }

                        listener.onSuccess(userList)
                    }

                    override fun onCancelled(error: DatabaseError) {
                        listener.onError(error.message)
                    }
                })
            }

            override fun onError(errorMessage: String) {
                listener.onError(errorMessage)
            }

        })


    }
}