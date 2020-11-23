package fcfm.lmad.poi.ChatPoi.domain.interactors.login

import com.google.firebase.auth.FirebaseAuth

class CheckLoggedInInteractor : ICheckLoggedInInteractor {
    override fun isUserAlreadyLoggedIn(): Boolean =
        FirebaseAuth.getInstance().currentUser != null
}