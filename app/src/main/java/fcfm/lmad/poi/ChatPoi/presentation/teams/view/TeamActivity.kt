package fcfm.lmad.poi.ChatPoi.presentation.teams.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.fragments.TeamRoomFilesFragment
import fcfm.lmad.poi.ChatPoi.fragments.TeamRoomPostsFragment
import fcfm.lmad.poi.ChatPoi.presentation.posts.view.NewPostActivity
import fcfm.lmad.poi.ChatPoi.presentation.shared.view.BaseActivity
import fcfm.lmad.poi.ChatPoi.presentation.teams.ITeamContract
import fcfm.lmad.poi.ChatPoi.presentation.teams.presenter.TeamPresenter
import kotlinx.android.synthetic.main.activity_team.*

class TeamActivity: BaseActivity(), ITeamContract.IView {

    lateinit var presenter: TeamPresenter
    override fun getLayout() = R.layout.activity_team

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = TeamPresenter()
        presenter.attachView(this)

        view_pager_teams_container.adapter = ViewPager2Adapter(this)
        TabLayoutMediator(tab_layout_team_main, view_pager_teams_container) { tab, position ->
            when(position){
                0 -> {
                    tab.text ="Publicaciones"
                    tab.setIcon(R.drawable.ic_baseline_notifications_24)
                }
                1 -> {
                    tab.text = "Archivos"
                    tab.setIcon(R.drawable.ic_forum_24px)
                }
            }
            view_pager_teams_container.setCurrentItem(tab.position, true)
        }.attach()

        btnAddNewTeamPost.setOnClickListener{
            val intent = Intent(this, NewPostActivity::class.java)
            startActivity(intent)
        }
    }

    internal class ViewPager2Adapter(activity: AppCompatActivity) : FragmentStateAdapter(activity){
        private val fragments = ArrayList<Fragment>()
        init {
            fragments.add(TeamRoomPostsFragment(activity))
            fragments.add(TeamRoomFilesFragment(activity))
        }
        override fun getItemCount(): Int = fragments.size
        override fun createFragment(position: Int): Fragment = fragments[position]
    }
}