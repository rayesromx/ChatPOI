package fcfm.lmad.poi.ChatPoi.domain.interactors.loginInteractor

import com.google.firebase.auth.FirebaseAuth

class LogoutInteractor : ILogoutInteractor {
    override fun logOut() {
        FirebaseAuth.getInstance().signOut()
    }
}