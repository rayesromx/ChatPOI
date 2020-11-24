package fcfm.lmad.poi.ChatPoi.presentation.teams.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.adapters.CustomExpandableListAdapter
import fcfm.lmad.poi.ChatPoi.domain.entities.TeamContainer
import fcfm.lmad.poi.ChatPoi.domain.interactors.teams.RetreiveTeamsInteractor
import fcfm.lmad.poi.ChatPoi.presentation.shared.view.BaseFragment
import fcfm.lmad.poi.ChatPoi.presentation.teams.IMainTeamsFragContract
import fcfm.lmad.poi.ChatPoi.presentation.teams.presenter.MainTeamsFragPresenter
import kotlinx.android.synthetic.main.main_teams_fragment.view.*

class MainTeamsFragment (
      private val ctx: Context
) : BaseFragment(ctx), IMainTeamsFragContract.IView {

    lateinit var adapter: CustomExpandableListAdapter
    lateinit var presenter : MainTeamsFragPresenter

    override fun getFragmentLayoutID() = R.layout.main_teams_fragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        presenter = MainTeamsFragPresenter(
            RetreiveTeamsInteractor()
        )
        presenter.attachView(this)
        presenter.loadTeamList()
        return rootView
    }

    override fun loadTeamList(teamList: List<TeamContainer>){

        var headerList = ArrayList<String>()
        var itemList = HashMap<String, List<String>>()

        for (item in teamList) {
            headerList.add(item.team!!.name)
            val subTeams : MutableList<String> = ArrayList()
            for(team in item.childTeams!!){
                subTeams.add(team.name)
            }
            itemList[item.team!!.name]=subTeams
        }
        /*
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
*/
        val adapter = CustomExpandableListAdapter(ctx,headerList,itemList)
        rootView.llMainTeamsContainer.setAdapter(adapter)
    }
}