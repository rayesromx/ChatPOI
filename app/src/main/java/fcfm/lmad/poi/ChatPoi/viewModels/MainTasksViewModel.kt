package fcfm.lmad.poi.ChatPoi.viewModels

import androidx.lifecycle.ViewModel
import fcfm.lmad.poi.ChatPoi.domain.entities.Task

class MainTasksViewModel : ViewModel() {
    val modelList = mutableListOf<Task>()

    fun load() {

    }
}