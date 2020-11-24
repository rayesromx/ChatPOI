package com.fcfm.poi.plantilla.presentation.login

import fcfm.lmad.poi.ChatPoi.presentation.shared.IBasePresenter
import fcfm.lmad.poi.ChatPoi.presentation.shared.IBaseView

interface ILoginContract {
    interface ILoginView: IBaseView {
        fun signIn()
        fun navigateToMain()
        fun navigateToRegister()
        fun setup()
        fun refreshUserLogStatus(isLoggedIn:Boolean)
    }

    interface ILoginPresenter: IBasePresenter<ILoginContract.ILoginView> {
        fun signInUserWithEmailAndPassword(email:String, password:String)
        fun checkEmptyFields(email:String, password:String): Boolean
        fun refreshUserLogStatus()
        fun setup()
    }
}