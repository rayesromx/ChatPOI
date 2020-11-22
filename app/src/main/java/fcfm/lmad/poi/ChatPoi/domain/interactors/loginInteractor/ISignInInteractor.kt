package fcfm.lmad.poi.ChatPoi.domain.interactors.loginInteractor

interface ISignInInteractor {
    interface ISignInCallback{
        fun onSignInSuccess()
        fun onSignInError(errorMsg:String)
    }
    fun signIn(email:String,password:String,listener:ISignInCallback)

}