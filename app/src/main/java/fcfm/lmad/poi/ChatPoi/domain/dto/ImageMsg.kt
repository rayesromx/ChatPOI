package fcfm.lmad.poi.ChatPoi.domain.dto

import android.net.Uri
import fcfm.lmad.poi.ChatPoi.domain.entities.Message

data class ImageMsg(
    val message: Message,
    val filePath: Uri
)