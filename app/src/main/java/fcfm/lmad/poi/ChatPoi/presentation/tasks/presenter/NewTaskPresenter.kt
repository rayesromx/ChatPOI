package fcfm.lmad.poi.ChatPoi.presentation.tasks.presenter

import fcfm.lmad.poi.ChatPoi.domain.entities.Task
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack
import fcfm.lmad.poi.ChatPoi.domain.interactors.tasks.CreateNewTask
import fcfm.lmad.poi.ChatPoi.presentation.shared.presenter.BasePresenter
import fcfm.lmad.poi.ChatPoi.presentation.tasks.INewTaskContract

class NewTaskPresenter(
    private val createNewTask: CreateNewTask
): BasePresenter<INewTaskContract.IView>(),  INewTaskContract.IPresenter{
    override fun createNewTask(task: Task) {
        createNewTask.execute(task, object: IBaseUseCaseCallBack<Task> {
            override fun onSuccess(data: Task?) {
                if(!isViewAttached()) return
                view!!.onTaskCreated(data!!)
            }

            override fun onError(error: String) {
                if(!isViewAttached()) return
                view!!.showError(error)
            }
        })
    }

}
