package fcfm.lmad.poi.ChatPoi.domain.entities

 class ChatRoom:BaseEntity() {
     var users:List<String> = emptyList()
     var name:String = ""

     override fun getHastMap(): HashMap<String, Any?> {
         val messageHashMap = HashMap<String, Any?>()
         messageHashMap["uid"] = uid
         messageHashMap["users"] = users
         messageHashMap["name"] = name
         return messageHashMap
     }
 }