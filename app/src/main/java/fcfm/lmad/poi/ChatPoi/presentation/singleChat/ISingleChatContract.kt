package fcfm.lmad.poi.ChatPoi.presentation.singleChat
import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.presentation.shared.IBasePresenter

interface ISingleChatContract {
    interface ISearchFrag{
        interface IView{
            fun showError(errorMsg:String)
            fun displayUserList(userList:List<User>)
            fun searchUser(searchedUsername:String)
        }
        interface IPresenter: IBasePresenter<IView> {
            fun retrieveAllUsers()
            fun searchUser(searchedUser:String)
        }
    }
    interface IListFrag{
        interface IView{

        }
        interface IPresenter: IBasePresenter<ISearchFrag.IView>{

        }
    }
    interface ISettingsFrag{
        interface IView{

        }
        interface IPresenter: IBasePresenter<ISearchFrag.IView>{

        }
    }

}