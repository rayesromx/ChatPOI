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

class SendMessageInteractor: ISendMessageInteractor {
    override fun sendImage(
        msg:Message,
        fileUri: Uri?,
        listener: ISendMessageInteractor.ISendMessageCallback
    ) {
        val reference = FirebaseDatabase.getInstance().reference
        msg.id = reference.push().key!!
        msg.sender=  FirebaseAuth.getInstance().currentUser!!.uid

        val storageReference = FirebaseStorage.getInstance().reference.child("ChatImages")
        val dbReference = FirebaseDatabase.getInstance().reference
        val filePath = storageReference.child(msg.id+".jpg")

        val uploadTask: StorageTask<*>
        uploadTask = filePath.putFile(fileUri!!)
        uploadTask.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>>{task->
            if(!task.isSuccessful){
                task.exception?.let{
                    throw it
                }
            }
            return@Continuation filePath.downloadUrl
        }).addOnCompleteListener{task->
            if(task.isSuccessful){
                val downloadUrl = task.result
                val url = downloadUrl.toString()
                msg.message = "Envio una imagen"
                msg.image_url = url

                dbReference.child("Chats").child(msg.id).setValue(msg.getHastMap())
            }
        }
    }

    override fun sendMessage(
        msg:Message,
        listener: ISendMessageInteractor.ISendMessageCallback
    ) {
        val reference = FirebaseDatabase.getInstance().reference
        msg.id = reference.push().key!!
        msg.sender=  FirebaseAuth.getInstance().currentUser!!.uid

        reference.child("Chats").child(msg.id!!)
            .setValue(msg.getHastMap()).addOnCompleteListener {
                if (it.isSuccessful) {
                    val chatListReference = FirebaseDatabase.getInstance()
                        .reference.child("ChatList")
                        .child(msg.sender)
                        .child(msg.receiver)

                    chatListReference.addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            if (!snapshot.exists())
                                chatListReference.child("id").setValue(msg.receiver)

                            val chatListReceiverReference = FirebaseDatabase.getInstance()
                                .reference.child("ChatList")
                                .child(msg.receiver)
                                .child(msg.sender)

                            chatListReceiverReference.child("id").setValue(msg.sender)
                        }
                        override fun onCancelled(error: DatabaseError) {
                            listener.onError(error.message)
                        }
                    })
                }
        }
    }
}