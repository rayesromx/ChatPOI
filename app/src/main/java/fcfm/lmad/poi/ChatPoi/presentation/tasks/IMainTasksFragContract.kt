package fcfm.lmad.poi.ChatPoi.presentation.tasks

import android.net.Uri
import fcfm.lmad.poi.ChatPoi.domain.dto.FileMsg
import fcfm.lmad.poi.ChatPoi.domain.entities.CompletedTask
import fcfm.lmad.poi.ChatPoi.domain.entities.Task
import fcfm.lmad.poi.ChatPoi.presentation.shared.IBasePresenter

interface IMainTasksFragContract {
    interface IView{
        fun showError(errorMsg:String)
        fun onTasksLoaded(tasks:List<Task>)
        fun onCompletedTasksLoaded(tasks:List<CompletedTask>)
        fun onTaskSelected(task:Task)
    }
    interface IPresenter: IBasePresenter<IView> {
        fun loadTasks(teamParent:String)
        fun loadCpmpletedTasks(userId:String)
    }
}


interface INewTaskContract{
    interface IView{
        fun showError(errorMsg:String)
        fun onTaskCreated(task:Task)
        fun createTask()

    }
    interface IPresenter: IBasePresenter<IView> {
        fun createNewTask(task: Task)
    }
}

interface ITaskContract{
    interface IView{
        fun showError(errorMsg:String)
        fun onTaskCompleted(task:CompletedTask)
        fun uploadFile()
    }
    interface IPresenter: IBasePresenter<IView> {
        fun setTaskAsCompleted(task:CompletedTask)
        fun sendFile(fm: FileMsg,task:Task)
        fun loadAttachments(task:Task)
        fun updateUser()
    }
}