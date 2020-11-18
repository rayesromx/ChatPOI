package fcfm.lmad.poi.ChatPoi.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fcfm.lmad.poi.ChatPoi.presentation.main.view.IFragmentAdmin
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.adapters.ChatRoomFilesAdapter
import fcfm.lmad.poi.ChatPoi.viewModels.ChatRoomFilesViewModel
import kotlinx.android.synthetic.main.chat_room_files_fragment.view.*

class ChatRoomFilesFragment(
    var fragAdmin: IFragmentAdmin
) : Fragment() {

    private lateinit var rootView: View
    private lateinit var viewModel: ChatRoomFilesViewModel
    lateinit var adapter: ChatRoomFilesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.chat_room_files_fragment, container, false)
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
            viewModel = ViewModelProvider(this).get(ChatRoomFilesViewModel::class.java)
            viewModel.load()
            adapter = ChatRoomFilesAdapter(viewModel.modelList,fragAdmin)
            rootView.rvChatRoomFilesFrag.adapter = adapter
        }
    }
}