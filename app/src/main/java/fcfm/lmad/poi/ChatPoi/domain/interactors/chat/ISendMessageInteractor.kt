package fcfm.lmad.poi.ChatPoi.domain.interactors.chat

import android.net.Uri
import fcfm.lmad.poi.ChatPoi.domain.entities.Message
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseInteractorCallBack

interface ISendMessageInteractor {
    interface ISendMessageCallback : IBaseInteractorCallBack<Message> {}
    fun sendMessage(msg:Message, listener: ISendMessageCallback)
    fun sendImage(msg:Message, fileUri: Uri?, listener: ISendMessageCallback)
}