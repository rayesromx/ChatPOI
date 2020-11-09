package fcfm.lmad.poi.ChatPoi.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fcfm.lmad.poi.ChatPoi.IFragmentAdmin
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.fragments.MainChatsFragment
import fcfm.lmad.poi.ChatPoi.models.Alert
import kotlinx.android.synthetic.main.item_view_alert.view.*

class MainAlertsFragmentAdapter(
    private val alertList: List<Alert>,
    val fragAdmin: IFragmentAdmin
) :
    RecyclerView.Adapter<MainAlertsFragmentAdapter.AlertViewHolder>() {

    inner class AlertViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(currentAlert: Alert) {
            itemView.main_alert_sender.text = currentAlert.sender
            itemView.main_alert_from.text = currentAlert.from
            itemView.main_alert_time.text = currentAlert.time
            itemView.main_alert_message.text = currentAlert.message
            //itemView.main_alert_icon.text = currentAlert.icon
            //itemView.main_alert_image.text = currentAlert.image
            itemView.setOnClickListener{ fragAdmin.changeFragment(MainChatsFragment(fragAdmin), "MainChatsFragment")}
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