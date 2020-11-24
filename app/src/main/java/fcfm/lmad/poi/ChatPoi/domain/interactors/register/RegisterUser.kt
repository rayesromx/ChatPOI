package fcfm.lmad.poi.ChatPoi.domain.interactors.register

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.FirebaseDatabase
import fcfm.lmad.poi.ChatPoi.domain.dto.RegisterData
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack

class RegisterUser:IRegisterUserUseCase {
    override fun execute(input: RegisterData, listener: IBaseUseCaseCallBack<Boolean>) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(input.user.email,input.password).addOnCompleteListener{
            if(it.isSuccessful){
                val firebaseUserID = FirebaseAuth.getInstance().currentUser?.uid
                val refUser = FirebaseDatabase.getInstance().reference.child("Users").child(firebaseUserID!!)

                input.user.uid = firebaseUserID
                input.user.profile_img = "https://firebasestorage.googleapis.com/v0/b/chatlmad.appspot.com/o/blank-profile-picture.png?alt=media&token=0c8c9c63-ee4e-4dca-ab66-2ca978816e4d"
                input.user.status = "offline"

                refUser.updateChildren(input.user.getHastMap()).addOnCompleteListener{task->
                    if(task.isSuccessful){
                        val profileUpdates = UserProfileChangeRequest.Builder()
                                .setDisplayName(input.user.username).build()
                        FirebaseAuth.getInstance().currentUser?.updateProfile(profileUpdates)?.addOnCompleteListener{itUpdate ->
                            if(itUpdate.isSuccessful){
                                listener.onSuccess(true)
                            }else{
                                listener.onSuccess(false)
                                listener.onError(itUpdate.exception?.message!!)
                            }
                        }
                    }else{
                        listener.onError(task.exception?.message.toString())
                    }
                }
            }else{
                listener.onError(it.exception?.message.toString())
            }
        }
    }
}