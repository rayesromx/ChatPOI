package fcfm.lmad.poi.ChatPoi.presentation.main.presenter

import fcfm.lmad.poi.ChatPoi.domain.entities.Team
import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.domain.interactors.login.LogoutInteractor
import fcfm.lmad.poi.ChatPoi.domain.interactors.teams.ITeamsSetupInteractor
import fcfm.lmad.poi.ChatPoi.domain.interactors.user.IOnUserLoggedInInteractor
import fcfm.lmad.poi.ChatPoi.domain.interactors.user.OnUserLoggedInInteractor
import fcfm.lmad.poi.ChatPoi.presentation.main.IMainContract

class MainPresenter(
    var logoutInteractor: LogoutInteractor,
    var onUserLoggedInInteractor: OnUserLoggedInInteractor,
    var teamsSetupInteractor: ITeamsSetupInteractor
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

    override fun setup() {
        teamsSetupInteractor.setupTeams(object:ITeamsSetupInteractor.ITeamsSetupInteractorCallBack{
            override fun onSuccess(data: List<Team>?) {
            }

            override fun onError(errorMessage: String) {
                view?.showError(errorMessage)
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