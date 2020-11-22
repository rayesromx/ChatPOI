package fcfm.lmad.poi.ChatPoi.domain.interactors

interface IBaseInteractorCallBack<T> {
    fun onSuccess(data: T?)
    fun onError(errorMessage:String)
}