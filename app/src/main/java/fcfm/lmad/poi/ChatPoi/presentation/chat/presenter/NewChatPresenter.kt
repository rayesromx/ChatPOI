package fcfm.lmad.poi.ChatPoi.presentation.chat.presenter

import fcfm.lmad.poi.ChatPoi.data.CustomSessionState
import fcfm.lmad.poi.ChatPoi.domain.entities.ChatRoom
import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack
import fcfm.lmad.poi.ChatPoi.domain.interactors.chat.ICreateNewChatRoomUseCase
import fcfm.lmad.poi.ChatPoi.domain.interactors.user.IListAllUsersUseCase
import fcfm.lmad.poi.ChatPoi.presentation.chat.IChatContract
import fcfm.lmad.poi.ChatPoi.presentation.shared.presenter.BasePresenter

class NewChatPresenter(
    private val listAllUsers: IListAllUsersUseCase,
    private val createNewChatRoom: ICreateNewChatRoomUseCase
): BasePresenter<IChatContract.INewChatList.IView>(), IChatContract.INewChatList.IPresenter{
    override fun getListOfChats() {
        listAllUsers.execute(object: IBaseUseCaseCallBack<List<User>> {
            override fun onSuccess(data: List<User>?) {
                if(isViewAttached())
                    view!!.displayUsers(data!!)
            }
            override fun onError(error: String) {
                if(isViewAttached())
                    view!!.showError(error)
            }
        })
    }

    override fun startNewChatRoom(chatRoom: ChatRoom){
        createNewChatRoom.execute(chatRoom, object: IBaseUseCaseCallBack<ChatRoom>{
            override fun onSuccess(data: ChatRoom?) {
                CustomSessionState.currentChatRoom = chatRoom
                if(!isViewAttached())
                    return
                view!!.startChat(data!!)
            }

            override fun onError(error: String) {
                if(!isViewAttached())
                    return
                view!!.showError(error)
            }

        })
    }
}