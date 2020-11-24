package fcfm.lmad.poi.ChatPoi.presentation.teams

import fcfm.lmad.poi.ChatPoi.domain.entities.TeamContainer
import fcfm.lmad.poi.ChatPoi.presentation.shared.IBasePresenter

interface IMainTeamsFragContract {
    interface IView{
        fun showError(errorMsg:String)
        fun loadTeamList(teamList: List<TeamContainer>)
    }
    interface IPresenter: IBasePresenter<IView> {
        fun loadTeamList()
    }
}