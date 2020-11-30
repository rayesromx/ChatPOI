package fcfm.lmad.poi.ChatPoi.domain.interactors.teams

import com.google.firebase.database.*
import fcfm.lmad.poi.ChatPoi.domain.IRepository.IRepositoryListener
import fcfm.lmad.poi.ChatPoi.domain.entities.Team
import fcfm.lmad.poi.ChatPoi.domain.entities.TeamContainer
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack
import fcfm.lmad.poi.ChatPoi.infrastructure.repositories.FireBaseRepository

class SetupDefaultTeams(
        private val repository: FireBaseRepository<Team>
): ISetupDefaultTeamsUseCase {
    override fun execute(listener: IBaseUseCaseCallBack<List<TeamContainer>>) {
        val dbReference = FirebaseDatabase.getInstance().reference
        val defaultTeams = getTeamsList()
        val fireBaseTeams = ArrayList<Team>()

        repository.getAll(object: IRepositoryListener<List<Team>>{
            override fun onSuccess(data: List<Team>) {
                fireBaseTeams.addAll(data)
                for (dteam in defaultTeams){
                    var exists = false
                    for(fteam in fireBaseTeams){
                        if(dteam.name == fteam.name){
                            exists = true
                            break
                        }
                    }
                    if(!exists){
                        repository.save(dteam,object: IRepositoryListener<String>{
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
            }
            override fun onError(error: String) {
                listener.onError(error)
            }
        })
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