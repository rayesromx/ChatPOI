package fcfm.lmad.poi.ChatPoi.presentation.chat.presenter

import fcfm.lmad.poi.ChatPoi.domain.entities.ChatRoom
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack
import fcfm.lmad.poi.ChatPoi.domain.interactors.chat.IRetrieveChatRoomsOfUserUseCase
import fcfm.lmad.poi.ChatPoi.presentation.chat.IChatContract
import fcfm.lmad.poi.ChatPoi.presentation.shared.presenter.BasePresenter

class ChatListPresenter(
    private val retrieveChatRoomsOfUser: IRetrieveChatRoomsOfUserUseCase
): BasePresenter<IChatContract.IChatListFrag.IView>(), IChatContract.IChatListFrag.IPresenter{
    override fun getListOfChats(userId:String) {
        retrieveChatRoomsOfUser.execute(userId,object: IBaseUseCaseCallBack<List<ChatRoom>> {
            override fun onSuccess(data: List<ChatRoom>?) {
                if(isViewAttached())
                    view!!.displayChatRooms(data!!)
            }
            override fun onError(error: String) {
                if(isViewAttached())
                    view!!.showError(error)
            }
        })
    }
}
