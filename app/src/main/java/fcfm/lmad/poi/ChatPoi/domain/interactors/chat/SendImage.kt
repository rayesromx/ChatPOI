package fcfm.lmad.poi.ChatPoi.domain.interactors.chat

import android.net.Uri
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageTask
import com.google.firebase.storage.UploadTask
import fcfm.lmad.poi.ChatPoi.domain.dto.ImageMsg
import fcfm.lmad.poi.ChatPoi.domain.entities.Message
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack

class SendImage: ISendImageUseCase {
    override fun execute(input: ImageMsg, listener: IBaseUseCaseCallBack<Message>) {
        val reference = FirebaseDatabase.getInstance().reference
        input.message.id = reference.push().key!!
        input.message.sender=  FirebaseAuth.getInstance().currentUser!!.uid

        val storageReference = FirebaseStorage.getInstance().reference.child("ChatImages")
        val dbReference = FirebaseDatabase.getInstance().reference
        val filePath = storageReference.child(input.message.id+".jpg")

        val uploadTask: StorageTask<*>
        uploadTask = filePath.putFile(input.filePath)
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
                input.message.message = "Envio una imagen"
                input.message.image_url = url

                dbReference.child("Chats").child(input.message.id).setValue(input.message.getHastMap())
            }
        }
    }
}