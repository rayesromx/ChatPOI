package fcfm.lmad.poi.ChatPoi.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fcfm.lmad.poi.ChatPoi.IFragmentAdmin
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.models.ChatRoomMessage
import fcfm.lmad.poi.ChatPoi.models.TeamPost
import kotlinx.android.synthetic.main.item_view_alert.view.*
import kotlinx.android.synthetic.main.item_view_alert.view.task_points
import kotlinx.android.synthetic.main.item_view_chat_room_left.view.*
import kotlinx.android.synthetic.main.item_view_team_post.view.*

class TeamRoomPostAdapter(
    private val teamPostsList: List<TeamPost>,
    val fragAdmin: IFragmentAdmin
) : RecyclerView.Adapter<TeamRoomPostAdapter.TeamRoomViewHolder>() {


    inner class TeamRoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(currentPost: TeamPost) {
            itemView.team_post_title.text = currentPost.title
            itemView.team_post_time.text = currentPost.time
            itemView.team_post_message.text = currentPost.message
            //itemView.main_alert_image.text = currentAlert.image
            itemView.setOnClickListener{ fragAdmin.launchActivity(1)}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamRoomViewHolder {
        var view : View
        view = LayoutInflater.from(parent.context).inflate(R.layout.item_view_team_post, parent, false)
        return TeamRoomViewHolder(view)
    }

    override fun onBindViewHolder(holder: TeamRoomViewHolder, position: Int) {
        holder.bindData(teamPostsList[position])
    }

    override fun getItemCount(): Int = teamPostsList.size
}