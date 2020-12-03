package fcfm.lmad.poi.ChatPoi.presentation.teams.view

import android.content.Intent
import android.os.Bundle
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.data.CustomSessionState
import fcfm.lmad.poi.ChatPoi.domain.entities.Team
import fcfm.lmad.poi.ChatPoi.domain.interactors.teams.CreateNewTeam
import fcfm.lmad.poi.ChatPoi.domain.interactors.teams.ListSubteamsFromTeam
import fcfm.lmad.poi.ChatPoi.infrastructure.repositories.TeamRepository
import fcfm.lmad.poi.ChatPoi.presentation.shared.view.BaseActivity
import fcfm.lmad.poi.ChatPoi.presentation.teams.INewSubTeamContract
import fcfm.lmad.poi.ChatPoi.presentation.teams.presenter.NewSubTeamPresenter
import kotlinx.android.synthetic.main.activity_new_sub_team.*

class NewSubTeamActivity : BaseActivity(), INewSubTeamContract.IView {

    lateinit var presenter: NewSubTeamPresenter


    override fun getLayout() = R.layout.activity_new_sub_team

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val teamRepo = TeamRepository()
        presenter = NewSubTeamPresenter(
            ListSubteamsFromTeam(teamRepo),
            CreateNewTeam(teamRepo)
        )

        presenter.attachView(this)
        presenter.loadTeamInformation(CustomSessionState.userTeam)
        btn_add_new_sub_team.setOnClickListener{
            val team = Team(etxt_sub_team_name.text.toString())
            team.parent =  CustomSessionState.userTeam
            presenter.createNewSubTeam(team)
        }
    }

    override fun loadTeamInformation(team: Team) {
        txt_parent_team_name.text = team.name
    }

    override fun onTeamCreated(team: Team) {
        CustomSessionState.currentTeam = team
        val intent = Intent(this, TeamActivity::class.java)
        startActivity(intent)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter.detachView()
    }
    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

}