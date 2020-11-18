package fcfm.lmad.poi.ChatPoi.presentation.login.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.fcfm.poi.plantilla.base.BaseActivity
import com.fcfm.poi.plantilla.presentation.login.ILoginContract
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.presentation.register.view.RegisterActivity
import fcfm.lmad.poi.ChatPoi.domain.interactors.loginInteractor.SignInInteractor
import fcfm.lmad.poi.ChatPoi.presentation.login.presenter.LoginPresenter
import fcfm.lmad.poi.ChatPoi.presentation.main.view.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), ILoginContract.ILoginView {

    lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = LoginPresenter(SignInInteractor())
        presenter.attachView(this)
        btnLogin.setOnClickListener {
            signIn()
        }
        btnSignup.setOnClickListener {
            navigateToRegister()
        }
    }

    override fun getLayout(): Int = R.layout.activity_login



    override fun showProgressBar() {
        pbarLogin.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        pbarLogin.visibility = View.GONE
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
        var intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun navigateToRegister() {
        startActivity(Intent(this, RegisterActivity::class.java))
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


/*
*
class LoginActivity : BaseActivity(), LoginContract.View {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        btnLogin.setOnClickListener {
            signIn()
            //startActivity(Intent(this, MainActivity::class.java))
        }
        btnSignup.setOnClickListener {
            //startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
    override fun getLayout(): Int = R.layout.activity_login

    override fun showError(msgError: String) {
        Toast.makeText(this,msgError, Toast.LENGTH_SHORT).show()
    }

    override fun showProgressBar() {
        pbarLogin.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        pbarLogin.visibility = View.GONE
    }

    override fun signIn() {
        toast(this,"prueba boton")
    }


}*/