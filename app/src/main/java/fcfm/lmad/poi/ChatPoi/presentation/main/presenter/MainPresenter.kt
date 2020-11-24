package fcfm.lmad.poi.ChatPoi.presentation.main.presenter

import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack
import fcfm.lmad.poi.ChatPoi.domain.interactors.login.GetLoggedUserData
import fcfm.lmad.poi.ChatPoi.domain.interactors.login.LogOut
import fcfm.lmad.poi.ChatPoi.presentation.main.IMainContract
import fcfm.lmad.poi.ChatPoi.presentation.shared.presenter.BasePresenter

class MainPresenter(
        var logOut: LogOut,
        var getLoggedUserData: GetLoggedUserData
): BasePresenter<IMainContract.IMainView>(), IMainContract.IMainPresenter {

    override fun logOut() {
        logOut.execute(object: IBaseUseCaseCallBack<Boolean>{
            override fun onSuccess(data: Boolean?) {
                view!!.logOut()
            }
            override fun onError(error: String) {
                view!!.showError(error)
            }
        })
    }

    override fun refreshUserData() {
        getLoggedUserData.execute(object: IBaseUseCaseCallBack<User> {
            override fun onSuccess(data: User?) {
                view!!.refreshUserData(data)
            }
            override fun onError(error: String) {
                view!!.showError(error)
            }
        })
    }
}