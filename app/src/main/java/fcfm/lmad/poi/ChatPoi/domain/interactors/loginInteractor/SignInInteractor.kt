package fcfm.lmad.poi.ChatPoi.domain.interactors.loginInteractor

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class SignInInteractor:ISignInInteractor {
    override fun signIn(
        email: String,
        password: String,
        listener: ISignInInteractor.ISignInCallback
    ) {

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
            .addOnCompleteListener{
                if(it.isSuccessful)
                    listener.onSignInSuccess()
                else
                    listener.onSignInError(it.exception?.message!!)
            }
    }
}