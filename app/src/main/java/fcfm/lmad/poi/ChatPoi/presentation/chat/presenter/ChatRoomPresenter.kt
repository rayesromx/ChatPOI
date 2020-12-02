package fcfm.lmad.poi.ChatPoi.presentation.chat.presenter

import android.net.Uri
import fcfm.lmad.poi.ChatPoi.domain.dto.RetrieveChat
import fcfm.lmad.poi.ChatPoi.domain.dto.ImageMsg
import fcfm.lmad.poi.ChatPoi.domain.entities.ChatMessageReference
import fcfm.lmad.poi.ChatPoi.domain.entities.ChatRoom
import fcfm.lmad.poi.ChatPoi.domain.entities.Message
import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack
import fcfm.lmad.poi.ChatPoi.domain.interactors.chat.*
import fcfm.lmad.poi.ChatPoi.domain.interactors.login.IGetLoggedUserDataUseCase
import fcfm.lmad.poi.ChatPoi.domain.interactors.user.ISearchUserByIdUseCase
import fcfm.lmad.poi.ChatPoi.presentation.chat.IChatContract
import fcfm.lmad.poi.ChatPoi.presentation.shared.presenter.BasePresenter

class ChatRoomPresenter(
    private val getCharRoomData: IGetCharRoomDataUseCase,
    private val searchUserById: ISearchUserByIdUseCase,
    private val sendMessage: ISendMessageUseCase,
    private val retrieveChatConversation: IRetrieveChatConversationUseCase,

        //deprecated
    private val sendImage: ISendImageUseCase,
):BasePresenter<IChatContract.IChatRoom.IView>(), IChatContract.IChatRoom.IPresenter {

    val chatUsers = ArrayList<User>()

    override fun retrieveChatRoomData(chatRoomId:String){
        getCharRoomData.execute(chatRoomId, object:IBaseUseCaseCallBack<ChatRoom>{
            override fun onSuccess(data: ChatRoom?) {
                if(!isViewAttached())  return
                val innerChatUsers = ArrayList<User>()
                val usersSize = data!!.users.size
                for (user in data.users){
                    searchUserById.execute(user, object:IBaseUseCaseCallBack<User>{
                        override fun onSuccess(data: User?) {
                            innerChatUsers.add(data!!)
                            if(innerChatUsers.size == usersSize){
                                view!!.refreshChatRoomTabLayout(innerChatUsers)
                                loadChatMessages(chatRoomId)
                            }
                        }
                        override fun onError(error: String) {
                        }
                    })
                }
            }
            override fun onError(error: String) {
                if(!isViewAttached())  return
            }
        })
    }

    override fun sendMessage(message: String, chatRoomReceiver:ChatRoom) {
        val msg = Message()
        msg.message = message
        msg.chat_room_id = chatRoomReceiver.uid
        sendMessage.execute(msg, object:IBaseUseCaseCallBack<Message>{
            override fun onSuccess(data: Message?) {

            }
            override fun onError(error: String) {
                view?.showError(error)
            }
        })
    }

    override fun loadChatMessages(chatRoomId: String) {
        retrieveChatConversation.execute(chatRoomId,object:IBaseUseCaseCallBack<List<Message>>{
            override fun onSuccess(data: List<Message>?) {
                view?.displayChatMessages(data!!)
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


}