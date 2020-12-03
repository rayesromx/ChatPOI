package fcfm.lmad.poi.ChatPoi.domain.interactors.teams

import fcfm.lmad.poi.ChatPoi.domain.IRepository
import fcfm.lmad.poi.ChatPoi.domain.entities.Team
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack
import fcfm.lmad.poi.ChatPoi.infrastructure.repositories.TeamRepository

class CreateNewTeam(
        private val repository: TeamRepository
):ICreateNewTeamUseCase {
    override fun execute(input: Team, listener: IBaseUseCaseCallBack<Team>) {
        repository.getByCustomParam("group",input.parent,object:IRepository.IRepositoryListener<List<Team>>{
            override fun onSuccess(data: List<Team>) {
                for (team in data){
                    if(team.group != input.parent)
                        continue
                    input.parent = team.uid
                    break
                }
                if(input.parent.isBlank())
                    listener.onError("No se encontro la carrera del usuario")
                else
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

            override fun onError(error: String) {
                listener.onError(error)
            }

        })


    }
}