package fcfm.lmad.poi.ChatPoi.domain.interactors.chat

import fcfm.lmad.poi.ChatPoi.data.CustomSessionState
import fcfm.lmad.poi.ChatPoi.domain.IRepository
import fcfm.lmad.poi.ChatPoi.domain.dto.FileMsg
import fcfm.lmad.poi.ChatPoi.domain.dto.ImageMsg
import fcfm.lmad.poi.ChatPoi.domain.entities.Message
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack
import fcfm.lmad.poi.ChatPoi.domain.interactors.files.SendFile
import fcfm.lmad.poi.ChatPoi.infrastructure.repositories.MessageRepository

class SendImage(
        private val messageRepository: MessageRepository
): ISendImageUseCase {
    override fun execute(input: ImageMsg, listener: IBaseUseCaseCallBack<FileMsg>) {
        val sendFile = SendFile()
        val mfile = FileMsg(input.fileName, input.filePath)
        input.message.sender = CustomSessionState.loggedUser.uid

        sendFile.execute(mfile, object : IBaseUseCaseCallBack<String> {
            override fun onSuccess(data: String?) {
                input.message.message = "Envio una imagen"
                input.message.image_url = data!!

                messageRepository.save(input.message, object : IRepository.IRepositoryListener<String> {
                    override fun onSuccess(data: String) {
                        mfile.msg.uid = data
                        listener.onSuccess(mfile)
                    }

                    override fun onError(error: String) {
                        listener.onError(error)
                    }
                })
            }

            override fun onError(error: String) {
                listener.onError(error)
            }
        })
    }
}