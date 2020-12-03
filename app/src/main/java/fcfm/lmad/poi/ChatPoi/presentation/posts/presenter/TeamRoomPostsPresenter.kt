package fcfm.lmad.poi.ChatPoi.presentation.posts.presenter

import fcfm.lmad.poi.ChatPoi.domain.entities.Team
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack
import fcfm.lmad.poi.ChatPoi.domain.interactors.posts.IListAllPostFromTeamUseCase
import fcfm.lmad.poi.ChatPoi.models.TeamPost
import fcfm.lmad.poi.ChatPoi.presentation.shared.presenter.BasePresenter
import fcfm.lmad.poi.ChatPoi.presentation.teams.ITeamPostsContract

class TeamRoomPostsPresenter(
        private val listAllPostFromTeam: IListAllPostFromTeamUseCase
): BasePresenter<ITeamPostsContract.IView>(), ITeamPostsContract.IPresenter{

    override fun getAllPostFromTeam(team: Team) {
        listAllPostFromTeam.execute(team, object: IBaseUseCaseCallBack<List<TeamPost>> {
            override fun onSuccess(data: List<TeamPost>?) {
                if(!isViewAttached()) return
                view!!.onGetAllPostFromTeam(data!!)
            }

            override fun onError(error: String) {
                if(!isViewAttached()) return
                view!!.showError(error)
            }

        })
    }
}