package fcfm.lmad.poi.ChatPoi.infrastructure.repositories

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import fcfm.lmad.poi.ChatPoi.domain.IRepository
import fcfm.lmad.poi.ChatPoi.domain.entities.ChatMessageReference

class ChatMessageReferenceRepository: FireBaseRepository<ChatMessageReference>("ChatMessageReference") {
    override fun getValue(item: DataSnapshot) = item.getValue(ChatMessageReference::class.java)
}