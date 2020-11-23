package fcfm.lmad.poi.ChatPoi.presentation.register.presenter

import androidx.core.util.PatternsCompat
import fcfm.lmad.poi.ChatPoi.domain.interactors.register.IRegisterInteractor.IRegisterCallback
import fcfm.lmad.poi.ChatPoi.domain.interactors.register.RegisterInteractor
import fcfm.lmad.poi.ChatPoi.presentation.register.IRegisterContract

class RegisterPresenter(
    registerInteractor: RegisterInteractor
):IRegisterContract.IRegisterPresenter {

    var view:IRegisterContract.IRegisterView? = null
    var registerInteractor:RegisterInteractor? = null

    init {
        this.registerInteractor = registerInteractor
    }

    override fun checkEmptyField(field: String): Boolean = field.isEmpty()

    override fun checkValidEmail(email: String):Boolean
            = PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()

    override fun checkPasswordsMatch(pwd1: String, pwd2: String): Boolean = pwd1 == pwd2

    override fun signUp(username: String, email: String, password: String,carrera:String) {
        view?.showProgressBar()
        registerInteractor?.signUp(username, email,password,carrera,object: IRegisterCallback{
            override fun onRegisterSucces() {
                view?.navigateToMain()
                view?.hideProgressBar()
            }

            override fun onRegisterFailure(errorMsg: String) {
                view?.showError(errorMsg)
                view?.hideProgressBar()
            }

        })
    }

    override fun attachView(view: IRegisterContract.IRegisterView) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun isViewAttached(): Boolean = view != null
}