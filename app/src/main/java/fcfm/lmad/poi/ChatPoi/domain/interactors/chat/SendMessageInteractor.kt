package fcfm.lmad.poi.ChatPoi.domain.interactors.chat

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import fcfm.lmad.poi.ChatPoi.domain.entities.Message

class SendMessageInteractor: ISendMessageInteractor {
    override fun sendMessage(
        message: Message,
        listener: ISendMessageInteractor.ISendMessageCallback
    ) {
        val reference = FirebaseDatabase.getInstance().reference
        val messageKey = reference.push().key
        val messageHashMap = HashMap<String, Any?>()
        messageHashMap["sender"] = message.sender
        messageHashMap["receiver"] = message.receiver
        messageHashMap["message"] = message.message
        messageHashMap["id"] =messageKey
        messageHashMap["image_url"] = ""
        messageHashMap["is_seen"] = false

        reference.child("Chats").child(messageKey!!)
            .setValue(messageHashMap).addOnCompleteListener{
                if(it.isSuccessful){
                    val firebaseUser = FirebaseAuth.getInstance().currentUser
                    val chatListReference = FirebaseDatabase.getInstance()
                        .reference.child("ChatList")
                    chatListReference.child("id").setValue(firebaseUser!!.uid)
                    val reference =  FirebaseDatabase.getInstance()
                        .reference.child("Users").child(firebaseUser!!.uid)

                }
            }


    }
}