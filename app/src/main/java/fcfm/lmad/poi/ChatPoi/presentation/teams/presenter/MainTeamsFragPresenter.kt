package fcfm.lmad.poi.ChatPoi.presentation.teams.presenter

import fcfm.lmad.poi.ChatPoi.domain.entities.TeamContainer
import fcfm.lmad.poi.ChatPoi.domain.interactors.teams.IRetreiveTeamsInteractor
import fcfm.lmad.poi.ChatPoi.presentation.shared.presenter.BasePresenter
import fcfm.lmad.poi.ChatPoi.presentation.teams.IMainTeamsFragContract

class MainTeamsFragPresenter(
 private val retreiveTeamsInteractor: IRetreiveTeamsInteractor
) : BasePresenter<IMainTeamsFragContract.IView>(), IMainTeamsFragContract.IPresenter {
    override fun loadTeamList() {
        retreiveTeamsInteractor.retreiveTeams(object:IRetreiveTeamsInteractor.IRetreiveTeamsInteractorCallBack{
            override fun onSuccess(data: List<TeamContainer>?) {
                view!!.loadTeamList(data!!)
            }
            override fun onError(errorMessage: String) {
                view!!.showError(errorMessage)
            }
        })
    }
}