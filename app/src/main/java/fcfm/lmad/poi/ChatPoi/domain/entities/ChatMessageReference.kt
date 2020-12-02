package fcfm.lmad.poi.ChatPoi.domain.entities

class ChatMessageReference : BaseEntity(){
    var chat_room_id:String = ""
    var message_id:String= ""
    override fun getHastMap():HashMap<String,Any?>{
        val messageHashMap = HashMap<String, Any?>()
        messageHashMap["chat_room_id"] =  chat_room_id
        messageHashMap["message_id"] = message_id
        messageHashMap["uid"] = uid
        return messageHashMap
    }
}