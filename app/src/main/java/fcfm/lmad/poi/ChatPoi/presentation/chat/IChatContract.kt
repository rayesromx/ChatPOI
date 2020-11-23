package fcfm.lmad.poi.ChatPoi.presentation.chat
import android.net.Uri
import fcfm.lmad.poi.ChatPoi.domain.entities.Message
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
            fun displayCurrentUserData(user:User)
            fun sendMessage()
            fun sendImage()
            fun displayChatMessages(messages:List<Message>)
        }
        interface IPresenter: IBasePresenter<IChatRoom.IView>{
            fun retrieveUserData(partnerUserId:String)
            fun retrieveCurrentUserData()
            fun sendMessage(message:String, receiver:String)
            fun sendImage(filePath: Uri, receiver:String)
            fun loadChatMessages(sender:String, receiver: String)
        }
    }
    interface ISettingsFrag{
        interface IView{

        }
        interface IPresenter: IBasePresenter<ISearchFrag.IView>{

        }
    }

}