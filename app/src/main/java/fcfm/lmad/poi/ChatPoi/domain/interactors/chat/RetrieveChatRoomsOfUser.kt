package fcfm.lmad.poi.ChatPoi.domain.interactors.chat

import fcfm.lmad.poi.ChatPoi.domain.IRepository
import fcfm.lmad.poi.ChatPoi.domain.entities.ChatRoom
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack
import fcfm.lmad.poi.ChatPoi.infrastructure.repositories.ChatRoomRepository

class RetrieveChatRoomsOfUser(
    private val chatRoomRepository: ChatRoomRepository
): IRetrieveChatRoomsOfUserUseCase {

    override fun execute(input:String, listener: IBaseUseCaseCallBack<List<ChatRoom>>) {
        chatRoomRepository.getChatRoomOfUser(input, object: IRepository.IRepositoryListener<List<ChatRoom>>{
            override fun onSuccess(data: List<ChatRoom>) {
                listener.onSuccess(data)
            }

            override fun onError(error: String) {
                listener.onError(error)
            }
        })
    }
}