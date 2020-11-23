package fcfm.lmad.poi.ChatPoi.domain.interactors.chat

import fcfm.lmad.poi.ChatPoi.domain.entities.Message
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseInteractorCallBack

interface IRetrieveChatConversationInteractor {
    interface IRetrieveChatConversationCallback:IBaseInteractorCallBack<List<Message>>{}
    fun getChatConversation(sender:String, receiver: String,listener:IRetrieveChatConversationCallback)
}

