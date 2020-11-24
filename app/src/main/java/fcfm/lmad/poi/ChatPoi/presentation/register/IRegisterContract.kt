package fcfm.lmad.poi.ChatPoi.presentation.register

import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.presentation.shared.IBasePresenter
import fcfm.lmad.poi.ChatPoi.presentation.shared.IBaseView

interface IRegisterContract {
    interface IRegisterView: IBaseView {
        fun navigateToMain()
        fun signUp()
    }
    interface IRegisterPresenter: IBasePresenter<IRegisterView> {
        fun checkEmptyField(field:String):Boolean
        fun checkValidEmail(email:String):Boolean
        fun checkPasswordsMatch(pwd1:String, pwd2:String):Boolean
        fun signUp(user: User, password:String)
    }
}