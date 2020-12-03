package fcfm.lmad.poi.ChatPoi.domain.interactors.posts

import fcfm.lmad.poi.ChatPoi.domain.IRepository
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack
import fcfm.lmad.poi.ChatPoi.infrastructure.repositories.TeamPostRepository
import fcfm.lmad.poi.ChatPoi.models.TeamPost

class CreateNewPost(
    private val repository : TeamPostRepository
) : ICreateNewPostUseCase{
    override fun execute(input: TeamPost, listener: IBaseUseCaseCallBack<TeamPost>) {
        repository.save(input, object: IRepository.IRepositoryListener<String>{
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