package fcfm.lmad.poi.ChatPoi.domain.interactors.chat

import fcfm.lmad.poi.ChatPoi.domain.entities.Message
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseInteractorCallBack

interface ISendMessageInteractor {
    interface ISendMessageCallback : IBaseInteractorCallBack<Message> {}
    fun sendMessage(message:Message,listener: ISendMessageCallback)
}