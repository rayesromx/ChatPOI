package fcfm.lmad.poi.ChatPoi.presentation.posts.view

import android.content.Intent
import android.os.Bundle
import fcfm.lmad.poi.ChatPoi.PostActivity
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.data.CustomSessionState
import fcfm.lmad.poi.ChatPoi.domain.interactors.posts.CreateNewPost
import fcfm.lmad.poi.ChatPoi.infrastructure.repositories.TeamPostRepository
import fcfm.lmad.poi.ChatPoi.models.TeamPost
import fcfm.lmad.poi.ChatPoi.presentation.posts.presenter.NewPostPresenter
import fcfm.lmad.poi.ChatPoi.presentation.shared.view.BaseActivity
import fcfm.lmad.poi.ChatPoi.presentation.teams.INewTeamPostContract
import kotlinx.android.synthetic.main.activity_new_post.*
import java.text.SimpleDateFormat
import java.util.*

class NewPostActivity : BaseActivity(), INewTeamPostContract.IView{

    lateinit var presenter: NewPostPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = NewPostPresenter(
            CreateNewPost(TeamPostRepository())
        )

        presenter.attachView(this)
        btn_add_new_team_post.setOnClickListener{
            createNewPost()
        }
    }

    override fun getLayout() =R.layout.activity_new_post
    override fun createNewPost() {
        val post = TeamPost()
        post.senderName = CustomSessionState.loggedUser.username
        post.senderName = CustomSessionState.loggedUser.uid
        post.title = etxt_new_team_post_title.text.toString()
        post.message = etxt_new_team_post_message.text.toString()
        post.teamParentID = CustomSessionState.currentTeam.uid

        val sdf = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
        val currentDate = sdf.format(Date())
        post.time = currentDate
        presenter.createNewPost(post)
    }

    override fun onCreatedNewPost(post: TeamPost) {
        CustomSessionState.currentTeamPost = post
        val intent = Intent(this, PostActivity::class.java)
        startActivity(intent)
    }

}