package fcfm.lmad.poi.ChatPoi.domain.interactors.posts

import fcfm.lmad.poi.ChatPoi.domain.IRepository
import fcfm.lmad.poi.ChatPoi.domain.entities.Team
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack
import fcfm.lmad.poi.ChatPoi.infrastructure.repositories.TeamPostRepository
import fcfm.lmad.poi.ChatPoi.models.TeamPost

class ListAllPostsFromTeam(
    private val repository : TeamPostRepository
) : IListAllPostFromTeamUseCase{
    override fun execute(input: Team, listener: IBaseUseCaseCallBack<List<TeamPost>>) {
        repository.getAll(object: IRepository.IRepositoryListener<List<TeamPost>>{
            override fun onSuccess(data: List<TeamPost>) {
                val posts = ArrayList<TeamPost>()
                for (post in data){
                    if(post.teamParentID != input.uid)
                        continue
                    posts.add(post)
                }
                listener.onSuccess(posts)
            }
            override fun onError(error: String) {
                listener.onError(error)
            }
        })
    }
}