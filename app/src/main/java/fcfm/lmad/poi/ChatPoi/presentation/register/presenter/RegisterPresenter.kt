package fcfm.lmad.poi.ChatPoi.presentation.register.presenter

import androidx.core.util.PatternsCompat
import fcfm.lmad.poi.ChatPoi.domain.dto.RegisterData
import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack
import fcfm.lmad.poi.ChatPoi.domain.interactors.register.IRegisterUserUseCase
import fcfm.lmad.poi.ChatPoi.domain.interactors.teams.IAssociateUserWithTeamUseCase
import fcfm.lmad.poi.ChatPoi.presentation.register.IRegisterContract
import fcfm.lmad.poi.ChatPoi.presentation.shared.presenter.BasePresenter

class RegisterPresenter(
        private val registerUser: IRegisterUserUseCase,
        private val associateUserWithTeam: IAssociateUserWithTeamUseCase
): BasePresenter<IRegisterContract.IView>(), IRegisterContract.IPresenter {

    override fun checkEmptyField(field: String): Boolean = field.isEmpty()

    override fun checkValidEmail(email: String):Boolean
            = PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()

    override fun checkPasswordsMatch(pwd1: String, pwd2: String): Boolean = pwd1 == pwd2

    override fun signUp(user: User, password:String){
        view?.showProgressBar()
        var registerData = RegisterData(user,password)
        registerUser.execute(registerData,object: IBaseUseCaseCallBack<Boolean> {
            override fun onSuccess(data: Boolean?) {
                view?.navigateToMain()
                view?.hideProgressBar()
            }

            override fun onError(error: String) {
                view?.showError(error)
                view?.hideProgressBar()
            }
        })
    }
}