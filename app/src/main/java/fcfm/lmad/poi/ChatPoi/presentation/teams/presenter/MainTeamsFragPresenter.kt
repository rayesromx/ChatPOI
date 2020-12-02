package fcfm.lmad.poi.ChatPoi.presentation.teams.presenter

import fcfm.lmad.poi.ChatPoi.domain.entities.TeamContainer
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack
import fcfm.lmad.poi.ChatPoi.domain.interactors.teams.IListSubteamsFromTeamUseCase
import fcfm.lmad.poi.ChatPoi.presentation.shared.presenter.BasePresenter
import fcfm.lmad.poi.ChatPoi.presentation.teams.IMainTeamsFragContract

class MainTeamsFragPresenter(
 private val listTeams: IListSubteamsFromTeamUseCase
) : BasePresenter<IMainTeamsFragContract.IView>(), IMainTeamsFragContract.IPresenter {

    override fun loadTeamList(mainTeam:String) {
        listTeams.execute(mainTeam, object: IBaseUseCaseCallBack<List<TeamContainer>> {
            override fun onSuccess(data: List<TeamContainer>?) {
                view!!.loadTeamList(data!!)
            }
            override fun onError(error: String) {
                view!!.showError(error)
            }
        })
    }
}