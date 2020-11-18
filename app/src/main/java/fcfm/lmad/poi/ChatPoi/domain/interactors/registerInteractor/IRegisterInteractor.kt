package fcfm.lmad.poi.ChatPoi.domain.interactors.registerInteractor

interface IRegisterInteractor {
    interface IRegisterCallback{
        fun onRegisterSucces()
        fun onRegisterFailure(errorMsg:String)
    }
    fun signUp(userName:String, email:String, password:String, listener:IRegisterCallback)
}