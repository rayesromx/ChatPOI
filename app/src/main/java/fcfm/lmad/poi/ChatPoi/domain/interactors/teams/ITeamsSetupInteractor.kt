package fcfm.lmad.poi.ChatPoi.domain.interactors.teams

import fcfm.lmad.poi.ChatPoi.domain.entities.Team
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseInteractorCallBack

interface ITeamsSetupInteractor {
    interface ITeamsSetupInteractorCallBack: IBaseInteractorCallBack<List<Team>>{}
    fun setupTeams(listener:ITeamsSetupInteractorCallBack)
    fun createSubTeam(team:Team, listener:ITeamsSetupInteractorCallBack)
}