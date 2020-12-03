package fcfm.lmad.poi.ChatPoi.presentation.main.presenter

import fcfm.lmad.poi.ChatPoi.data.CustomSessionState
import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack
import fcfm.lmad.poi.ChatPoi.domain.interactors.login.GetLoggedUserData
import fcfm.lmad.poi.ChatPoi.domain.interactors.login.LogOut
import fcfm.lmad.poi.ChatPoi.domain.interactors.user.UpdateUser
import fcfm.lmad.poi.ChatPoi.presentation.main.IMainContract
import fcfm.lmad.poi.ChatPoi.presentation.shared.presenter.BasePresenter

class MainPresenter(
        var logOut: LogOut,
        var getLoggedUserData: GetLoggedUserData,
        var updateUser: UpdateUser
): BasePresenter<IMainContract.IMainView>(), IMainContract.IMainPresenter {

    override fun logOut() {
        logOut.execute(object: IBaseUseCaseCallBack<Boolean>{
            override fun onSuccess(data: Boolean?) {
                if(!isViewAttached()) return
                CustomSessionState.loggedUser.status = "offline"
                updateUser.execute(CustomSessionState.loggedUser, object: IBaseUseCaseCallBack<User>{
                    override fun onSuccess(data: User?) {
                        view!!.logOut()
                    }
                    override fun onError(error: String) {
                        view!!.showError(error)
                    }
                })
            }
            override fun onError(error: String) {
                if(!isViewAttached()) return
                view!!.showError(error)
            }
        })
    }

    override fun refreshUserData() {
        getLoggedUserData.execute(object: IBaseUseCaseCallBack<User> {
            override fun onSuccess(data: User?) {
                if(!isViewAttached()) return
                view!!.refreshUserData(data)

            }
            override fun onError(error: String) {
                if(!isViewAttached()) return
                view!!.showError(error)
            }
        })
    }
}