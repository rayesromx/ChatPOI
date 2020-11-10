package fcfm.lmad.poi.ChatPoi.viewModels

import androidx.lifecycle.ViewModel
import fcfm.lmad.poi.ChatPoi.models.Task

class MainTasksViewModel : ViewModel() {
    val modelList = mutableListOf<Task>()

    fun load() {
        for (i in 1..10)
        {
            modelList.add(Task("Laboratorio de BdM", "Vence el $i de noviembre de 2020", "10 pts",description = "Practica 3 Gpo2", icon = ""))
            modelList.add(Task("Practica $i modelado", "Vence el $i de diciembre de 2020", "5pts",description = "Practica 1 Gpo2", icon = ""))
            modelList.add(Task("Tarea $i POI", "Entregado", "10 pts",description = "Practica $i Gpo12", icon = ""))
        }
    }
}