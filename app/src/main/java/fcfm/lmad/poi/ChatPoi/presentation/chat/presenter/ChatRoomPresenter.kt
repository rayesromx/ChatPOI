package fcfm.lmad.poi.ChatPoi.presentation.chat.presenter

import android.net.Uri
import fcfm.lmad.poi.ChatPoi.domain.dto.RetrieveChat
import fcfm.lmad.poi.ChatPoi.domain.dto.ImageMsg
import fcfm.lmad.poi.ChatPoi.domain.entities.Message
import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack
import fcfm.lmad.poi.ChatPoi.domain.interactors.chat.IRetrieveChatConversationUseCase
import fcfm.lmad.poi.ChatPoi.domain.interactors.chat.ISendImageUseCase
import fcfm.lmad.poi.ChatPoi.domain.interactors.chat.ISendMessageUseCase
import fcfm.lmad.poi.ChatPoi.domain.interactors.login.IGetLoggedUserDataUseCase
import fcfm.lmad.poi.ChatPoi.domain.interactors.user.ISearchUserByIdUseCase
import fcfm.lmad.poi.ChatPoi.presentation.chat.IChatContract
import fcfm.lmad.poi.ChatPoi.presentation.shared.presenter.BasePresenter

class ChatRoomPresenter(
        private val getLoggedUserData: IGetLoggedUserDataUseCase,
        private val searchUserById: ISearchUserByIdUseCase,
        private val sendMessage: ISendMessageUseCase,
        private val sendImage: ISendImageUseCase,
        private val retrieveChatConversation: IRetrieveChatConversationUseCase
):BasePresenter<IChatContract.IChatRoom.IView>(), IChatContract.IChatRoom.IPresenter {

    override fun retrieveCurrentUserData() {
        getLoggedUserData.execute( object: IBaseUseCaseCallBack<User> {
            override fun onSuccess(data: User?) {
                view?.displayCurrentUserData(data!!)
            }
            override fun onError(error: String) {
                view?.showError(error)
            }
        })
    }

    override fun retrieveUserData(partnerUserId: String) {
        searchUserById.execute(partnerUserId, object:IBaseUseCaseCallBack<User>{
            override fun onSuccess(data: User?) {
               view?.displayUserData(data!!)
            }
            override fun onError(error: String) {
                view?.showError(error)
            }
        })
    }

    override fun sendMessage(message: String, receiver: String) {
        val msg = Message()
        msg.message = message
        msg.receiver = receiver
        sendMessage.execute(msg, object:IBaseUseCaseCallBack<Message>{
            override fun onSuccess(data: Message?) {
                //view?.displayUserData(data!!)
            }
            override fun onError(error: String) {
                view?.showError(error)
            }
        })
    }

    override fun sendImage(filePath: Uri, receiver: String) {
        val msg = Message()
        msg.receiver = receiver
        val imageMessage = ImageMsg(msg,filePath)

        sendImage.execute(imageMessage, object:IBaseUseCaseCallBack<Message>{
            override fun onSuccess(data: Message?) {
                //view?.displayUserData(data!!)
            }
            override fun onError(error: String) {
                view?.showError(error)
            }
        })
    }

    override fun loadChatMessages(sender: String, reciever: String) {
        val retrieveChat = RetrieveChat(sender,reciever)
        retrieveChatConversation.execute(retrieveChat,object:IBaseUseCaseCallBack<List<Message>>{
            override fun onSuccess(data: List<Message>?) {
                view?.displayChatMessages(data!!)
            }

            override fun onError(error: String) {
                view?.showError(error)
            }
        })
    }
}