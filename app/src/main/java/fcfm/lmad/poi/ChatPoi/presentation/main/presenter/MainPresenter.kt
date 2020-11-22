package fcfm.lmad.poi.ChatPoi.presentation.main.presenter

import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.domain.interactors.loginInteractor.LogoutInteractor
import fcfm.lmad.poi.ChatPoi.domain.interactors.userInteractor.IOnUserLoggedInInteractor
import fcfm.lmad.poi.ChatPoi.domain.interactors.userInteractor.OnUserLoggedInInteractor
import fcfm.lmad.poi.ChatPoi.presentation.main.IMainContract

class MainPresenter(
    var logoutInteractor: LogoutInteractor,
    var onUserLoggedInInteractor: OnUserLoggedInInteractor
):IMainContract.IMainPresenter {

    var view: IMainContract.IMainView? = null

    override fun logOut() {
        logoutInteractor.logOut()
    }

    override fun refreshUserData() {
        onUserLoggedInInteractor.refreshUserData(
            object : IOnUserLoggedInInteractor.IOnUserLoggedInCallback {
                override fun onLoggedInSuccess(user: User?) {
                    view!!.refreshUserData(user)
                }
                override fun onLoggedInError(errorMsg: String) {
                    view!!.showError(errorMsg)
                }
            })
    }

    override fun attachView(view: IMainContract.IMainView) {
        this.view = view
    }
    override fun detachView() {
        view = null
    }
    override fun isViewAttached(): Boolean = view != null
}