package fcfm.lmad.poi.ChatPoi.presentation.shared


interface IBasePresenter<T> {
    fun attachView(view: T)
    fun detachView()
    fun isViewAttached():Boolean
}
