package fcfm.lmad.poi.ChatPoi.presentation.chat.presenter

import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack
import fcfm.lmad.poi.ChatPoi.domain.interactors.chat.IRetrieveChatUserListUseCase
import fcfm.lmad.poi.ChatPoi.presentation.chat.IChatContract
import fcfm.lmad.poi.ChatPoi.presentation.shared.presenter.BasePresenter

class ChatListPresenter(
    private val retrieveChatUserList: IRetrieveChatUserListUseCase
): BasePresenter<IChatContract.IChatListFrag.IView>(), IChatContract.IChatListFrag.IPresenter{
    override fun getListOfChats() {
        retrieveChatUserList.execute(object: IBaseUseCaseCallBack<List<User>> {
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
}
