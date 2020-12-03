package fcfm.lmad.poi.ChatPoi.domain.interactors.chat

import fcfm.lmad.poi.ChatPoi.data.CustomSessionState
import fcfm.lmad.poi.ChatPoi.domain.IRepository
import fcfm.lmad.poi.ChatPoi.domain.entities.ChatRoom
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack
import fcfm.lmad.poi.ChatPoi.infrastructure.repositories.ChatRoomRepository

class CreateNewChatRoom(
    private val repository: ChatRoomRepository
): ICreateNewChatRoomUseCase {
    override fun execute(input: ChatRoom, listener: IBaseUseCaseCallBack<ChatRoom>) {
        if(input.users.size > 2) {
            save(input, listener)
            return
        }
        else
        repository.getChatRoomOfUser(CustomSessionState.loggedUser.uid, object:IRepository.IRepositoryListener<List<ChatRoom>>{
            override fun onSuccess(data: List<ChatRoom>) {
                for (chat in data){
                    if(chat.users.size == 2 && chat.users.containsAll(input.users)){
                        //listener.onError("Ya existe un chat con los usuarios seleccionados")
                        return
                    }
                }
                save(input,listener)
            }
            override fun onError(error: String) {
                listener.onError(error)
            }
        })

    }

    private fun save(input: ChatRoom, listener: IBaseUseCaseCallBack<ChatRoom>){
        repository.save(input,object:IRepository.IRepositoryListener<String>{
            override fun onSuccess(data: String) {
                val model = ChatRoom()
                model.uid = data
                listener.onSuccess(model)
            }

            override fun onError(error: String) {
                listener.onError(error)
            }
        })
    }
}