package fcfm.lmad.poi.ChatPoi.domain.entities

import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class Team: BaseEntity {
    var name:String = ""
    var group:String = ""
    var parent:String = ""

    override fun getHastMap():HashMap<String,Any?>{
        val messageHashMap = HashMap<String, Any?>()
        messageHashMap["uid"] =  uid
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