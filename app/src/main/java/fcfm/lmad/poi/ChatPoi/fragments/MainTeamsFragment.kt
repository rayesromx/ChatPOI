package fcfm.lmad.poi.ChatPoi.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fcfm.lmad.poi.ChatPoi.viewModels.MainTeamsViewModel
import fcfm.lmad.poi.ChatPoi.R

class MainTeamsFragment : Fragment() {

    companion object {
        fun newInstance() = MainTeamsFragment()
    }

    private lateinit var viewModel: MainTeamsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_teams_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainTeamsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}