package fcfm.lmad.poi.ChatPoi.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fcfm.lmad.poi.ChatPoi.presentation.main.view.IFragmentAdmin
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.adapters.TeamRoomPostAdapter
import fcfm.lmad.poi.ChatPoi.viewModels.TeamPostViewModel
import kotlinx.android.synthetic.main.team_room_posts_fragment.view.*

class TeamRoomPostsFragment(
    var fragAdmin: IFragmentAdmin
) : Fragment() {

    private lateinit var rootView: View
    private lateinit var viewModel: TeamPostViewModel
    lateinit var adapter: TeamRoomPostAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.team_room_posts_fragment, container, false)
        initializeVM()
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initializeVM()
    }

    private fun initializeVM()
    {
        if (!this::viewModel.isInitialized)
        {
            viewModel = ViewModelProvider(this).get(TeamPostViewModel::class.java)
            viewModel.load()
            adapter = TeamRoomPostAdapter(viewModel.modelList,fragAdmin)
            rootView.rvTeamRoomPostsFrag.adapter = adapter
        }
    }
}