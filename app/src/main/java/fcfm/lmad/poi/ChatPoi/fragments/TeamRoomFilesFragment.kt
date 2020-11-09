package fcfm.lmad.poi.ChatPoi.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fcfm.lmad.poi.ChatPoi.IFragmentAdmin
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.adapters.ChatRoomFilesAdapter
import fcfm.lmad.poi.ChatPoi.adapters.TeamRoomFilesAdapter
import fcfm.lmad.poi.ChatPoi.viewModels.ChatRoomFilesViewModel
import fcfm.lmad.poi.ChatPoi.viewModels.TeamRoomFilesViewModel
import kotlinx.android.synthetic.main.chat_room_files_fragment.view.*
import kotlinx.android.synthetic.main.team_room_files_fragment.view.*

class TeamRoomFilesFragment(
    var fragAdmin: IFragmentAdmin
) : Fragment() {

    private lateinit var rootView: View
    private lateinit var viewModel: TeamRoomFilesViewModel
    lateinit var adapter: TeamRoomFilesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.team_room_files_fragment, container, false)
        initializeVM()
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initializeVM()
    }

    private fun initializeVM() {
        if (!this::viewModel.isInitialized)
        {
            viewModel = ViewModelProvider(this).get(TeamRoomFilesViewModel::class.java)
            viewModel.load()
            adapter = TeamRoomFilesAdapter(viewModel.modelList,fragAdmin)
            rootView.rvTeamRoomFilesFrag.adapter = adapter
        }
    }
}