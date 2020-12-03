package fcfm.lmad.poi.ChatPoi.infrastructure.repositories

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import fcfm.lmad.poi.ChatPoi.data.CustomSessionState
import fcfm.lmad.poi.ChatPoi.domain.IRepository
import fcfm.lmad.poi.ChatPoi.domain.dto.Attachment
import fcfm.lmad.poi.ChatPoi.domain.entities.CompletedTask
import fcfm.lmad.poi.ChatPoi.domain.entities.Task

class TaskRepository: FireBaseRepository<Task>("Tasks") {
    override fun getValue(item: DataSnapshot) = item.getValue(Task::class.java)
}

class CompletedTaskRepository: FireBaseRepository<CompletedTask>("CompletedTasks") {
    override fun getValue(item: DataSnapshot) = item.getValue(CompletedTask::class.java)

    override fun getAll(listener: IRepository.IRepositoryListener<List<CompletedTask>>) {
        getTableRef().addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val models = ArrayList<CompletedTask>()
                for (model in snapshot.children){
                    val m = getValue(model)!!
                    if(m.userUid == CustomSessionState.loggedUser.uid)
                    models.add(m)
                }
                listener.onSuccess(models)
            }
            override fun onCancelled(error: DatabaseError) {
                listener.onError(error.message)
            }
        })
    }
}

class AttachmentsRepository: FireBaseRepository<Attachment>("Attachments") {
    override fun getValue(item: DataSnapshot) = item.getValue(Attachment::class.java)
}