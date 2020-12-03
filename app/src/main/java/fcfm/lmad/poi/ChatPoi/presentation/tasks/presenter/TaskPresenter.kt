package fcfm.lmad.poi.ChatPoi.presentation.tasks.presenter

import fcfm.lmad.poi.ChatPoi.domain.entities.CompletedTask
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack
import fcfm.lmad.poi.ChatPoi.domain.interactors.tasks.SetTaskAsCompletedByUser
import fcfm.lmad.poi.ChatPoi.presentation.shared.presenter.BasePresenter
import fcfm.lmad.poi.ChatPoi.presentation.tasks.ITaskContract

class TaskPresenter(
    private val setTaskAsCompletedByUser: SetTaskAsCompletedByUser
): BasePresenter<ITaskContract.IView>(), ITaskContract.IPresenter{
    override fun setTaskAsCompleted(task: CompletedTask){
        setTaskAsCompletedByUser.execute(task, object: IBaseUseCaseCallBack<CompletedTask> {
            override fun onSuccess(data: CompletedTask?) {
                if(!isViewAttached()) return
                view!!.onTaskCompleted(data!!)
            }

            override fun onError(error: String) {
                if(!isViewAttached()) return
                view!!.showError(error)
            }

        })
    }
}