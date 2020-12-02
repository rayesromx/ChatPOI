package fcfm.lmad.poi.ChatPoi.data

import fcfm.lmad.poi.ChatPoi.domain.entities.ChatRoom
import fcfm.lmad.poi.ChatPoi.domain.entities.User

object CustomSessionState {
    lateinit var loggedUser: User
    lateinit var currentChatRoom: ChatRoom
}