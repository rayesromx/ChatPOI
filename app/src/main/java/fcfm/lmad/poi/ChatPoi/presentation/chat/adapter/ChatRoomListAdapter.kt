package fcfm.lmad.poi.ChatPoi.presentation.chat.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.data.CustomSessionState
import fcfm.lmad.poi.ChatPoi.domain.entities.ChatRoom
import fcfm.lmad.poi.ChatPoi.presentation.chat.view.ChatRoomActivity
import kotlinx.android.synthetic.main.selectable_chat_item_view.view.*
import kotlinx.android.synthetic.main.user_search_item_view_layout.view.*

class ChatRoomListAdapter(
        private val chatRooms:List<ChatRoom>
) : RecyclerView.Adapter<ChatRoomListAdapter.ViewHolder?>() {

    var ctx: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        ctx = parent.context
        var view = LayoutInflater.from(parent.context).inflate(R.layout.user_search_item_view_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(chatRooms[position])
    }

    override fun getItemCount() = chatRooms.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(chatRoom: ChatRoom) {
            itemView.user_search_item_user_name.text = chatRoom.name
            if(chatRoom.users.size>2)
                itemView.user_search_item_profile_image.visibility = View.GONE
            else
                itemView.user_search_item_profile_image.visibility = View.VISIBLE

            //Picasso.get().load(user.profile_img).into(itemView.user_search_item_profile_image)
            itemView.setOnClickListener{
                val intent = Intent(ctx, ChatRoomActivity::class.java)
                CustomSessionState.currentChatRoom = chatRoom
                ctx?.startActivity(intent)
            }
        }
    }
}