package fcfm.lmad.poi.ChatPoi.domain.interactors.user

import fcfm.lmad.poi.ChatPoi.domain.IRepository
import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack
import fcfm.lmad.poi.ChatPoi.infrastructure.repositories.UserRepository

class GetLoggedUser(
    private val repository: UserRepository
) :IGetLoggedUserUseCase {
    override fun execute(listener: IBaseUseCaseCallBack<User>) {
        repository.getCurrentLoggedUser(object: IRepository.IRepositoryListener<User?>{
            override fun onSuccess(data: User?) {
                listener.onSuccess(data)
            }

            override fun onError(error: String) {
                listener.onError(error)
            }
        })
    }
}