package fcfm.lmad.poi.ChatPoi.domain.interactors.login

import com.google.firebase.auth.FirebaseAuth

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