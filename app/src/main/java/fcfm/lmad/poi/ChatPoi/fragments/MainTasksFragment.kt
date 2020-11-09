package fcfm.lmad.poi.ChatPoi.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fcfm.lmad.poi.ChatPoi.viewModels.MainTasksViewModel
import fcfm.lmad.poi.ChatPoi.R

class MainTasksFragment : Fragment() {

    companion object {
        fun newInstance() = MainTasksFragment()
    }

    private lateinit var viewModel: MainTasksViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_tasks_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainTasksViewModel::class.java)
        // TODO: Use the ViewModel
    }

}