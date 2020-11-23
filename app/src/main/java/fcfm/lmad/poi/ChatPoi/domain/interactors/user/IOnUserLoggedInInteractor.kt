package fcfm.lmad.poi.ChatPoi.domain.interactors.user

import fcfm.lmad.poi.ChatPoi.domain.entities.User

interface IOnUserLoggedInInteractor {
    interface IOnUserLoggedInCallback{
        fun onLoggedInSuccess(user:User?)
        fun onLoggedInError(errorMsg:String)
    }
    fun refreshUserData(listener:IOnUserLoggedInCallback)
}