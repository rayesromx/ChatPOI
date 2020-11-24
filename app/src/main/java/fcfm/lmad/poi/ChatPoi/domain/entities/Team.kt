package fcfm.lmad.poi.ChatPoi.domain.entities

class Team {
    var id:String = ""
    var name:String = ""
    var group:String = ""
    var parent:String = ""

    fun getHastMap():HashMap<String,Any?>{
        val messageHashMap = HashMap<String, Any?>()
        messageHashMap["id"] =  id
        messageHashMap["name"] = name
        messageHashMap["group"] = group
        messageHashMap["parent"] = parent
        return messageHashMap
    }

    constructor()
    constructor(name:String){
        this.name = name
        this.group = name.toLowerCase()
    }
}

class TeamContainer{
    var team:Team? = null
    var childTeams: ArrayList<Team>? = null
}