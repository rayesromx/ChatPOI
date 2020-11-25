package fcfm.lmad.poi.ChatPoi.presentation.chat.presenter

import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack
import fcfm.lmad.poi.ChatPoi.domain.interactors.user.IListAllUsersUseCase
import fcfm.lmad.poi.ChatPoi.presentation.chat.IChatContract
import fcfm.lmad.poi.ChatPoi.presentation.shared.presenter.BasePresenter

class NewChatPresenter(
    private val listAllUsers: IListAllUsersUseCase
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
}