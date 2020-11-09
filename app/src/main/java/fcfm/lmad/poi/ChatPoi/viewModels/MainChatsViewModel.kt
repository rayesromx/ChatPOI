package fcfm.lmad.poi.ChatPoi.viewModels

import androidx.lifecycle.ViewModel
import fcfm.lmad.poi.ChatPoi.models.Alert
import fcfm.lmad.poi.ChatPoi.models.AlertType
import fcfm.lmad.poi.ChatPoi.models.Chat

class MainChatsViewModel : ViewModel() {
    val modelList = mutableListOf<Chat>()

    fun load() {
        for (i in 0..10)
        {
           modelList.add(Chat("Angelica Garcia", "Grupo LMAD General", "Que tal, amigos!...$i", time = "Hoy"))
           modelList.add(Chat("Raymundo Espinosa", "Tarea asignada", "Nueva tarea $i",time = "Hoy"))
           modelList.add(Chat("Luis Miguel", "Chat", "Hey chamo $i", time = "Hoy"))
        }
    }
}