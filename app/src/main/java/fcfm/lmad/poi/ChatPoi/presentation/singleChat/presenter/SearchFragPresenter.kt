package fcfm.lmad.poi.ChatPoi.presentation.singleChat.presenter

import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.domain.interactors.userInteractor.IListUsersInteractor
import fcfm.lmad.poi.ChatPoi.presentation.shared.presenter.BasePresenter
import fcfm.lmad.poi.ChatPoi.presentation.singleChat.ISingleChatContract

class SearchFragPresenter(
    private val listUsersInteractor: IListUsersInteractor
): BasePresenter<ISingleChatContract.ISearchFrag.IView>(), ISingleChatContract.ISearchFrag.IPresenter{

    override fun retrieveAllUsers() {
        listUsersInteractor.retrieveAllUsers(object: IListUsersInteractor.IListUsersCallback {
            override fun onSuccess(data: List<User>?) {
                view?.displayUserList(data!!)
            }
            override fun onError(errorMessage: String) {
                view?.showError(errorMessage)
            }
        })
    }

    override fun searchUser(searchedUser: String) {
        listUsersInteractor.searchUser(searchedUser,object: IListUsersInteractor.IListUsersCallback {
            override fun onSuccess(data: List<User>?) {
                view?.displayUserList(data!!)
            }
            override fun onError(errorMessage: String) {
                view?.showError(errorMessage)
            }
        })
    }
}