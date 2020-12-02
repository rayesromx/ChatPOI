package fcfm.lmad.poi.ChatPoi.domain.interactors.chat

import fcfm.lmad.poi.ChatPoi.domain.IRepository
import fcfm.lmad.poi.ChatPoi.domain.entities.ChatRoom
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack
import fcfm.lmad.poi.ChatPoi.infrastructure.repositories.ChatRoomRepository

class GetCharRoomData(
    private val repository: ChatRoomRepository
) : IGetCharRoomDataUseCase {
    override fun execute(input: String, listener: IBaseUseCaseCallBack<ChatRoom>) {
        repository.getById(input,object:IRepository.IRepositoryListener<ChatRoom?>{
            override fun onSuccess(data: ChatRoom?) {
                listener.onSuccess(data)
            }
            override fun onError(error: String) {
                listener.onError(error)
            }
        })
    }
}