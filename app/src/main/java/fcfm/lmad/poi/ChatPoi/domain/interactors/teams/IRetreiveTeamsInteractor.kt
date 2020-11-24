package fcfm.lmad.poi.ChatPoi.domain.interactors.teams

import fcfm.lmad.poi.ChatPoi.domain.entities.TeamContainer
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseInteractorCallBack

interface IRetreiveTeamsInteractor {
    interface IRetreiveTeamsInteractorCallBack: IBaseInteractorCallBack<List<TeamContainer>> {}
    fun retreiveTeams(listener:IRetreiveTeamsInteractorCallBack)
}