package fcfm.lmad.poi.ChatPoi.domain.interactors.chat

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import fcfm.lmad.poi.ChatPoi.domain.dto.RetrieveChat
import fcfm.lmad.poi.ChatPoi.domain.entities.Message
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack

class RetrieveChatConversation:IRetrieveChatConversationUseCase {
    override fun execute(input: RetrieveChat, listener: IBaseUseCaseCallBack<List<Message>>) {
        val messages = ArrayList<Message>()
        val reference = FirebaseDatabase.getInstance().reference.child("Chats")

        reference.addListenerForSingleValueEvent(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                messages.clear()
                for (item in snapshot.children){
                    val message = item.getValue(Message::class.java)!!
                    if((message.sender == input.sender && message.receiver == input.receiver) ||
                            (message.receiver == input.sender && message.sender== input.receiver)){
                        messages.add(message)
                    }
                }
                listener.onSuccess(messages)
            }

            override fun onCancelled(error: DatabaseError) {
                listener.onError(error.message)
            }
        })
    }
}
