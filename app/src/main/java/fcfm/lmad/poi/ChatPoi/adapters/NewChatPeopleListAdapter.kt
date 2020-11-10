package fcfm.lmad.poi.ChatPoi.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fcfm.lmad.poi.ChatPoi.IFragmentAdmin
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.models.People
import fcfm.lmad.poi.ChatPoi.models.RoomFile
import fcfm.lmad.poi.ChatPoi.models.TeamPost
import kotlinx.android.synthetic.main.item_view_chat_room_file.view.*
import kotlinx.android.synthetic.main.item_view_new_chat_people.view.*
import kotlinx.android.synthetic.main.item_view_post_comment.view.*
import kotlinx.android.synthetic.main.item_view_post_comment.view.post_from

class NewChatPeopleListAdapter(
    private val peopleList: List<People>,
    val fragAdmin: IFragmentAdmin
) : RecyclerView.Adapter<NewChatPeopleListAdapter.NewChatPeopleListViewHolder>() {


    inner class NewChatPeopleListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(currentPerson: People) {
            itemView.new_chat_people_name.text = currentPerson.name
            //itemView.new_chat_people_image.text = currentPerson.image
            //itemView.post_image.text = currentAlert.image
            itemView.setOnClickListener{ fragAdmin.launchActivity(1)}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewChatPeopleListViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_view_new_chat_people, parent, false)
        return NewChatPeopleListViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewChatPeopleListViewHolder, position: Int) {
        holder.bindData(peopleList[position])
    }

    override fun getItemCount(): Int = peopleList.size
}