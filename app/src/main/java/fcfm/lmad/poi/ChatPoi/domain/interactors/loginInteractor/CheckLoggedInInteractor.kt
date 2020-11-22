package fcfm.lmad.poi.ChatPoi.domain.interactors.loginInteractor

import com.google.firebase.auth.FirebaseAuth

class CheckLoggedInInteractor : ICheckLoggedInInteractor {
    override fun isUserAlreadyLoggedIn(): Boolean =
        FirebaseAuth.getInstance().currentUser != null
}