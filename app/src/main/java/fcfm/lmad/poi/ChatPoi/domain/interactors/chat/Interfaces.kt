package fcfm.lmad.poi.ChatPoi.domain.interactors.chat

import fcfm.lmad.poi.ChatPoi.domain.dto.RetrieveChat
import fcfm.lmad.poi.ChatPoi.domain.dto.ImageMsg
import fcfm.lmad.poi.ChatPoi.domain.entities.Message
import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCase
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseWithInput

interface IRetrieveChatConversationUseCase: IBaseUseCaseWithInput<RetrieveChat, List<Message>> {}
interface IRetrieveChatUserListUseCase: IBaseUseCase<List<User>>
interface ISendMessageUseCase: IBaseUseCaseWithInput<Message, Message> {}
interface ISendImageUseCase: IBaseUseCaseWithInput<ImageMsg, Message> {}
