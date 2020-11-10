package fcfm.lmad.poi.ChatPoi.fragments

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fcfm.lmad.poi.ChatPoi.IFragmentAdmin
import fcfm.lmad.poi.ChatPoi.MainActivity
import fcfm.lmad.poi.ChatPoi.viewModels.MainChatsViewModel
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.adapters.MainAlertsFragmentAdapter
import fcfm.lmad.poi.ChatPoi.adapters.MainChatsFragmentAdapter
import fcfm.lmad.poi.ChatPoi.viewModels.MainAlertsViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.main_alerts_fragment.view.*
import kotlinx.android.synthetic.main.main_chats_fragment.*
import kotlinx.android.synthetic.main.main_chats_fragment.view.*

class MainChatsFragment(
    var fragAdmin: IFragmentAdmin
) : Fragment() {

    private lateinit var rootView: View
    lateinit var adapter: MainChatsFragmentAdapter
    private lateinit var viewModel: MainChatsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.main_chats_fragment, container, false)
        initializeVM()

        rootView.btnAddNewTeamChat.setOnClickListener {fragAdmin.launchActivity(6)}

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
            viewModel = ViewModelProvider(this).get(MainChatsViewModel::class.java)
            viewModel.load()
            adapter = MainChatsFragmentAdapter(viewModel.modelList,fragAdmin)
            rootView.rvMainChatFrag.adapter = adapter
        }
    }
}