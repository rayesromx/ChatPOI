package fcfm.lmad.poi.ChatPoi.fragments

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fcfm.lmad.poi.ChatPoi.IFragmentAdmin
import fcfm.lmad.poi.ChatPoi.viewModels.MainTeamsViewModel
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.adapters.CustomExpandableListAdapter
import kotlinx.android.synthetic.main.main_tasks_fragment.view.*
import kotlinx.android.synthetic.main.main_teams_fragment.view.*

class MainTeamsFragment (
      private val ctx: Context,
      val fragAdmin: IFragmentAdmin
) : Fragment() {

    private lateinit var rootView: View
    private lateinit var viewModel: MainTeamsViewModel
    lateinit var headerList : List<String>
    lateinit var itemList : HashMap<String, List<String>>
    lateinit var adapter: CustomExpandableListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.main_teams_fragment, container, false)
        swhoList()

        val adapter = CustomExpandableListAdapter(ctx,headerList,itemList,fragAdmin)
        rootView.llMainTeamsContainer.setAdapter(adapter)
        rootView.btnAddNewTeam.setOnClickListener {fragAdmin.launchActivity(7)}
        return rootView
    }

    fun swhoList()
    {
        headerList = ArrayList()
        itemList = HashMap()

        (headerList as ArrayList<String>).add("LMAD")
        (headerList as ArrayList<String>).add("LCC")

        val item1 : MutableList<String> = ArrayList()
        item1.add("General")
        item1.add("BDM")

        val item2 : MutableList<String> = ArrayList()
        item2.add("General")
        item2.add("POO")

        itemList[headerList[0]]=item1
        itemList[headerList[1]]=item2
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainTeamsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}