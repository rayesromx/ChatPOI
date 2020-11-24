package fcfm.lmad.poi.ChatPoi.presentation.main

import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.presentation.shared.IBasePresenter

interface IMainContract {
    interface IMainView {
        fun logOut()
        fun refreshUserData(user:User?)
        fun showError(errorMsg:String)
        fun setup()
    }

    interface IMainPresenter: IBasePresenter<IMainView>{
        fun logOut()
        fun refreshUserData()
        fun setup()
    }
}