package fcfm.lmad.poi.ChatPoi.domain.interactors.login

import com.google.firebase.auth.FirebaseAuth
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack

class CheckLoggedIn : ICheckLoggedInUseCase {
    override fun execute(listener: IBaseUseCaseCallBack<Boolean>) {
        listener.onSuccess(FirebaseAuth.getInstance().currentUser != null)
    }
}