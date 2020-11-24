package fcfm.lmad.poi.ChatPoi.domain.interactors

interface IBaseUseCaseCallBack<Response> {
    fun onSuccess(data: Response?)
    fun onError(error: String)
}

interface IBaseUseCaseWithInput<Request,Response> {
    fun execute(input:Request,listener:IBaseUseCaseCallBack<Response>)
}

interface IBaseUseCase<Response> {
    fun execute(listener:IBaseUseCaseCallBack<Response>)
}

