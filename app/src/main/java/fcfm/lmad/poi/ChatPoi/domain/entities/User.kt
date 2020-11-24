package fcfm.lmad.poi.ChatPoi.domain.entities

import android.provider.ContactsContract

class User {
     var group: String = ""
     var profile_img: String = ""
     var search: String = ""
     var status: String = ""
     var uid: String = ""
    var username: String = ""
    var email: String = ""

    constructor()
    constructor(
        group: String,
        profile_img: String,
        search: String,
        status: String,
        uid: String,
        username: String,
        email: String
    ) {
        this.group = group
        this.profile_img = profile_img
        this.search = search
        this.status = status
        this.uid = uid
        this.username = username
        this.email = email
    }

    fun getHastMap():HashMap<String,Any?>{
        val messageHashMap = HashMap<String, Any?>()
        messageHashMap["group"] =  group.toLowerCase()
        messageHashMap["profile_img"] = profile_img
        messageHashMap["search"] = username.toLowerCase()
        messageHashMap["status"] = status
        messageHashMap["uid"] = uid
        messageHashMap["username"] = username
        messageHashMap["email"] = email
        return messageHashMap
    }
}