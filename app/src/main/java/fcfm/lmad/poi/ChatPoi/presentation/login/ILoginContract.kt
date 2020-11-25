package com.fcfm.poi.plantilla.presentation.login

import fcfm.lmad.poi.ChatPoi.presentation.shared.IBasePresenter
import fcfm.lmad.poi.ChatPoi.presentation.shared.IBaseViewWithProgress

interface ILoginContract {
    interface IView: IBaseViewWithProgress {
        fun signIn()
        fun navigateToMain()
        fun navigateToRegister()
        fun setup()
        fun refreshUserLogStatus(isLoggedIn:Boolean)
    }

    interface IPresenter: IBasePresenter<ILoginContract.IView> {
        fun signInUserWithEmailAndPassword(email:String, password:String)
        fun checkEmptyFields(email:String, password:String): Boolean
        fun refreshUserLogStatus()
        fun setup()
    }
}