package fcfm.lmad.poi.ChatPoi.domain.interactors.chat

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import fcfm.lmad.poi.ChatPoi.domain.entities.Message

class RetrieveChatConversationInteractor:IRetrieveChatConversationInteractor {
    override fun getChatConversation(sender: String, receiver: String,listener: IRetrieveChatConversationInteractor.IRetrieveChatConversationCallback) {
        var chatList = ArrayList<Message>()
        val reference = FirebaseDatabase.getInstance().reference.child("Chats")

        reference.addListenerForSingleValueEvent(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                chatList.clear()
                for (item in snapshot.children){
                    val chat = item.getValue(Message::class.java)
                    if((chat?.sender == sender && chat?.receiver == receiver) || (chat?.receiver == sender && chat?.sender== receiver )){
                        chatList.add(chat)
                    }
                }
                listener.onSuccess(chatList!!)
            }

            override fun onCancelled(error: DatabaseError) {
                listener.onError(error.message)
            }
        })
    }
}
