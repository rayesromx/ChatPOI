package fcfm.lmad.poi.ChatPoi.presentation.teams.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.adapters.CustomExpandableListAdapter
import fcfm.lmad.poi.ChatPoi.data.CustomSessionState
import fcfm.lmad.poi.ChatPoi.domain.entities.TeamContainer
import fcfm.lmad.poi.ChatPoi.domain.interactors.teams.ListSubteamsFromTeam
import fcfm.lmad.poi.ChatPoi.infrastructure.repositories.TeamRepository
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
            ListSubteamsFromTeam(TeamRepository())
        )
        presenter.attachView(this)
        presenter.loadTeamList(CustomSessionState.loggedUser.group)

        rootView.btn_add_new_sub_team.setOnClickListener{
            addNewSubTeam()
        }
        return rootView
    }

    override fun loadTeamList(teamList: List<TeamContainer>){
        val adapter = CustomExpandableListAdapter(ctx,teamList)
        rootView.llMainTeamsContainer.setAdapter(adapter)
    }

    override fun addNewSubTeam(){
        val intent = Intent(context, NewSubTeamActivity::class.java)
        ctx.startActivity(intent)
    }
}