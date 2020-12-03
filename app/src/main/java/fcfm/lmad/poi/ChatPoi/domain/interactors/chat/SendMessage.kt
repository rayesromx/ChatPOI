package fcfm.lmad.poi.ChatPoi.domain.interactors.chat

import fcfm.lmad.poi.ChatPoi.data.CustomSessionState
import fcfm.lmad.poi.ChatPoi.domain.IRepository
import fcfm.lmad.poi.ChatPoi.domain.entities.Message
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack
import fcfm.lmad.poi.ChatPoi.infrastructure.repositories.MessageRepository

class SendMessage(
    private val messageRepository: MessageRepository
): ISendMessageUseCase {
    override fun execute(input: Message, listener: IBaseUseCaseCallBack<Message>) {
        input.sender = CustomSessionState.loggedUser.uid
        input.message = CustomSessionState.encrypt(input.message)

        messageRepository.save(input, object: IRepository.IRepositoryListener<String>{
            override fun onSuccess(data: String) {
                listener.onSuccess(input)
            }
            override fun onError(error: String) {
                listener.onError(error)
            }
        })
    }
}