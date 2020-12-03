package fcfm.lmad.poi.ChatPoi.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fcfm.lmad.poi.ChatPoi.presentation.main.view.IFragmentAdmin
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.domain.entities.Task
import fcfm.lmad.poi.ChatPoi.presentation.tasks.IMainTasksFragContract
import kotlinx.android.synthetic.main.item_view_alert.view.*

class MainTasksFragmentAdapter(
    private val taskList: List<Task>,
    private val parentView: IMainTasksFragContract.IView
) :
    RecyclerView.Adapter<MainTasksFragmentAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(currentTask: Task) {
            itemView.task_name.text = currentTask.name
            itemView.task_description.text = currentTask.description
            itemView.task_points.text = currentTask.points
            itemView.task_due_date.text = currentTask.dueDate
            itemView.setOnClickListener{parentView.onTaskSelected(currentTask)}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_view_task, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {

        holder.bindData(taskList[position])
    }

    override fun getItemCount(): Int = taskList.size
}