package fcfm.lmad.poi.ChatPoi.domain.interactors.user

import fcfm.lmad.poi.ChatPoi.domain.IRepository
import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack
import fcfm.lmad.poi.ChatPoi.infrastructure.repositories.FireBaseRepository

class SearchUserById(
    private val repository: FireBaseRepository<User>
) :ISearchUserByIdUseCase {
    override fun execute(input: String, listener: IBaseUseCaseCallBack<User>) {
        repository.getById(input,object:IRepository.IRepositoryListener<User?>{
            override fun onSuccess(data: User?) {
                listener.onSuccess(data)
            }

            override fun onError(error: String) {
                listener.onError(error)
            }
        })
    }
}