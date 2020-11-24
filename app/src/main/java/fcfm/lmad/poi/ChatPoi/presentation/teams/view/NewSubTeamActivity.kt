package fcfm.lmad.poi.ChatPoi.presentation.teams.view

import android.os.Bundle
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.domain.entities.Team
import fcfm.lmad.poi.ChatPoi.domain.interactors.teams.ListTeams
import fcfm.lmad.poi.ChatPoi.presentation.shared.view.BaseActivity
import fcfm.lmad.poi.ChatPoi.presentation.teams.INewSubTeamContract
import fcfm.lmad.poi.ChatPoi.presentation.teams.presenter.NewSubTeamPresenter
import kotlinx.android.synthetic.main.activity_new_sub_team.*

class NewSubTeamActivity : BaseActivity(), INewSubTeamContract.IView {

    lateinit var presenter: NewSubTeamPresenter
    lateinit var teamId: String
    lateinit var teamName: String

    override fun getLayout() = R.layout.activity_new_sub_team

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        teamId = intent.getStringExtra("team_id")!!
        teamName = intent.getStringExtra("team_name")!!

        presenter = NewSubTeamPresenter(
            ListTeams()
        )

        presenter.attachView(this)
        presenter.loadTeamInformation(teamId)

        txt_parent_team_name.text = teamName
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter.detachView()
    }
    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun loadTeamInformation(team: Team) {
        TODO("Not yet implemented")
    }

}