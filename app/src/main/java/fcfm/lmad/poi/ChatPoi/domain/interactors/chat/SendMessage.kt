package fcfm.lmad.poi.ChatPoi.domain.interactors.chat

import android.net.Uri
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageTask
import com.google.firebase.storage.UploadTask
import fcfm.lmad.poi.ChatPoi.domain.entities.Message
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack

class SendMessage: ISendMessageUseCase {
    override fun execute(input: Message, listener: IBaseUseCaseCallBack<Message>) {
        val reference = FirebaseDatabase.getInstance().reference
        input.id = reference.push().key!!
        input.sender=  FirebaseAuth.getInstance().currentUser!!.uid

        reference.child("Chats").child(input.id)
            .setValue(input.getHastMap()).addOnCompleteListener {
                if (it.isSuccessful) {
                    val chatListReference = FirebaseDatabase.getInstance()
                            .reference.child("ChatList")
                            .child(input.sender)
                            .child(input.receiver)

                    chatListReference.addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            if (!snapshot.exists())
                                chatListReference.child("id").setValue(input.receiver)

                            val chatListReceiverReference = FirebaseDatabase.getInstance()
                                    .reference.child("ChatList")
                                    .child(input.receiver)
                                    .child(input.sender)

                            chatListReceiverReference.child("id").setValue(input.sender)
                        }
                        override fun onCancelled(error: DatabaseError) {
                            listener.onError(error.message)
                        }
                    })
                }
            }
    }
}