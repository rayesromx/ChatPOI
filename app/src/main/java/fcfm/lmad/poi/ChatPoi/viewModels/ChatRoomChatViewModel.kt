package fcfm.lmad.poi.ChatPoi.viewModels

import androidx.lifecycle.ViewModel
import fcfm.lmad.poi.ChatPoi.models.ChatRoomMessage

class ChatRoomChatViewModel : ViewModel() {
    val modelList = mutableListOf<ChatRoomMessage>()

    fun load(esGrupal: Boolean) {
        for (i in 0..10)
        {
            modelList.add(ChatRoomMessage("Andres Manuel", "Preggunta $i", "Que tal, amigos!...$i", time = "Hoy",isMine = 0))
            modelList.add(ChatRoomMessage("Donald Trump", "Respuesta $i", "Nueva tarea $i", time = "Hoy",isMine = 1))
            if(esGrupal)
            {
                modelList.add(ChatRoomMessage("justin trudeau", "Question $i", "Que tal, amigos!...$i", time = "Hoy",isMine = 0))
                modelList.add(ChatRoomMessage("Joe Biden", "Topic $i $i", "Nueva tarea $i", time = "Hoy",isMine = 0))
                modelList.add(ChatRoomMessage("Evo Morales", "Respuesta $i", "Nueva tarea $i", time = "Hoy",isMine = 0))
            }
        }
    }
}