package fcfm.lmad.poi.ChatPoi.presentation.chat.presenter

import android.net.Uri
import fcfm.lmad.poi.ChatPoi.domain.entities.Message
import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.domain.interactors.chat.ISendMessageInteractor
import fcfm.lmad.poi.ChatPoi.domain.interactors.user.IRetrieveUserDataInteractor
import fcfm.lmad.poi.ChatPoi.presentation.chat.IChatContract
import fcfm.lmad.poi.ChatPoi.presentation.shared.presenter.BasePresenter

class ChatRoomPresenter(
    private val retrieveUserDataInteractor: IRetrieveUserDataInteractor,
    private val sendMessageInteractor: ISendMessageInteractor
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

    override fun sendMessage(message: String, receiver: String) {
        var msg = Message()
        msg.message = message
        msg.receiver = receiver

        sendMessageInteractor.sendMessage(msg, object:ISendMessageInteractor.ISendMessageCallback{
            override fun onSuccess(data: Message?) {
                //view?.displayUserData(data!!)
            }
            override fun onError(errorMessage: String) {
                view?.showError(errorMessage)
            }
        })
    }

    override fun sendImage(filePath: Uri, receiver: String) {
        var msg = Message()
        msg.receiver = receiver

        sendMessageInteractor.sendImage(msg,filePath, object:ISendMessageInteractor.ISendMessageCallback{
            override fun onSuccess(data: Message?) {
                //view?.displayUserData(data!!)
            }
            override fun onError(errorMessage: String) {
                view?.showError(errorMessage)
            }
        })
    }
}