package fcfm.lmad.poi.ChatPoi.data

import fcfm.lmad.poi.ChatPoi.domain.entities.ChatRoom
import fcfm.lmad.poi.ChatPoi.domain.entities.Team
import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.models.TeamPost

object CustomSessionState {
    lateinit var loggedUser: User
    lateinit var currentChatRoom: ChatRoom
    lateinit var userTeam:String
    lateinit var currentTeam: Team
    lateinit var currentTeamPost: TeamPost
}