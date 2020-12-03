package fcfm.lmad.poi.ChatPoi.presentation.login.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import fcfm.lmad.poi.ChatPoi.presentation.shared.view.BaseActivity
import com.fcfm.poi.plantilla.presentation.login.ILoginContract
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.data.CustomSessionState
import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.domain.interactors.login.CheckLoggedIn
import fcfm.lmad.poi.ChatPoi.presentation.register.view.RegisterActivity
import fcfm.lmad.poi.ChatPoi.domain.interactors.login.LogIn
import fcfm.lmad.poi.ChatPoi.domain.interactors.teams.SetupDefaultTeams
import fcfm.lmad.poi.ChatPoi.domain.interactors.user.GetLoggedUser
import fcfm.lmad.poi.ChatPoi.infrastructure.repositories.TeamRepository
import fcfm.lmad.poi.ChatPoi.infrastructure.repositories.UserRepository
import fcfm.lmad.poi.ChatPoi.presentation.login.presenter.LoginPresenter
import fcfm.lmad.poi.ChatPoi.presentation.main.view.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), ILoginContract.IView {

    lateinit var presenter: LoginPresenter

    override fun getLayout(): Int = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = LoginPresenter(
            LogIn(),
            CheckLoggedIn(),
            SetupDefaultTeams(
                TeamRepository()
            ),
            GetLoggedUser(UserRepository())
        )
        presenter.attachView(this)

        presenter.refreshUserLogStatus()

        btnLogin.setOnClickListener {
            signIn()
        }
        btnSignup.setOnClickListener {
            navigateToRegister()
        }
        setup()
    }

    override fun setup() {
        presenter.setup()
    }

    override fun refreshUserLogStatus(isLoggedIn: Boolean) {
        if(isLoggedIn) {
            navigateToMain()

        }
    }

    override fun refreshUserData(loggedUser: User) {
        CustomSessionState.loggedUser = loggedUser
        CustomSessionState.userTeam = loggedUser.group
        navigateToMain()
    }

    override fun signIn() {
        val email = etxt_email.text.toString().trim()
        val password = etxt_password.text.toString().trim()
        if(presenter.checkEmptyFields(email, password))
            toast(this,"Revisa el email o contrasena")
        else
            presenter.signInUserWithEmailAndPassword(email, password)
    }

    override fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun navigateToRegister() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    override fun showProgressBar() {
        pbarLogin.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        pbarLogin.visibility = View.GONE
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter.detachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}