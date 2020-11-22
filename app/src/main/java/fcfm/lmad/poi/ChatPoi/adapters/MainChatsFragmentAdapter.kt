package fcfm.lmad.poi.ChatPoi.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fcfm.lmad.poi.ChatPoi.presentation.main.view.IFragmentAdmin
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.models.Chat
import kotlinx.android.synthetic.main.item_view_chat.view.*

class MainChatsFragmentAdapter(
    private val chatList: List<Chat>
) :
    RecyclerView.Adapter<MainChatsFragmentAdapter.ChatViewHolder>() {

    inner class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(currentChat: Chat) {
            itemView.main_chat_from.text = currentChat.from
            itemView.main_chat_time.text = currentChat.time
            itemView.main_chat_message.text = currentChat.message
            //itemView.main_chat_image.text = currentAlert.image
            //itemView.setOnClickListener{ fragAdmin.launchActivity(0)}
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