package fcfm.lmad.poi.ChatPoi.viewModels

import androidx.lifecycle.ViewModel
import fcfm.lmad.poi.ChatPoi.models.RoomFile

class TeamRoomFilesViewModel : ViewModel() {
    val modelList = mutableListOf<RoomFile>()

    fun load() {
        modelList.add(RoomFile("Presentacion de PPT", "Preggunta ", "Que tal, amigos!...", time = "Hoy",image = ""))
        modelList.add(RoomFile("Diagrama de flujo", "Respuesta ", "Nueva tarea ", time = "Hoy",image = ""))
        modelList.add(RoomFile("Laboratorio de mate", "Respuesta ", "Nueva tarea ", time = "Hoy",image = ""))
        modelList.add(RoomFile("ProgramaFinal.cpp", "Respuesta ", "Nueva tarea ", time = "Hoy",image = ""))
        modelList.add(RoomFile("capturas.zip", "Respuesta ", "Nueva tarea ", time = "Hoy",image = ""))
        modelList.add(RoomFile("respuestas de examen", "Respuesta ", "Nueva tarea ", time = "Hoy",image = ""))
        modelList.add(RoomFile("Evaluacion", "Respuesta ", "Nueva tarea ", time = "Hoy",image = ""))
        modelList.add(RoomFile("rubrica de proyecto", "Respuesta ", "Nueva tarea ", time = "Hoy",image = ""))
    }
}