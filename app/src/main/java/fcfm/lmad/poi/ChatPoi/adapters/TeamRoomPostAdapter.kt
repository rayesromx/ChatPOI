package fcfm.lmad.poi.ChatPoi.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fcfm.lmad.poi.ChatPoi.presentation.main.view.IFragmentAdmin
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.models.TeamPost
import fcfm.lmad.poi.ChatPoi.presentation.teams.ITeamPostsContract
import kotlinx.android.synthetic.main.item_view_team_post.view.*

class TeamRoomPostAdapter(
    private val teamPostsList: List<TeamPost>,
    private val parentView: ITeamPostsContract.IView
) : RecyclerView.Adapter<TeamRoomPostAdapter.TeamRoomViewHolder>() {

    inner class TeamRoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(currentPost: TeamPost) {
            itemView.team_post_title.text = currentPost.title
            itemView.team_post_time.text = currentPost.time
            itemView.team_post_message.text = currentPost.message
            //itemView.main_alert_image.text = currentAlert.image
            itemView.setOnClickListener{parentView.navigateToTeamPost(currentPost)}
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