package fcfm.lmad.poi.ChatPoi.presentation.chat.presenter

import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack
import fcfm.lmad.poi.ChatPoi.domain.interactors.user.IListAllUsersUseCase
import fcfm.lmad.poi.ChatPoi.domain.interactors.user.ISearchUserByUsernameUseCase
import fcfm.lmad.poi.ChatPoi.presentation.shared.presenter.BasePresenter
import fcfm.lmad.poi.ChatPoi.presentation.chat.IChatContract

class SearchFragPresenter(
    private val listUsers: IListAllUsersUseCase,
    private val searchUserByUsername: ISearchUserByUsernameUseCase
): BasePresenter<IChatContract.ISearchFrag.IView>(), IChatContract.ISearchFrag.IPresenter{

    override fun retrieveAllUsers() {
        listUsers.execute(object: IBaseUseCaseCallBack<List<User>> {
            override fun onSuccess(data: List<User>?) {
                view?.displayUserList(data!!)
            }
            override fun onError(error: String) {
                view?.showError(error)
            }
        })
    }

    override fun searchUser(searchedUser: String) {
        searchUserByUsername.execute(searchedUser,object:IBaseUseCaseCallBack< List<User>> {
            override fun onSuccess(data: List<User>?) {
                view?.displayUserList(data!!)
            }
            override fun onError(error: String) {
                view?.showError(error)
            }
        })
    }
}