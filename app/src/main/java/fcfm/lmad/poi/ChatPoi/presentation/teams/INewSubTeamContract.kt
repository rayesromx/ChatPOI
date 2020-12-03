package fcfm.lmad.poi.ChatPoi.presentation.teams

import fcfm.lmad.poi.ChatPoi.domain.entities.Team
import fcfm.lmad.poi.ChatPoi.presentation.shared.IBasePresenter

interface INewSubTeamContract {
    interface IView{
        fun showError(errorMsg:String)
        fun loadTeamInformation(team: Team)
        fun onTeamCreated(team:Team)
    }
    interface IPresenter: IBasePresenter<IView> {
        fun loadTeamInformation(mainTeam:String)
        fun createNewSubTeam(team: Team)
    }
}