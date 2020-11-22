package fcfm.lmad.poi.ChatPoi.presentation.login.presenter

import com.fcfm.poi.plantilla.presentation.login.ILoginContract
import fcfm.lmad.poi.ChatPoi.domain.interactors.loginInteractor.ICheckLoggedInInteractor
import fcfm.lmad.poi.ChatPoi.domain.interactors.loginInteractor.ISignInInteractor

class LoginPresenter(
    val signInInteractor: ISignInInteractor,
    val checkLoggedInInteractor: ICheckLoggedInInteractor
):ILoginContract.ILoginPresenter{

    var view:ILoginContract.ILoginView? = null

    override fun attachView(loginView: ILoginContract.ILoginView) {
        this.view = loginView
    }
    override fun detachView() {
        view = null
    }
    override fun isViewAttached(): Boolean = view != null

    override fun signInUserWithEmailAndPassword(email: String, password: String) {
        view?.showProgressBar()
        signInInteractor.signIn(email,password,object: ISignInInteractor.ISignInCallback{
            override fun onSignInSuccess() {
                if(isViewAttached()){
                    view?.hideProgressBar()
                    view?.navigateToMain()
                }
            }

            override fun onSignInError(errorMsg: String) {
                if(isViewAttached()){
                    view?.hideProgressBar()
                    view?.showError(errorMsg)
                }
            }

        } )

    }

    override fun checkEmptyFields(email: String, password: String) = email.isEmpty() || password.isEmpty()

    override fun isUserAlreadyLoggedIn(): Boolean = checkLoggedInInteractor.isUserAlreadyLoggedIn()

}