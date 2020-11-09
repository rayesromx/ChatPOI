package fcfm.lmad.poi.ChatPoi.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fcfm.lmad.poi.ChatPoi.IFragmentAdmin
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.models.ChatRoomMessage
import kotlinx.android.synthetic.main.item_view_alert.view.*

class ChatRoomChatAdapter(
    private val chatRoomMessageList: List<ChatRoomMessage>,
    val fragAdmin: IFragmentAdmin
) : RecyclerView.Adapter<ChatRoomChatAdapter.ChatRoomViewHolder>() {

    private val MESSAGE_TYPE_LEFT = 0
    private val MESSAGE_TYPE_RIGHT = 1

    inner class ChatRoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(currentChatMessage: ChatRoomMessage) {
            itemView.chat_room_file_name.text = currentChatMessage.message
            itemView.chat_room_file_time.text = currentChatMessage.time
            //itemView.main_alert_image.text = currentAlert.image
            //itemView.setOnClickListener{ fragAdmin.launchActivity()}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatRoomViewHolder {
        var view : View
        if(viewType == MESSAGE_TYPE_LEFT)
            view = LayoutInflater.from(parent.context).inflate(R.layout.item_view_chat_room_left, parent, false)
        else
            view = LayoutInflater.from(parent.context).inflate(R.layout.item_view_chat_room_rigth, parent, false)

        return ChatRoomViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        if(chatRoomMessageList[position].isMine ==  1)
            return MESSAGE_TYPE_RIGHT
        return MESSAGE_TYPE_LEFT
    }

    override fun onBindViewHolder(holder: ChatRoomViewHolder, position: Int) {
        holder.bindData(chatRoomMessageList[position])
    }

    override fun getItemCount(): Int = chatRoomMessageList.size
}