package fcfm.lmad.poi.ChatPoi.domain.interactors.login

import com.google.firebase.auth.FirebaseAuth
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack

class LogOut : ILogOutUseCase {
    override fun execute(listener: IBaseUseCaseCallBack<Boolean>) {
        FirebaseAuth.getInstance().signOut()
    }
}