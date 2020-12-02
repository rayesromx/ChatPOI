package fcfm.lmad.poi.ChatPoi.domain.interactors.chat

import fcfm.lmad.poi.ChatPoi.domain.dto.ImageMsg
import fcfm.lmad.poi.ChatPoi.domain.entities.ChatMessageReference
import fcfm.lmad.poi.ChatPoi.domain.entities.ChatRoom
import fcfm.lmad.poi.ChatPoi.domain.entities.Message
import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCase
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseWithInput

interface IRetrieveChatRoomsOfUserUseCase: IBaseUseCaseWithInput<String, List<ChatRoom>> {}
interface IRetrieveChatConversationUseCase: IBaseUseCaseWithInput<String, List<Message>> {}
interface IRetrieveChatUserListUseCase: IBaseUseCase<List<User>>
interface ISendMessageUseCase: IBaseUseCaseWithInput<Message, Message> {}
interface ISendImageUseCase: IBaseUseCaseWithInput<ImageMsg, Message> {}

interface ICreateNewChatRoomUseCase: IBaseUseCaseWithInput<ChatRoom, ChatRoom> {}
interface IGetCharRoomDataUseCase: IBaseUseCaseWithInput<String, ChatRoom> {}
interface IRelateChatRoomMessageUseCase: IBaseUseCaseWithInput<ChatMessageReference, Boolean> {}
