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
    var canDecrypt = true

    fun encrypt(input:String) = AESUtils.encrypt(input)
    fun decrypt(input:String) :String{
        if(canDecrypt)
            return AESUtils.decrypt(input)
        return input
    }
}