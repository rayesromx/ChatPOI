package fcfm.lmad.poi.ChatPoi.presentation.chat.presenter

import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.domain.interactors.user.IRetrieveUserDataInteractor
import fcfm.lmad.poi.ChatPoi.presentation.chat.IChatContract
import fcfm.lmad.poi.ChatPoi.presentation.shared.presenter.BasePresenter

class ChatRoomPresenter(
    private val retrieveUserDataInteractor: IRetrieveUserDataInteractor
):BasePresenter<IChatContract.IChatRoom.IView>(), IChatContract.IChatRoom.IPresenter {
    override fun retrieveUserData(partnerUserId: String) {
        retrieveUserDataInteractor.retrieveUser(partnerUserId, object:IRetrieveUserDataInteractor.IRetrieveUserDataInteractorCallback{
            override fun onSuccess(data: User?) {
                view?.displayUserData(data!!)
            }
            override fun onError(errorMessage: String) {
                view?.showError(errorMessage)
            }
        })
    }
}