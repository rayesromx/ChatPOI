package fcfm.lmad.poi.ChatPoi.presentation.register.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import fcfm.lmad.poi.ChatPoi.presentation.shared.view.BaseActivity
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.domain.interactors.register.RegisterUser
import fcfm.lmad.poi.ChatPoi.domain.interactors.teams.AssociateUserWithTeam
import fcfm.lmad.poi.ChatPoi.presentation.main.view.MainActivity
import fcfm.lmad.poi.ChatPoi.presentation.register.IRegisterContract
import fcfm.lmad.poi.ChatPoi.presentation.register.presenter.RegisterPresenter
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity(), IRegisterContract.IView {

    lateinit var presenter: IRegisterContract.IPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = RegisterPresenter(
            RegisterUser(),
            AssociateUserWithTeam()
        )
        presenter.attachView(this)

        btn_register.setOnClickListener{
            signUp()
        }
    }

    override fun getLayout(): Int = R.layout.activity_register

    override fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun signUp() {
        val username = etxt_user_name.text.toString().trim()
        val email = etxt_email.text.toString().trim()
        val pwd1 = etxt_password.text.toString().trim()
        val pwd2 = etxt_password_confirmation.text.toString().trim()
        var group = spin_carrera.selectedItem.toString()

        if(presenter.checkEmptyField(username)){
            etxt_user_name.error = "El nombre esta vacio"
            return
        }
        if(!presenter.checkValidEmail(email)){
            etxt_email.error = "El correo no es valido"
            return
        }

        if(presenter.checkEmptyField(pwd1)){
            etxt_password.error = "La contrasena esta vacia"
            return
        }

        if(presenter.checkEmptyField(pwd2)){
            etxt_password_confirmation.error = "La contrasena de confirmacion esta vacia"
            return
        }

        if(!presenter.checkPasswordsMatch(pwd1,pwd2)){
            etxt_password_confirmation.error = "Las contrasenas no coinciden"
            return
        }

        val user = User()
        user.username = username
        user.email = email
        user.group = group

        presenter.signUp(user,pwd1)
    }

    override fun showProgressBar() {
        pbar_loading.visibility = View.VISIBLE
        btn_register.visibility = View.GONE
    }

    override fun hideProgressBar() {
        pbar_loading.visibility = View.GONE
        btn_register.visibility = View.VISIBLE
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