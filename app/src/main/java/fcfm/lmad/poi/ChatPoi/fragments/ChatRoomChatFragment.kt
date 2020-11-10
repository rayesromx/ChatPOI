package fcfm.lmad.poi.ChatPoi.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fcfm.lmad.poi.ChatPoi.IFragmentAdmin
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.adapters.ChatRoomChatAdapter
import fcfm.lmad.poi.ChatPoi.adapters.MainChatsFragmentAdapter
import fcfm.lmad.poi.ChatPoi.viewModels.ChatRoomChatViewModel
import fcfm.lmad.poi.ChatPoi.viewModels.MainChatsViewModel
import kotlinx.android.synthetic.main.chat_room_chat_fragment.view.*
import kotlinx.android.synthetic.main.main_chats_fragment.view.*
import kotlinx.android.synthetic.main.main_chats_fragment.view.rvMainChatFrag

class ChatRoomChatFragment(
    var fragAdmin: IFragmentAdmin,
    private var esGrupal: Boolean
) : Fragment() {

    private lateinit var rootView: View
    private lateinit var viewModel: ChatRoomChatViewModel
    lateinit var adapter: ChatRoomChatAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.chat_room_chat_fragment, container, false)
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
            viewModel = ViewModelProvider(this).get(ChatRoomChatViewModel::class.java)
            viewModel.load(esGrupal)
            adapter = ChatRoomChatAdapter(viewModel.modelList,fragAdmin,esGrupal)
            rootView.rvChatRoomChatFrag.adapter = adapter
        }
    }
}