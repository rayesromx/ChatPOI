package fcfm.lmad.poi.ChatPoi.fragments

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.adapters.TeamRoomFilesAdapter
import fcfm.lmad.poi.ChatPoi.models.TeamPost
import fcfm.lmad.poi.ChatPoi.presentation.shared.view.BaseFragment
import fcfm.lmad.poi.ChatPoi.presentation.teams.ITeamPostsContract
import fcfm.lmad.poi.ChatPoi.viewModels.TeamRoomFilesViewModel
import kotlinx.android.synthetic.main.team_room_files_fragment.view.*

class TeamRoomFilesFragment(
    private val ctx: Context
): BaseFragment(ctx){

    private lateinit var viewModel: TeamRoomFilesViewModel
    lateinit var adapter: TeamRoomFilesAdapter

    override fun getFragmentLayoutID() = R.layout.team_room_files_fragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.team_room_files_fragment, container, false)
      //  initializeVM()
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
      //  initializeVM()
    }

    private fun initializeVM() {
        if (!this::viewModel.isInitialized)
        {
            viewModel = ViewModelProvider(this).get(TeamRoomFilesViewModel::class.java)
            viewModel.load()
            //adapter = TeamRoomFilesAdapter(viewModel.modelList,fragAdmin)
            rootView.rvTeamRoomFilesFrag.adapter = adapter
        }
    }

}