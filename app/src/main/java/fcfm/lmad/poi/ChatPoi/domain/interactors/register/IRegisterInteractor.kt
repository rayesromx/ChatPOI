package fcfm.lmad.poi.ChatPoi.domain.interactors.register

interface IRegisterInteractor {
    interface IRegisterCallback{
        fun onRegisterSucces()
        fun onRegisterFailure(errorMsg:String)
    }
    fun signUp(userName:String, email:String, password:String, carrera:String,listener:IRegisterCallback)
}