package fcfm.lmad.poi.ChatPoi.domain.interactors.chat

import fcfm.lmad.poi.ChatPoi.domain.IRepository
import fcfm.lmad.poi.ChatPoi.domain.entities.Message
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack
import fcfm.lmad.poi.ChatPoi.infrastructure.repositories.MessageRepository

class RetrieveChatConversation(
    private val messageRepository: MessageRepository
):IRetrieveChatConversationUseCase {
    override fun execute(input: String, listener: IBaseUseCaseCallBack<List<Message>>) {
        val messages = ArrayList<Message>()
        messageRepository.getAll(object:IRepository.IRepositoryListener<List<Message>>{
            override fun onSuccess(data: List<Message>) {
                for(msg in data){
                    if(msg.chat_room_id != input) continue
                        messages.add(msg)
                }
                listener.onSuccess(messages)
            }
            override fun onError(error: String) {
                listener.onError(error)
            }
        })
    }
}
