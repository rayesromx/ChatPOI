package fcfm.lmad.poi.ChatPoi.infrastructure.repositories

import com.google.firebase.database.DataSnapshot
import fcfm.lmad.poi.ChatPoi.domain.IRepository
import fcfm.lmad.poi.ChatPoi.domain.entities.ChatRoom

class ChatRoomRepository: FireBaseRepository<ChatRoom>("ChatRoom") {
    override fun getValue(item: DataSnapshot) = item.getValue(ChatRoom::class.java)
    fun getChatRoomOfUser(userUid:String, listener: IRepository.IRepositoryListener<List<ChatRoom>>){
        val chatRooms = ArrayList<ChatRoom>()
        getAll(object:IRepository.IRepositoryListener<List<ChatRoom>>{
            override fun onSuccess(data: List<ChatRoom>) {
                chatRooms.clear()
                for (room in data){
                    if(!room.users.contains(userUid))
                        continue
                    chatRooms.add(room)
                }
                listener.onSuccess(chatRooms)
            }

            override fun onError(error: String) {
                listener.onError(error)
            }
        })
    }
}