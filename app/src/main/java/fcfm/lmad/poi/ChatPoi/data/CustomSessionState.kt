package fcfm.lmad.poi.ChatPoi.data

import fcfm.lmad.poi.ChatPoi.domain.entities.*
import fcfm.lmad.poi.ChatPoi.models.TeamPost

object CustomSessionState {
    lateinit var loggedUser: User
    lateinit var currentChatRoom: ChatRoom
    lateinit var userTeam:String
    lateinit var currentTeam: Team
    lateinit var currentTeamPost: TeamPost
    lateinit var currentTask: Task
    var currentCompletedTasks = CompletedTask()
    var canDecrypt = true

    fun encrypt(input:String) = AESUtils.encrypt(input)
    fun decrypt(input:String) :String{
        if(canDecrypt)
            return AESUtils.decrypt(input)
        return input
    }
}