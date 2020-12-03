package fcfm.lmad.poi.ChatPoi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import fcfm.lmad.poi.ChatPoi.adapters.PostCommentsAdapter
import fcfm.lmad.poi.ChatPoi.data.CustomSessionState
import fcfm.lmad.poi.ChatPoi.models.TeamPost
import fcfm.lmad.poi.ChatPoi.presentation.main.view.IFragmentAdmin
import kotlinx.android.synthetic.main.activity_post.*

class PostActivity : AppCompatActivity()  {

    lateinit var adapter: PostCommentsAdapter
    val modelList = mutableListOf<TeamPost>()
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        team_post_title.text = CustomSessionState.currentTeamPost.title
        team_post_time.text = CustomSessionState.currentTeamPost.time
        team_post_message.text = CustomSessionState.currentTeamPost.message

        linearLayoutManager = LinearLayoutManager(this)
        rvPost.layoutManager = linearLayoutManager

        adapter = PostCommentsAdapter(modelList)
        rvPost.adapter = adapter
    }

}