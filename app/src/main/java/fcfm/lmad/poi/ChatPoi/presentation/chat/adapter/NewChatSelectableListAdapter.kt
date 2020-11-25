package fcfm.lmad.poi.ChatPoi.presentation.chat.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.domain.dto.SelectableUser
import kotlinx.android.synthetic.main.selectable_chat_item_view.view.*

class NewChatSelectableListAdapter(
        private val ctx: Context,
        private val selectableUsers: List<SelectableUser>
) : RecyclerView.Adapter<NewChatSelectableListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(selectableUser: SelectableUser) {
            itemView.txt_selectable_username.text = selectableUser.user.username
            Picasso.get().load(selectableUser.user.profile_img).into(itemView.cimgv_selectable_profile_image)
            itemView.setOnClickListener{
                selectableUser.isSelected = !selectableUser.isSelected
                if(selectableUser.isSelected)
                    itemView.cv_selectable_chat.backgroundTintList = ContextCompat.getColorStateList(ctx,R.color.selected_item)
                else
                    itemView.cv_selectable_chat.backgroundTintList = ContextCompat.getColorStateList(ctx,R.color.unselected_item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.selectable_chat_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(selectableUsers[position])
    }

    override fun getItemCount(): Int = selectableUsers.size
}