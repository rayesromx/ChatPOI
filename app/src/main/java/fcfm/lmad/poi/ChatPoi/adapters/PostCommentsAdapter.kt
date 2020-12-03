package fcfm.lmad.poi.ChatPoi.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fcfm.lmad.poi.ChatPoi.presentation.main.view.IFragmentAdmin
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.models.TeamPost
import kotlinx.android.synthetic.main.item_view_post_comment.view.*

class PostCommentsAdapter(
    private val commentList: List<TeamPost>,
    val fragAdmin: IFragmentAdmin
) : RecyclerView.Adapter<PostCommentsAdapter.PostCommentViewHolder>() {


    inner class PostCommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(currentPost: TeamPost) {
            itemView.post_from.text = currentPost.senderName
            itemView.post_message.text = currentPost.message
            itemView.post_time.text = currentPost.time
            //itemView.post_image.text = currentAlert.image
            itemView.setOnClickListener{ fragAdmin.launchActivity(1)}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostCommentViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_view_post_comment, parent, false)
        return PostCommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostCommentViewHolder, position: Int) {
        holder.bindData(commentList[position])
    }

    override fun getItemCount(): Int = commentList.size
}