package fcfm.lmad.poi.ChatPoi.viewModels

import androidx.lifecycle.ViewModel
import fcfm.lmad.poi.ChatPoi.models.ChatRoomFile

class ChatRoomFilesViewModel : ViewModel() {
    val modelList = mutableListOf<ChatRoomFile>()

    fun load() {
        modelList.add(ChatRoomFile("Presentacion de PPT", "Preggunta ", "Que tal, amigos!...", time = "Hoy",image = ""))
        modelList.add(ChatRoomFile("Diagrama de flujo", "Respuesta ", "Nueva tarea ", time = "Hoy",image = ""))
        modelList.add(ChatRoomFile("Laboratorio de mate", "Respuesta ", "Nueva tarea ", time = "Hoy",image = ""))
        modelList.add(ChatRoomFile("ProgramaFinal.cpp", "Respuesta ", "Nueva tarea ", time = "Hoy",image = ""))
        modelList.add(ChatRoomFile("capturas.zip", "Respuesta ", "Nueva tarea ", time = "Hoy",image = ""))
        modelList.add(ChatRoomFile("respuestas de examen", "Respuesta ", "Nueva tarea ", time = "Hoy",image = ""))
        modelList.add(ChatRoomFile("Evaluacion", "Respuesta ", "Nueva tarea ", time = "Hoy",image = ""))
        modelList.add(ChatRoomFile("rubrica de proyecto", "Respuesta ", "Nueva tarea ", time = "Hoy",image = ""))
    }
}