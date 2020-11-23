package fcfm.lmad.poi.ChatPoi.presentation.chat
import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.presentation.shared.IBasePresenter

interface IChatContract {
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
    interface IChatRoom{
        interface IView{
            fun showError(errorMsg:String)
            fun displayUserData(user:User)

        }
        interface IPresenter: IBasePresenter<IChatRoom.IView>{
            fun retrieveUserData(partnerUserId:String)
        }
    }
    interface ISettingsFrag{
        interface IView{

        }
        interface IPresenter: IBasePresenter<ISearchFrag.IView>{

        }
    }

}