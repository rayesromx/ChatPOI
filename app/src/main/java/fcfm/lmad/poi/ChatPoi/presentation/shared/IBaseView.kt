package fcfm.lmad.poi.ChatPoi.presentation.shared

interface IBaseView {
    fun showError(errorMsg:String)

}

interface IBaseViewWithProgress:IBaseView{
    fun showProgressBar()
    fun hideProgressBar()
}