package fcfm.lmad.poi.ChatPoi.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fcfm.lmad.poi.ChatPoi.presentation.main.view.IFragmentAdmin
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.domain.entities.DisplayableTask
import fcfm.lmad.poi.ChatPoi.domain.entities.Task
import fcfm.lmad.poi.ChatPoi.presentation.tasks.IMainTasksFragContract
import kotlinx.android.synthetic.main.item_view_alert.view.*

class MainTasksFragmentAdapter(
    private val taskList: List<DisplayableTask>,
    private val parentView: IMainTasksFragContract.IView
) :
    RecyclerView.Adapter<MainTasksFragmentAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(currentTask: DisplayableTask) {
            itemView.task_name.text = currentTask.task.name
            itemView.task_description.text = currentTask.task.description
            if(!currentTask.isCompleted) {
                itemView.task_points.text = currentTask.task.points
                itemView.task_due_date.text = currentTask.task.dueDate
            }
            else {
                itemView.task_points.text = "TAREA COMPLETADA"
                itemView.task_due_date.text = "Completado"
            }

            itemView.setOnClickListener{parentView.onTaskSelected(currentTask.task)}
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