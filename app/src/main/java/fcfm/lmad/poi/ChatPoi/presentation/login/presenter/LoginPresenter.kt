package fcfm.lmad.poi.ChatPoi.presentation.login.presenter

import com.fcfm.poi.plantilla.presentation.login.ILoginContract
import fcfm.lmad.poi.ChatPoi.domain.dto.LoginData
import fcfm.lmad.poi.ChatPoi.domain.entities.TeamContainer
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack
import fcfm.lmad.poi.ChatPoi.domain.interactors.login.ICheckLoggedInUseCase
import fcfm.lmad.poi.ChatPoi.domain.interactors.login.ILogInUseCase
import fcfm.lmad.poi.ChatPoi.domain.interactors.teams.ISetupDefaultTeamsUseCase
import fcfm.lmad.poi.ChatPoi.presentation.shared.presenter.BasePresenter

class LoginPresenter(
        private val logIn: ILogInUseCase,
        private val checkLoggedIn: ICheckLoggedInUseCase,
        private var setupDefaultTeams: ISetupDefaultTeamsUseCase
): BasePresenter<ILoginContract.IView>(), ILoginContract.IPresenter{

    override fun isViewAttached(): Boolean = view != null

    override fun signInUserWithEmailAndPassword(email: String, password: String) {
        view?.showProgressBar()
        val loginData = LoginData(email,password)
        logIn.execute(loginData,object: IBaseUseCaseCallBack<Boolean> {
            override fun onSuccess(data: Boolean?) {
                if(isViewAttached()){
                    view?.hideProgressBar()
                    if(data!!)
                        view?.navigateToMain()
                    else
                        view?.showError("Error al tratar de ingresar")
                }
            }

            override fun onError(error: String) {
                if(isViewAttached()){
                    view?.hideProgressBar()
                    view?.showError(error)
                }
            }
        } )
    }

    override fun checkEmptyFields(email: String, password: String) = email.isEmpty() || password.isEmpty()

    override fun refreshUserLogStatus() {
        checkLoggedIn.execute(object:IBaseUseCaseCallBack<Boolean>{
            override fun onSuccess(data: Boolean?) {
                view!!.refreshUserLogStatus(data!!)
            }
            override fun onError(error: String) {
                view!!.showError(error)
            }
        })
    }

    override fun setup() {
        setupDefaultTeams.execute(object:IBaseUseCaseCallBack<List<TeamContainer>>{
            override fun onSuccess(data: List<TeamContainer>?) {
            }

            override fun onError(error: String) {
                view?.showError(error)
            }
        })
    }
}