package fcfm.lmad.poi.ChatPoi.presentation.teams.presenter

import fcfm.lmad.poi.ChatPoi.domain.entities.TeamContainer
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack
import fcfm.lmad.poi.ChatPoi.domain.interactors.teams.IListTeamsUseCase
import fcfm.lmad.poi.ChatPoi.presentation.shared.presenter.BasePresenter
import fcfm.lmad.poi.ChatPoi.presentation.teams.INewSubTeamContract

class NewSubTeamPresenter(
        private val listTeams: IListTeamsUseCase
): BasePresenter<INewSubTeamContract.IView>(), INewSubTeamContract.IPresenter {

    override fun loadTeamInformation(teamId:String) {
        listTeams.execute(object: IBaseUseCaseCallBack<List<TeamContainer>> {
            override fun onSuccess(data: List<TeamContainer>?) {
                val team = data!![0].team
                view!!.loadTeamInformation(team!!)
            }
            override fun onError(error: String) {
                view!!.showError(error)
            }
        })
    }
}