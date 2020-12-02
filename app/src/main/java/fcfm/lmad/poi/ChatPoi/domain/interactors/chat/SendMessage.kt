package fcfm.lmad.poi.ChatPoi.domain.interactors.chat

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import fcfm.lmad.poi.ChatPoi.domain.IRepository
import fcfm.lmad.poi.ChatPoi.domain.entities.Message
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack
import fcfm.lmad.poi.ChatPoi.infrastructure.repositories.MessageRepository

class SendMessage(
    private val messageRepository: MessageRepository
): ISendMessageUseCase {
    override fun execute(input: Message, listener: IBaseUseCaseCallBack<Message>) {
        val reference = FirebaseDatabase.getInstance().reference
        input.uid = reference.push().key!!
        input.sender = FirebaseAuth.getInstance().currentUser!!.uid
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