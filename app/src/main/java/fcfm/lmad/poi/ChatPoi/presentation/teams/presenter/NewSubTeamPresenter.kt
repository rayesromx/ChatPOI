package fcfm.lmad.poi.ChatPoi.presentation.teams.presenter

import fcfm.lmad.poi.ChatPoi.domain.entities.Team
import fcfm.lmad.poi.ChatPoi.domain.entities.TeamContainer
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack
import fcfm.lmad.poi.ChatPoi.domain.interactors.teams.ICreateNewTeamUseCase
import fcfm.lmad.poi.ChatPoi.domain.interactors.teams.IListSubteamsFromTeamUseCase
import fcfm.lmad.poi.ChatPoi.presentation.shared.presenter.BasePresenter
import fcfm.lmad.poi.ChatPoi.presentation.teams.INewSubTeamContract

class NewSubTeamPresenter(
    private val listTeams: IListSubteamsFromTeamUseCase,
    private val createNewTeam: ICreateNewTeamUseCase

): BasePresenter<INewSubTeamContract.IView>(), INewSubTeamContract.IPresenter {

    override fun loadTeamInformation(mainTeam:String) {
        listTeams.execute(mainTeam,object: IBaseUseCaseCallBack<List<TeamContainer>> {
            override fun onSuccess(data: List<TeamContainer>?) {
                if(!isViewAttached())  return
                val team = data!![0].team
                view!!.loadTeamInformation(team!!)
            }
            override fun onError(error: String) {
                if(!isViewAttached())  return
                view!!.showError(error)
            }
        })
    }

    override fun createNewSubTeam(team: Team) {
        createNewTeam.execute(team,object: IBaseUseCaseCallBack<Team> {
            override fun onSuccess(data: Team?) {
                if(!isViewAttached())  return
                view!!.onTeamCreated(data!!)
            }

            override fun onError(error: String) {
                if(!isViewAttached())  return
                    view!!.showError(error)
            }

        })
    }
}