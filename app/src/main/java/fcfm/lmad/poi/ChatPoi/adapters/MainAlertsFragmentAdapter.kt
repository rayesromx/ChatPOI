package fcfm.lmad.poi.ChatPoi.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fcfm.lmad.poi.ChatPoi.presentation.main.view.IFragmentAdmin
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.models.Alert
import kotlinx.android.synthetic.main.item_view_alert.view.*

class MainAlertsFragmentAdapter(
    private val alertList: List<Alert>,
    val fragAdmin: IFragmentAdmin
) :
    RecyclerView.Adapter<MainAlertsFragmentAdapter.AlertViewHolder>() {

    inner class AlertViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(currentAlert: Alert) {
            itemView.task_name.text = currentAlert.sender
            itemView.task_description.text = currentAlert.from
            itemView.task_points.text = currentAlert.time
            itemView.task_due_date.text = currentAlert.message
            //itemView.main_alert_icon.text = currentAlert.icon
            //itemView.main_alert_image.text = currentAlert.image
            //itemView.setOnClickListener{ fragAdmin.changeFragment(MainChatsFragment(fragAdmin), "MainChatsFragment")}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlertViewHolder {
        return AlertViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_view_alert, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AlertViewHolder, position: Int) {

        holder.bindData(alertList[position])
    }

    override fun getItemCount(): Int = alertList.size
}