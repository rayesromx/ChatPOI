package fcfm.lmad.poi.ChatPoi.domain.interactors.chat

import android.net.Uri
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageTask
import com.google.firebase.storage.UploadTask
import fcfm.lmad.poi.ChatPoi.data.CustomSessionState
import fcfm.lmad.poi.ChatPoi.domain.IRepository
import fcfm.lmad.poi.ChatPoi.domain.dto.ImageMsg
import fcfm.lmad.poi.ChatPoi.domain.entities.Message
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack
import fcfm.lmad.poi.ChatPoi.infrastructure.repositories.MessageRepository

class SendImage(
        private val messageRepository: MessageRepository
): ISendImageUseCase {
    override fun execute(input: ImageMsg, listener: IBaseUseCaseCallBack<Message>) {

        input.message.sender = CustomSessionState.loggedUser.uid
        val storageReference = FirebaseStorage.getInstance().reference.child("ChatImages")
        val filePath = storageReference.child(input.message.uid+".jpg")

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

                messageRepository.save(input.message, object: IRepository.IRepositoryListener<String>{
                    override fun onSuccess(data: String) {
                        listener.onSuccess(input.message)
                    }
                    override fun onError(error: String) {
                        listener.onError(error)
                    }
                })
            }
        }


    }
}