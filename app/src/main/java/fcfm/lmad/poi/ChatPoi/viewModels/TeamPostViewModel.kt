package fcfm.lmad.poi.ChatPoi.viewModels

import androidx.lifecycle.ViewModel
import fcfm.lmad.poi.ChatPoi.models.ChatRoomMessage
import fcfm.lmad.poi.ChatPoi.models.TeamPost

class TeamPostViewModel : ViewModel() {
    val modelList = mutableListOf<TeamPost>()

    fun load() {
        for (i in 0..10)
        {
           ///modelList.add(TeamPost("Ayuda con POO", message = "Alguien sabe algo de interfaces y polimorfismo???!!!", title =  "Que tal, amigos!...$i", time = "Hoy",image = ""))
           // modelList.add(TeamPost("Mensaje a la comunidad", message = "Se les informa que la entrega del proyecto final se adelanta 2 semanas...",title =  "Nueva ", time = "Hoy",image = ""))
        }
    }
}