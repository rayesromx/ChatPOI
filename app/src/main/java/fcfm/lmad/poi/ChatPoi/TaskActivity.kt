package fcfm.lmad.poi.ChatPoi

import android.os.Bundle
import fcfm.lmad.poi.ChatPoi.data.CustomSessionState
import fcfm.lmad.poi.ChatPoi.domain.entities.CompletedTask
import fcfm.lmad.poi.ChatPoi.domain.interactors.tasks.SetTaskAsCompletedByUser
import fcfm.lmad.poi.ChatPoi.infrastructure.repositories.CompletedTaskRepository
import fcfm.lmad.poi.ChatPoi.presentation.shared.view.BaseActivity
import fcfm.lmad.poi.ChatPoi.presentation.tasks.ITaskContract
import fcfm.lmad.poi.ChatPoi.presentation.tasks.presenter.TaskPresenter
import kotlinx.android.synthetic.main.activity_task.*



class TaskActivity : BaseActivity(), ITaskContract.IView {

    lateinit var presenter : TaskPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = TaskPresenter(
            SetTaskAsCompletedByUser(CompletedTaskRepository())
        )

        presenter.attachView(this)

        if(CustomSessionState.currentCompletedTasks.taskIds.contains(CustomSessionState.currentTask.uid))
        {
            txt_current_task_title.text = "Tarea completada"
            btn_complete_task.isEnabled = false
        }
        else {
            txt_current_task_title.text = "Detalles de la tarea"
            btn_complete_task.isEnabled = true
        }
        txt_current_task_name.text = CustomSessionState.currentTask.name
        txt_current_task_due_date.text = "Vence el ${CustomSessionState.currentTask.dueDate}"
        txt_current_task_description.text = CustomSessionState.currentTask.description


        btn_complete_task.setOnClickListener{
            CustomSessionState.currentCompletedTasks.userUid = CustomSessionState.loggedUser.uid
            CustomSessionState.currentCompletedTasks.taskIds.add(CustomSessionState.currentTask.uid)
            presenter.setTaskAsCompleted(CustomSessionState.currentCompletedTasks)
        }
    }

    override fun getLayout() = R.layout.activity_task
    override fun onTaskCompleted(task: CompletedTask) {
        CustomSessionState.loggedUser.stars += CustomSessionState.currentTask.points.toInt()
        finish()
    }
}