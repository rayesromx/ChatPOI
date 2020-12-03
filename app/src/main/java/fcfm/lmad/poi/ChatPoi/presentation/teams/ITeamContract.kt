package fcfm.lmad.poi.ChatPoi.presentation.teams

import fcfm.lmad.poi.ChatPoi.domain.entities.Team
import fcfm.lmad.poi.ChatPoi.models.TeamPost
import fcfm.lmad.poi.ChatPoi.presentation.shared.IBasePresenter

interface ITeamContract {
    interface IView{
        fun showError(errorMsg:String)
    }
    interface IPresenter: IBasePresenter<IView> {
    }
}

interface ITeamPostsContract {
    interface IView{
        fun showError(errorMsg:String)
        fun onGetAllPostFromTeam(posts:List<TeamPost>)
        fun navigateToTeamPost(teamPost:TeamPost)
    }
    interface IPresenter: IBasePresenter<IView> {
        fun getAllPostFromTeam(team: Team)
    }

}

interface INewTeamPostContract {
    interface IView{
        fun showError(errorMsg:String)
        fun createNewPost()
        fun onCreatedNewPost(post:TeamPost)
       //
    }
    interface IPresenter: IBasePresenter<IView> {
        fun createNewPost(post:TeamPost)
       //
    }
}
