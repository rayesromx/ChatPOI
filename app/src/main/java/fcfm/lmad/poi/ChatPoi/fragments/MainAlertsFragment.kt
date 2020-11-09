package fcfm.lmad.poi.ChatPoi.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fcfm.lmad.poi.ChatPoi.IFragmentAdmin
import fcfm.lmad.poi.ChatPoi.viewModels.MainAlertsViewModel
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.adapters.MainAlertsFragmentAdapter
import kotlinx.android.synthetic.main.main_alerts_fragment.view.*

class MainAlertsFragment(
    var fragAdmin: IFragmentAdmin
) : Fragment() {

    private lateinit var rootView: View
    lateinit var adapter: MainAlertsFragmentAdapter
    private lateinit var viewModel: MainAlertsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.main_alerts_fragment, container, false)
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
            viewModel = ViewModelProvider(this).get(MainAlertsViewModel::class.java)
            viewModel.load()
            adapter = MainAlertsFragmentAdapter(viewModel.modelList,fragAdmin)
            rootView.rvMainAlertFrag.adapter = adapter
        }
    }
}