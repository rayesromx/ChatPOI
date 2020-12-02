package fcfm.lmad.poi.ChatPoi.domain.interactors.chat

import fcfm.lmad.poi.ChatPoi.domain.IRepository
import fcfm.lmad.poi.ChatPoi.domain.entities.ChatMessageReference
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack
import fcfm.lmad.poi.ChatPoi.infrastructure.repositories.ChatMessageReferenceRepository

class RelateChatRoomMessage(
    private val repository:  ChatMessageReferenceRepository
): IRelateChatRoomMessageUseCase{
    override fun execute(input: ChatMessageReference, listener: IBaseUseCaseCallBack<Boolean>) {
        repository.save(input,object: IRepository.IRepositoryListener<String>{
            override fun onSuccess(data: String) {
                listener.onSuccess(true)
            }

            override fun onError(error: String) {
                listener.onSuccess(false)
                listener.onError(error)
            }
        })
    }
}