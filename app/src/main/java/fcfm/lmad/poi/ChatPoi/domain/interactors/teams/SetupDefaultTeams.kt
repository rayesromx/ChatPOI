package fcfm.lmad.poi.ChatPoi.domain.interactors.teams

import fcfm.lmad.poi.ChatPoi.domain.IRepository.IRepositoryListener
import fcfm.lmad.poi.ChatPoi.domain.entities.Team
import fcfm.lmad.poi.ChatPoi.domain.entities.TeamContainer
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack
import fcfm.lmad.poi.ChatPoi.infrastructure.repositories.TeamRepository

class SetupDefaultTeams(
    private val repository: TeamRepository
): ISetupDefaultTeamsUseCase {
    override fun execute(listener: IBaseUseCaseCallBack<List<TeamContainer>>) {
        for (team in getTeamsList()){
            repository.getByCustomParam("name",team.name,object: IRepositoryListener<List<Team>>{
                override fun onSuccess(data: List<Team>) {
                    if(data.isEmpty()){
                        repository.save(team,object: IRepositoryListener<String>{
                            override fun onSuccess(data: String) {
                                val subTeam = Team("General")
                                subTeam.parent = data
                                repository.save(subTeam,object:IRepositoryListener<String>{
                                    override fun onSuccess(data: String) {
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

                override fun onError(error: String) {
                    listener.onError(error)
                }
            })
        }
    }


    private fun getTeamsList():List<Team>{
        val teams = ArrayList<Team>()
        teams.add(Team("LMAD"))
        teams.add(Team("LCC"))
        teams.add(Team("LSTI"))
        teams.add(Team("LM"))
        teams.add(Team("LF"))
        teams.add(Team("LA"))
        return teams
    }
}