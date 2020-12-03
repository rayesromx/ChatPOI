package fcfm.lmad.poi.ChatPoi.domain.interactors.tasks

import fcfm.lmad.poi.ChatPoi.domain.IRepository
import fcfm.lmad.poi.ChatPoi.domain.entities.CompletedTask
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack
import fcfm.lmad.poi.ChatPoi.infrastructure.repositories.CompletedTaskRepository

class SetTaskAsCompletedByUser(
        private val repository: CompletedTaskRepository
):ISetTaskAsCompletedByUserUseCase {
    override fun execute(input: CompletedTask, listener: IBaseUseCaseCallBack<CompletedTask>) {
        repository.save(input, object: IRepository.IRepositoryListener<String> {
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