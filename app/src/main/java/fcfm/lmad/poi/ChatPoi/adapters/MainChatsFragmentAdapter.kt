package fcfm.lmad.poi.ChatPoi.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fcfm.lmad.poi.ChatPoi.IFragmentAdmin
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.fragments.MainAlertsFragment
import fcfm.lmad.poi.ChatPoi.fragments.MainChatsFragment
import fcfm.lmad.poi.ChatPoi.models.Chat
import kotlinx.android.synthetic.main.item_view_alert.view.*

class MainChatsFragmentAdapter(
    private val chatList: List<Chat>,
    val fragAdmin: IFragmentAdmin
) :
    RecyclerView.Adapter<MainChatsFragmentAdapter.ChatViewHolder>() {

    inner class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(currentChat: Chat) {
            itemView.main_alert_from.text = currentChat.from
            itemView.main_alert_time.text = currentChat.time
            itemView.main_alert_message.text = currentChat.message
            //itemView.main_alert_image.text = currentAlert.image
            itemView.setOnClickListener{ fragAdmin.launchActivity()}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        return ChatViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_view_chat, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bindData(chatList[position])
    }

    override fun getItemCount(): Int = chatList.size
}