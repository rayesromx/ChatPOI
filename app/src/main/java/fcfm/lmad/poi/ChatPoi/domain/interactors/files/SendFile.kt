package fcfm.lmad.poi.ChatPoi.domain.interactors.files

import android.net.Uri
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageTask
import com.google.firebase.storage.UploadTask
import fcfm.lmad.poi.ChatPoi.domain.dto.FileMsg
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack

class SendFile:ISendFileUseCase{
    override fun execute(input: FileMsg, listener: IBaseUseCaseCallBack<String>) {
        val storageReference = FirebaseStorage.getInstance().reference.child("Files")

        //val filePath = storageReference.child(input.fileName+"."+input.extension)

        val uploadTask: StorageTask<*>
        uploadTask = storageReference.putFile(input.filePath!!)
        uploadTask.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>>{ task->
            if(!task.isSuccessful){
                listener.onError(task.exception?.message!!)
                task.exception?.let{
                    throw it
                }
            }
            return@Continuation storageReference.downloadUrl
        }).addOnCompleteListener{task->
            if(task.isSuccessful){
                val downloadUrl = task.result
                val url = downloadUrl.toString()
              listener.onSuccess(url)
            }
        }
    }

}