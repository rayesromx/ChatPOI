package fcfm.lmad.poi.ChatPoi.domain.interactors.tasks

import fcfm.lmad.poi.ChatPoi.domain.IRepository
import fcfm.lmad.poi.ChatPoi.domain.entities.CompletedTask
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack
import fcfm.lmad.poi.ChatPoi.infrastructure.repositories.CompletedTaskRepository

class GetCompletedTasks(
    private val repository: CompletedTaskRepository
):ICompletedTasksUseCase {
    override fun execute(input: String, listener: IBaseUseCaseCallBack<List<CompletedTask>>) {
        repository.getAll(object:IRepository.IRepositoryListener<List<CompletedTask>>{
            override fun onSuccess(data: List<CompletedTask>) {
                val tasks = ArrayList<CompletedTask>()
                for(task in data){
                    if(task.userUid == input)
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