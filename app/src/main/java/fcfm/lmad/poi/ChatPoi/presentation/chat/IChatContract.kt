package fcfm.lmad.poi.ChatPoi.presentation.chat
import android.net.Uri
import fcfm.lmad.poi.ChatPoi.domain.dto.SelectableUser
import fcfm.lmad.poi.ChatPoi.domain.entities.ChatList
import fcfm.lmad.poi.ChatPoi.domain.entities.ChatRoom
import fcfm.lmad.poi.ChatPoi.domain.entities.Message
import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.presentation.shared.IBasePresenter
import fcfm.lmad.poi.ChatPoi.presentation.shared.IBaseView

interface IChatContract {
    interface ISearchFrag{
        interface IView : IBaseView {
            fun displayUserList(userList:List<User>)
            fun searchUser(searchedUsername:String)
        }
        interface IPresenter: IBasePresenter<IView> {
            fun retrieveAllUsers()
            fun searchUser(searchedUser:String)
        }
    }
    interface IChatRoom{
        interface IView : IBaseView {
            fun refreshChatRoomTabLayout(users:List<User>)
            //deprecated
            fun displayUserData(user: User)
            fun sendMessage()
            fun sendImage()
            fun displayChatMessages(messages:List<Message>)
            fun onDownloadFile(downloadId:Long)
            fun startDownloadingUrl(msg:Message)
            fun displayLargeImage()
        }
        interface IPresenter: IBasePresenter<IView>{
            fun retrieveChatRoomData(chatRoomId:String)
            fun sendMessage(message:String, chatRoomReceiver:ChatRoom)
            fun loadChatMessages(chatRoomId: String)
            fun startDownloadingUrl(msg:Message)
            //deprecated
            fun sendImage(fileName:String, filePath: Uri, receiver:String)
            fun retrieveUserData(partnerUserId: String)
        }
    }

    interface IChatListFrag{
        interface IView : IBaseView {
            fun displayChatRooms(list:List<ChatRoom>)
            fun navigateToNewChat()

        }
        interface IPresenter: IBasePresenter<IView> {
            fun getListOfChats(userId:String)
        }
    }

    interface INewChatList{
        interface IView : IBaseView {
            fun displayUsers(list:List<User>)
            fun startChat(chatRoom: ChatRoom)
        }
        interface IPresenter: IBasePresenter<IView> {
            fun getListOfChats()
            fun startNewChatRoom(chatRoom: ChatRoom)
        }
    }

}