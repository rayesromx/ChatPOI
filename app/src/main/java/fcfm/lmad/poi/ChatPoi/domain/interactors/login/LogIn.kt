package fcfm.lmad.poi.ChatPoi.domain.interactors.login

import com.google.firebase.auth.FirebaseAuth
import fcfm.lmad.poi.ChatPoi.data.CustomSessionState
import fcfm.lmad.poi.ChatPoi.domain.dto.LoginData
import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack
import fcfm.lmad.poi.ChatPoi.domain.interactors.user.UpdateUser
import fcfm.lmad.poi.ChatPoi.infrastructure.repositories.UserRepository

class LogIn:ILogInUseCase {
    override fun execute(input: LoginData, listener: IBaseUseCaseCallBack<Boolean>) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(input.email,input.password)
            .addOnCompleteListener{
                if(it.isSuccessful) {

                    listener.onSuccess(true)
                }
                else {
                    listener.onSuccess(false)
                    listener.onError(it.exception?.message!!)
                }
            }
    }
}