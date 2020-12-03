package fcfm.lmad.poi.ChatPoi.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fcfm.lmad.poi.ChatPoi.PostActivity
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.adapters.TeamRoomPostAdapter
import fcfm.lmad.poi.ChatPoi.data.CustomSessionState
import fcfm.lmad.poi.ChatPoi.domain.interactors.posts.ListAllPostsFromTeam
import fcfm.lmad.poi.ChatPoi.infrastructure.repositories.TeamPostRepository
import fcfm.lmad.poi.ChatPoi.models.TeamPost
import fcfm.lmad.poi.ChatPoi.presentation.posts.presenter.TeamRoomPostsPresenter
import fcfm.lmad.poi.ChatPoi.presentation.shared.view.BaseFragment
import fcfm.lmad.poi.ChatPoi.presentation.teams.ITeamPostsContract
import kotlinx.android.synthetic.main.team_room_posts_fragment.view.*

class TeamRoomPostsFragment(
    private val ctx: Context
): BaseFragment(ctx), ITeamPostsContract.IView{

    lateinit var presenter: TeamRoomPostsPresenter
    lateinit var adapter: TeamRoomPostAdapter

    override fun getFragmentLayoutID() = R.layout.team_room_posts_fragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.team_room_posts_fragment, container, false)
        presenter = TeamRoomPostsPresenter(
            ListAllPostsFromTeam(TeamPostRepository())
        )
        presenter.attachView(this)
        presenter.getAllPostFromTeam(CustomSessionState.currentTeam)
        return rootView
    }

    override fun onGetAllPostFromTeam(posts: List<TeamPost>) {
        adapter = TeamRoomPostAdapter(posts,this)
        rootView.rvTeamRoomPostsFrag.adapter = adapter
    }
    override fun navigateToTeamPost(teamPost:TeamPost){
        CustomSessionState.currentTeamPost = teamPost
        val intent = Intent(ctx, PostActivity::class.java)
        ctx.startActivity(intent)
    }
}