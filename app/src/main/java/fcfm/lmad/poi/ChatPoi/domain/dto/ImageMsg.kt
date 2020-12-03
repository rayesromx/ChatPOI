package fcfm.lmad.poi.ChatPoi.domain.dto

import android.net.Uri
import fcfm.lmad.poi.ChatPoi.domain.entities.BaseEntity
import fcfm.lmad.poi.ChatPoi.domain.entities.Message

data class ImageMsg(
    val message: Message,
    val filePath: Uri,
    val fileName:String
)

class FileMsg{
    var fileName:String = ""
    var extension:String=""
    var filePath: Uri? = null
    lateinit var msg:Message

    constructor()
    constructor(name:String, filePath: Uri){
        val uri = filePath.toString()
        val extension: String = uri.substring(uri.lastIndexOf(".") + 1)
        this.fileName = name
        this.extension = extension
        this.filePath = filePath
    }

}

class Attachment : BaseEntity(){
    var fileName:String = ""
    var url:String = ""
    var parentTaskId:String = ""
    override fun getHastMap(): HashMap<String, Any?> {
        val messageHashMap = HashMap<String, Any?>()
        messageHashMap["fileName"] =  fileName
        messageHashMap["url"] = url
        messageHashMap["parentTaskId"] = parentTaskId
        messageHashMap["uid"] = uid
        return messageHashMap
    }

}