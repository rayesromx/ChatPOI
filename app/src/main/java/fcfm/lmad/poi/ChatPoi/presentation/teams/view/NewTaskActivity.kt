package fcfm.lmad.poi.ChatPoi.presentation.teams.view

import android.content.Intent
import android.os.Bundle
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.TaskActivity
import fcfm.lmad.poi.ChatPoi.data.CustomSessionState
import fcfm.lmad.poi.ChatPoi.domain.entities.Task
import fcfm.lmad.poi.ChatPoi.domain.interactors.tasks.CreateNewTask
import fcfm.lmad.poi.ChatPoi.infrastructure.repositories.TaskRepository
import fcfm.lmad.poi.ChatPoi.presentation.shared.view.BaseActivity
import fcfm.lmad.poi.ChatPoi.presentation.tasks.INewTaskContract
import fcfm.lmad.poi.ChatPoi.presentation.tasks.presenter.NewTaskPresenter
import kotlinx.android.synthetic.main.activity_new_task.*


class NewTaskActivity: BaseActivity(), INewTaskContract.IView {

    lateinit var presenter: NewTaskPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = NewTaskPresenter(
            CreateNewTask(TaskRepository())
        )
        presenter.attachView(this)
        btn_add_new_task.setOnClickListener{
            createTask()
        }
    }

    override fun getLayout() = R.layout.activity_new_task

    override fun createTask() {
        var task = Task()
        task.name =etxt_task_name.text.toString()
        task.dueDate =etxt_task_dueDate.text.toString()
        task.description =etxt_task_description.text.toString()
        task.points = etxt_task_points.text.toString()
        task.createdBy = CustomSessionState.loggedUser.uid
        task.teamParent = CustomSessionState.loggedUser.group
        presenter.createNewTask(task)

    }

    override fun onTaskCreated(task: Task) {
        CustomSessionState.currentTask = task
        val intent = Intent(this, TaskActivity::class.java)
        startActivity(intent)
    }

}