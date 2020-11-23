package fcfm.lmad.poi.ChatPoi.domain.entities

 class Message {
     var sender: String = ""
     var receiver: String= ""
     var message: String= ""
     var id: String= ""
     var image_url: String= ""
     var is_seen: Boolean = false

     fun getHastMap():HashMap<String,Any?>{
         val messageHashMap = HashMap<String, Any?>()
         messageHashMap["sender"] =  sender
         messageHashMap["receiver"] = receiver
         messageHashMap["message"] = message
         messageHashMap["image_url"] = image_url
         messageHashMap["is_seen"] = is_seen
         return messageHashMap
     }
 }