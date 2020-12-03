package fcfm.lmad.poi.ChatPoi.domain.entities

class Task: BaseEntity() {
    var name: String = ""
    var dueDate: String = ""
    var points:  String = ""
    var description: String = ""
    var icon: String = ""
    var teamParent: String = ""
    var createdBy: String = ""

    override fun getHastMap():HashMap<String,Any?>{
        val messageHashMap = HashMap<String, Any?>()
        messageHashMap["name"] =  name
        messageHashMap["dueDate"] = dueDate
        messageHashMap["points"] = points
        messageHashMap["description"] = description
        messageHashMap["icon"] = icon
        messageHashMap["uid"] = uid
        messageHashMap["teamParent"] = teamParent
        messageHashMap["createdBy"] =createdBy
        return messageHashMap
    }
}

class CompletedTask: BaseEntity() {
    var userUid: String = ""
    var taskIds: ArrayList<String> = ArrayList()
    override fun getHastMap():HashMap<String,Any?>{
        val messageHashMap = HashMap<String, Any?>()
        messageHashMap["userUid"] =  userUid
        messageHashMap["taskIds"] = taskIds
        messageHashMap["uid"] = uid
        return messageHashMap
    }
}

data class DisplayableTask(val task:Task, var isCompleted:Boolean)

