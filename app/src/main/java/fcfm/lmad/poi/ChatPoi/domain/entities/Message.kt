package fcfm.lmad.poi.ChatPoi.domain.entities

data class Message (
    val sender: String,
    val receiver: String,
    val message: String,
    val id: String,
    val image_url : String,
    val is_seen: Boolean
)