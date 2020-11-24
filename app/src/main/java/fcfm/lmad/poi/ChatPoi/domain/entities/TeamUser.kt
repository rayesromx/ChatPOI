package fcfm.lmad.poi.ChatPoi.domain.entities

class TeamUser {
    var id:String = ""
    var teamId:String = ""
    var userId:String = ""

    fun getHastMap():HashMap<String,Any?>{
        val messageHashMap = HashMap<String, Any?>()
        messageHashMap["id"] =  teamId
        messageHashMap["teamId"] =  teamId
        messageHashMap["userId"] = userId
        return messageHashMap
    }
}