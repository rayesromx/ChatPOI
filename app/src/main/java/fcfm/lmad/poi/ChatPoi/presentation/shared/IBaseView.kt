package fcfm.lmad.poi.ChatPoi.presentation.shared

interface IBaseView {
    fun showError(errorMsg:String)
    fun showProgressBar()
    fun hideProgressBar()
}