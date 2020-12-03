package fcfm.lmad.poi.ChatPoi.models

import fcfm.lmad.poi.ChatPoi.domain.entities.BaseEntity

class TeamPost: BaseEntity() {
    var senderUid: String = ""
    var senderName: String = ""
    var title: String = ""
    var time: String = ""
    var message: String = ""
    var teamParentID: String = ""

    override fun getHastMap(): HashMap<String, Any?> {
        val messageHashMap = HashMap<String, Any?>()
        messageHashMap["uid"] = uid
        messageHashMap["senderUid"] = senderUid
        messageHashMap["senderName"] = senderName
        messageHashMap["title"] = title
        messageHashMap["time"] = time
        messageHashMap["message"] = message
        messageHashMap["teamParentID"] = teamParentID
        return messageHashMap
    }
}