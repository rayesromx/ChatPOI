package fcfm.lmad.poi.ChatPoi.models

data class ChatRoomMessage (
    val from: String,
    val message: String,
    val image: String,
    val time : String,
    val isMine : Int
)