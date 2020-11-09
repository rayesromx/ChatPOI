package fcfm.lmad.poi.ChatPoi.viewModels

import androidx.lifecycle.ViewModel
import fcfm.lmad.poi.ChatPoi.IFragmentAdmin
import fcfm.lmad.poi.ChatPoi.adapters.MainAlertsFragmentAdapter
import fcfm.lmad.poi.ChatPoi.models.Alert
import fcfm.lmad.poi.ChatPoi.models.AlertType

class MainAlertsViewModel() : ViewModel() {

    val modelList = mutableListOf<Alert>()

    fun load() {
        for (i in 0..10)
        {
            modelList.add(Alert("Angelica Garcia", "Grupo LMAD General", "Que tal, amigos!...$i",alertType = AlertType.ALERT_TEAMS,image = "", icon = "", time = "Hoy"))
            modelList.add(Alert("Raymundo Espinosa", "Tarea asignada", "Nueva tarea $i",alertType = AlertType.ALERT_TASKS,image = "", icon = "", time = "Hoy"))
            modelList.add(Alert("Luis Miguel", "Chat", "Hey chamo $i",alertType = AlertType.ALERT_CHAT,image = "", icon = "", time = "Hoy"))
        }
    }
}