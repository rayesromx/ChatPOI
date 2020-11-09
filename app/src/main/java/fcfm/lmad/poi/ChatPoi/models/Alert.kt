package fcfm.lmad.poi.ChatPoi.models

data class Alert(
    val sender: String,
    val from: String,
    val message: String,
    val image: String,
    val time : String,
    val alertType: AlertType,
    val icon: String
)

enum class AlertType{
    ALERT_CHAT,
    ALERT_TEAMS,
    ALERT_TASKS
}