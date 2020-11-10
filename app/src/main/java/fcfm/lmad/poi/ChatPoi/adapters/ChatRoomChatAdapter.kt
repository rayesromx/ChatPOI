package fcfm.lmad.poi.ChatPoi.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fcfm.lmad.poi.ChatPoi.IFragmentAdmin
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.models.ChatRoomMessage
import kotlinx.android.synthetic.main.activity_chat_room.view.*
import kotlinx.android.synthetic.main.item_view_alert.view.*
import kotlinx.android.synthetic.main.item_view_alert.view.task_points
import kotlinx.android.synthetic.main.item_view_chat_room_left.view.*

class ChatRoomChatAdapter(
    private val chatRoomMessageList: List<ChatRoomMessage>,
    val fragAdmin: IFragmentAdmin,
    private var esGrupal: Boolean
) : RecyclerView.Adapter<ChatRoomChatAdapter.ChatRoomViewHolder>() {

    private val MESSAGE_TYPE_LEFT = 0
    private val MESSAGE_TYPE_RIGHT = 1

    inner class ChatRoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(currentChatMessage: ChatRoomMessage) {
            itemView.item_view_chat_room_person.text = currentChatMessage.from
            itemView.item_view_chat_room_message.text = currentChatMessage.message
            itemView.item_view_chat_room_messge_time.text = currentChatMessage.time
            if(!esGrupal) {
                itemView.item_view_chat_room_person.visibility = View.GONE
            }

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