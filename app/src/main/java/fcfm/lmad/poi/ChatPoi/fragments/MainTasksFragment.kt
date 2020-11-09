package fcfm.lmad.poi.ChatPoi.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fcfm.lmad.poi.ChatPoi.IFragmentAdmin
import fcfm.lmad.poi.ChatPoi.viewModels.MainTasksViewModel
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.adapters.MainAlertsFragmentAdapter
import fcfm.lmad.poi.ChatPoi.adapters.MainTasksFragmentAdapter
import fcfm.lmad.poi.ChatPoi.viewModels.MainAlertsViewModel
import kotlinx.android.synthetic.main.main_alerts_fragment.view.*
import kotlinx.android.synthetic.main.main_alerts_fragment.view.rvMainAlertFrag
import kotlinx.android.synthetic.main.main_tasks_fragment.view.*

class MainTasksFragment(
    var fragAdmin: IFragmentAdmin
) : Fragment() {

    private lateinit var rootView: View
    lateinit var adapter: MainTasksFragmentAdapter
    private lateinit var viewModel: MainTasksViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.main_tasks_fragment, container, false)
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
            viewModel = ViewModelProvider(this).get(MainTasksViewModel::class.java)
            viewModel.load()
            adapter = MainTasksFragmentAdapter(viewModel.modelList,fragAdmin)
            rootView.rvMainTaskFrag.adapter = adapter
        }
    }
}
