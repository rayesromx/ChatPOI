package fcfm.lmad.poi.ChatPoi.presentation.posts.presenter

import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack
import fcfm.lmad.poi.ChatPoi.domain.interactors.posts.ICreateNewPostUseCase
import fcfm.lmad.poi.ChatPoi.models.TeamPost
import fcfm.lmad.poi.ChatPoi.presentation.shared.presenter.BasePresenter
import fcfm.lmad.poi.ChatPoi.presentation.teams.INewTeamPostContract

class NewPostPresenter(
    private val createNewPost: ICreateNewPostUseCase
)
    : BasePresenter<INewTeamPostContract.IView>(), INewTeamPostContract.IPresenter{
    override fun createNewPost(post: TeamPost) {
        createNewPost.execute(post,object: IBaseUseCaseCallBack<TeamPost> {
            override fun onSuccess(data: TeamPost?) {
                if(!isViewAttached()) return
                view!!.onCreatedNewPost(data!!)
            }

            override fun onError(error: String) {
                if(!isViewAttached()) return
                view!!.showError(error)
            }
        })
    }
}
