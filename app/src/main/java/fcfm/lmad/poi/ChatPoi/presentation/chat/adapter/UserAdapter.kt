package fcfm.lmad.poi.ChatPoi.presentation.chat.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.presentation.chat.view.ChatRoomActivity
import kotlinx.android.synthetic.main.user_search_item_view_layout.view.*

class UserAdapter(
        private val userList:List<User>,
        private val isChatChecked:Boolean
) : RecyclerView.Adapter<UserAdapter.ViewHolder?>() {

    var ctx: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        ctx = parent.context
        var view = LayoutInflater.from(parent.context).inflate(R.layout.user_search_item_view_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(userList[position])
    }

    override fun getItemCount() = userList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(user: User) {
            itemView.user_search_item_user_name.text = user.username
            Picasso.get().load(user.profile_img).into(itemView.user_search_item_profile_image)
            itemView.setOnClickListener{
                val intent = Intent(ctx, ChatRoomActivity::class.java)
                intent.putExtra("visit_id",user.uid)
                ctx?.startActivity(intent)
            }
        }
    }
}