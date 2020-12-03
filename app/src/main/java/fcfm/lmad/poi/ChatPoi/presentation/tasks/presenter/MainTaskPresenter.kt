package fcfm.lmad.poi.ChatPoi.presentation.tasks.presenter

import fcfm.lmad.poi.ChatPoi.domain.entities.CompletedTask
import fcfm.lmad.poi.ChatPoi.domain.entities.Task
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack
import fcfm.lmad.poi.ChatPoi.domain.interactors.tasks.GetCompletedTasks
import fcfm.lmad.poi.ChatPoi.domain.interactors.tasks.GetGroupTasks
import fcfm.lmad.poi.ChatPoi.presentation.shared.presenter.BasePresenter
import fcfm.lmad.poi.ChatPoi.presentation.tasks.IMainTasksFragContract

class MainTaskPresenter(
    private val getGroupTasks: GetGroupTasks,
    private val getCompletedTasks: GetCompletedTasks
):BasePresenter<IMainTasksFragContract.IView>(),IMainTasksFragContract.IPresenter {
    override fun loadTasks(teamParent:String) {
        getGroupTasks.execute(teamParent, object: IBaseUseCaseCallBack<List<Task>>{
            override fun onSuccess(data: List<Task>?) {
                if(!isViewAttached()) return
                view!!.onTasksLoaded(data!!)
            }

            override fun onError(error: String) {
                if(!isViewAttached()) return
                view!!.showError(error)
            }

        })
    }

    override fun loadCpmpletedTasks(userId: String) {
        getCompletedTasks.execute(userId, object:IBaseUseCaseCallBack<List<CompletedTask>>{
            override fun onSuccess(data: List<CompletedTask>?) {
                if(!isViewAttached()) return
                view!!.onCompletedTasksLoaded(data!!)
            }

            override fun onError(error: String) {
                if(!isViewAttached()) return
                view!!.showError(error)
            }

        })
    }

}