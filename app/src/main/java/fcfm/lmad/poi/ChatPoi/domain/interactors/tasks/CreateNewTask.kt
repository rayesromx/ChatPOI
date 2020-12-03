package fcfm.lmad.poi.ChatPoi.domain.interactors.tasks

import fcfm.lmad.poi.ChatPoi.domain.IRepository
import fcfm.lmad.poi.ChatPoi.domain.entities.Task
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack
import fcfm.lmad.poi.ChatPoi.infrastructure.repositories.TaskRepository

class CreateNewTask(
    private val taskRepository: TaskRepository
):ICreateNewTaskUseCase {
    override fun execute(input: Task, listener: IBaseUseCaseCallBack<Task>) {
        taskRepository.save(input, object:IRepository.IRepositoryListener<String>{
            override fun onSuccess(data: String) {
                input.uid = data
                listener.onSuccess(input)
            }
            override fun onError(error: String) {
                listener.onError(error)
            }
        })
    }
}