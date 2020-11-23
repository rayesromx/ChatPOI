package fcfm.lmad.poi.ChatPoi.presentation.chat.presenter

import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.domain.interactors.chat.IRetrieveChatUserListInteractor
import fcfm.lmad.poi.ChatPoi.presentation.chat.IChatContract
import fcfm.lmad.poi.ChatPoi.presentation.shared.presenter.BasePresenter

class ChatListPresenter(
    private val retrieveChatUserListInteractor: IRetrieveChatUserListInteractor
)
    : BasePresenter<IChatContract.IChatListFrag.IView>(), IChatContract.IChatListFrag.IPresenter{
    override fun getChatListOfUser() {
        retrieveChatUserListInteractor.getChatuserList(object:IRetrieveChatUserListInteractor.IRetrieveChatUserListICallback {
            override fun onSuccess(data: List<User>?) {
                view!!.displayUsers(data!!)
            }

            override fun onError(errorMessage: String) {
                view!!.showError(errorMessage)
            }
        })
    }
}