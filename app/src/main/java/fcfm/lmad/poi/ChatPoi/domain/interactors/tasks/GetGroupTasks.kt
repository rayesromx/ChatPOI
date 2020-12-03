package fcfm.lmad.poi.ChatPoi.domain.interactors.tasks

import fcfm.lmad.poi.ChatPoi.domain.IRepository
import fcfm.lmad.poi.ChatPoi.domain.entities.Task
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack
import fcfm.lmad.poi.ChatPoi.infrastructure.repositories.TaskRepository

class GetGroupTasks(
        private val taskRepository: TaskRepository
):IGetGroupTasksUseCase {
    override fun execute(input: String, listener: IBaseUseCaseCallBack<List<Task>>) {
        taskRepository.getAll(object: IRepository.IRepositoryListener<List<Task>>{
            override fun onSuccess(data: List<Task>) {
                val tasks = ArrayList<Task>()
                for (task in data){
                    if(task.teamParent!=input) continue
                    tasks.add(task)
                }
                listener.onSuccess(tasks)
            }
            override fun onError(error: String) {
                listener.onError(error)
            }
        })
    }
}