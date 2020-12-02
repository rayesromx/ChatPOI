package fcfm.lmad.poi.ChatPoi.domain.interactors.teams

import fcfm.lmad.poi.ChatPoi.domain.IRepository
import fcfm.lmad.poi.ChatPoi.domain.entities.TeamContainer
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack
import fcfm.lmad.poi.ChatPoi.infrastructure.repositories.TeamRepository

class ListSubteamsFromTeam(
    private val repository: TeamRepository,
): IListSubteamsFromTeamUseCase {
    override fun execute(input:String, listener: IBaseUseCaseCallBack<List<TeamContainer>>) {
        repository.getTeamsList(input,object: IRepository.IRepositoryListener<List<TeamContainer>>{
            override fun onSuccess(data: List<TeamContainer>) {
                listener.onSuccess(data)
            }
            override fun onError(error: String) {
                listener.onError(error)
            }
        })
    }
}