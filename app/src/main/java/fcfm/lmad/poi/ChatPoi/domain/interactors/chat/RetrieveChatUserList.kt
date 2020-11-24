package fcfm.lmad.poi.ChatPoi.domain.interactors.chat

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import fcfm.lmad.poi.ChatPoi.domain.entities.ChatList
import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack
import fcfm.lmad.poi.ChatPoi.domain.interactors.user.ListAllUsers

class RetrieveChatUserList(
    private val listAllUsers: ListAllUsers
): IRetrieveChatUserListUseCase {

    lateinit var userChatList: ArrayList<ChatList>
    lateinit var userList: ArrayList<User>

    override fun execute(listener: IBaseUseCaseCallBack<List<User>>) {
        listAllUsers.execute(object:IBaseUseCaseCallBack<List<User>>{
            override fun onSuccess(data: List<User>?) {
                val currentUser = FirebaseAuth.getInstance().currentUser
                val ref = FirebaseDatabase.getInstance().reference.child("ChatList").child(currentUser!!.uid)
                ref.addValueEventListener(object:ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        userChatList = ArrayList()
                        for(item in snapshot.children){
                            val chat  = item.getValue(ChatList::class.java)
                            userChatList.add(chat!!)
                        }

                        userList = ArrayList()
                        for(user in data!!){
                            for(chat in userChatList) {
                                if(user.uid == chat.id)
                                    userList.add(user)
                            }
                        }

                        listener.onSuccess(userList)
                    }

                    override fun onCancelled(error: DatabaseError) {
                        listener.onError(error.message)
                    }
                })
            }

            override fun onError(error: String) {
                listener.onError(error)
            }
        })
    }
}