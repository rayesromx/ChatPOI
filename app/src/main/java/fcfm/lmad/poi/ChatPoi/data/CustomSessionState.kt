package fcfm.lmad.poi.ChatPoi.data

import fcfm.lmad.poi.ChatPoi.domain.entities.*
import fcfm.lmad.poi.ChatPoi.models.TeamPost

object CustomSessionState {
    var loggedUser: User = User()
    var currentChatRoom = ChatRoom()
    var userTeam = ""
    var currentTeam = Team()
     var currentTeamPost = TeamPost()
     var currentTask = Task()
    var currentImage:String = ""
    val docsFolder = "chatPoi/docs/"

    var currentCompletedTasks = CompletedTask()
    var canDecrypt = true

    fun encrypt(input:String) = AESUtils.encrypt(input)
    fun decrypt(input:String) :String{
        if(canDecrypt)
            return AESUtils.decrypt(input)
        return input
    }
}