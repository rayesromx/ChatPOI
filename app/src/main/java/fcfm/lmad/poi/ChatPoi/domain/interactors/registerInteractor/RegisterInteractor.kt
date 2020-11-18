package fcfm.lmad.poi.ChatPoi.domain.interactors.registerInteractor

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

class RegisterInteractor:IRegisterInteractor {
    override fun signUp(
        userName: String,
        email: String,
        password: String,
        listener: IRegisterInteractor.IRegisterCallback
    ) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener{
            if(it.isSuccessful){
                val profileUpdates = UserProfileChangeRequest.Builder()
                    .setDisplayName(userName)
                    .build()

                FirebaseAuth.getInstance().currentUser?.updateProfile(profileUpdates)?.addOnCompleteListener{itUpdate ->
                    if(itUpdate.isSuccessful){
                        listener.onRegisterSucces()
                    }
                }

            }else{
                listener.onRegisterFailure(it.exception?.message.toString())
            }
        }
    }
}