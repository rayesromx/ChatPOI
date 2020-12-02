package fcfm.lmad.poi.ChatPoi.presentation.chat.adapter

import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fcfm.lmad.poi.ChatPoi.presentation.main.view.IFragmentAdmin
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.domain.entities.Message
import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.models.ChatRoomMessage
import kotlinx.android.synthetic.main.item_view_chat_room_left.view.*

class ChatRoomChatAdapter(
    private val chatRoomMessageList: List<Message>,
    val currentUser: User,
    val chatUsers: List<User>,
    private var esGrupal: Boolean
) : RecyclerView.Adapter<ChatRoomChatAdapter.ChatRoomViewHolder>() {

    private val MESSAGE_TYPE_LEFT = 0
    private val MESSAGE_TYPE_RIGHT = 1

    inner class ChatRoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(currentChatMessage: Message) {
            for (user in chatUsers){
                if(user.uid == currentChatMessage.sender)
                    itemView.item_view_chat_room_person.text = user.username
            }

            if(currentChatMessage.image_url.isBlank()) {
                itemView.item_view_chat_room_message.text = currentChatMessage.message
                itemView.item_view_chat_room_message.visibility = View.VISIBLE
                itemView.item_view_chat_room_image.visibility = View.GONE
            }
            else {
                Picasso.get().load(currentChatMessage.image_url)
                    .into(itemView.item_view_chat_room_image)
                itemView.item_view_chat_room_message.visibility = View.GONE
                itemView.item_view_chat_room_image.visibility = View.VISIBLE
            }
            //itemView.item_view_chat_room_messge_seen.text = currentChatMessage.time
            if(!esGrupal || currentChatMessage.sender == currentUser.uid)
                itemView.item_view_chat_room_person.visibility = View.GONE
            else
                itemView.item_view_chat_room_person.visibility = View.VISIBLE
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
        if(chatRoomMessageList[position].sender == currentUser.uid)
            return MESSAGE_TYPE_RIGHT
        return MESSAGE_TYPE_LEFT
    }

    override fun onBindViewHolder(holder: ChatRoomViewHolder, position: Int) {
        holder.bindData(chatRoomMessageList[position])
    }

    override fun getItemCount(): Int = chatRoomMessageList.size
}