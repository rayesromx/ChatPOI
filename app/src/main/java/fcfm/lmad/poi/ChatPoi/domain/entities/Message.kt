package fcfm.lmad.poi.ChatPoi.domain.entities

 class Message : BaseEntity() {
     var sender: String = ""
     var receiver: String= ""
     var message: String= ""
     var chat_room_id: String= ""
     var image_url: String= ""
     var is_seen: Boolean = false

     override fun getHastMap():HashMap<String,Any?>{
         val messageHashMap = HashMap<String, Any?>()
         messageHashMap["sender"] =  sender
         messageHashMap["receiver"] = receiver
         messageHashMap["message"] = message
         messageHashMap["image_url"] = image_url
         messageHashMap["is_seen"] = is_seen
         messageHashMap["uid"] = uid
         messageHashMap["chat_room_id"] = chat_room_id
         return messageHashMap
     }
 }