package fcfm.lmad.poi.ChatPoi.infrastructure.repositories

import com.google.firebase.database.DataSnapshot
import fcfm.lmad.poi.ChatPoi.domain.entities.CompletedTask
import fcfm.lmad.poi.ChatPoi.domain.entities.Task

class TaskRepository: FireBaseRepository<Task>("Tasks") {
    override fun getValue(item: DataSnapshot) = item.getValue(Task::class.java)
}

class CompletedTaskRepository: FireBaseRepository<CompletedTask>("CompletedTasks") {
    override fun getValue(item: DataSnapshot) = item.getValue(CompletedTask::class.java)
}