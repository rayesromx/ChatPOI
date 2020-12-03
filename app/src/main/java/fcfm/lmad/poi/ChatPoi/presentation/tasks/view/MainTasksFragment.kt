package fcfm.lmad.poi.ChatPoi.presentation.tasks.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fcfm.lmad.poi.ChatPoi.presentation.teams.view.NewTaskActivity
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.adapters.MainTasksFragmentAdapter
import fcfm.lmad.poi.ChatPoi.data.CustomSessionState
import fcfm.lmad.poi.ChatPoi.domain.entities.CompletedTask
import fcfm.lmad.poi.ChatPoi.domain.entities.DisplayableTask
import fcfm.lmad.poi.ChatPoi.domain.entities.Task
import fcfm.lmad.poi.ChatPoi.domain.interactors.tasks.GetCompletedTasks
import fcfm.lmad.poi.ChatPoi.domain.interactors.tasks.GetGroupTasks
import fcfm.lmad.poi.ChatPoi.infrastructure.repositories.CompletedTaskRepository
import fcfm.lmad.poi.ChatPoi.infrastructure.repositories.TaskRepository
import fcfm.lmad.poi.ChatPoi.presentation.shared.view.BaseFragment
import fcfm.lmad.poi.ChatPoi.presentation.tasks.IMainTasksFragContract
import fcfm.lmad.poi.ChatPoi.presentation.tasks.presenter.MainTaskPresenter
import kotlinx.android.synthetic.main.main_tasks_fragment.*
import kotlinx.android.synthetic.main.main_tasks_fragment.view.*

class MainTasksFragment(
    private val ctx: Context
) : BaseFragment(ctx), IMainTasksFragContract.IView  {

    lateinit var presenter: MainTaskPresenter
    lateinit var adapter: MainTasksFragmentAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        presenter = MainTaskPresenter(
            GetGroupTasks(TaskRepository()),
            GetCompletedTasks(CompletedTaskRepository())
        )
        presenter.attachView(this)
        rootView.btnAddNewTask.setOnClickListener {
            val intent = Intent(ctx, NewTaskActivity::class.java)
            ctx.startActivity(intent)
        }
        presenter.loadTasks(CustomSessionState.loggedUser.group)
        presenter.loadCpmpletedTasks(CustomSessionState.loggedUser.uid)
        return rootView
    }

    override fun getFragmentLayoutID()=R.layout.main_tasks_fragment

    var dtask = ArrayList<DisplayableTask>()
    override fun onTasksLoaded(tasks: List<Task>) {
        dtask.clear()
        for (t in tasks)
            dtask.add(DisplayableTask(t, false))
        adapter = MainTasksFragmentAdapter(dtask,this)
        rootView.rvMainTaskFrag.adapter = adapter

        user_count_starts.text = CustomSessionState.loggedUser.stars.toString()
    }

    override fun onCompletedTasksLoaded(tasks: List<CompletedTask>) {
        if(tasks.size > 0) {
            CustomSessionState.currentCompletedTasks = tasks[0]
            for (t in dtask){
                if(CustomSessionState.currentCompletedTasks.taskIds.contains(t.task.uid))
                    t.isCompleted = true
            }

            adapter = MainTasksFragmentAdapter(dtask,this)
            rootView.rvMainTaskFrag.adapter = adapter
        }
    }

    override fun onTaskSelected(task: Task) {
        CustomSessionState.currentTask = task
        val intent = Intent(ctx, TaskActivity::class.java)
        ctx.startActivity(intent)
    }
}
