package fcfm.lmad.poi.ChatPoi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import fcfm.lmad.poi.ChatPoi.fragments.ChatRoomChatFragment
import fcfm.lmad.poi.ChatPoi.fragments.ChatRoomFilesFragment
import fcfm.lmad.poi.ChatPoi.fragments.TeamRoomFilesFragment
import fcfm.lmad.poi.ChatPoi.fragments.TeamRoomPostsFragment
import kotlinx.android.synthetic.main.activity_chat_room.*
import kotlinx.android.synthetic.main.activity_chat_room.chatRoomOptionTabLayout
import kotlinx.android.synthetic.main.activity_team.*

class TeamActivity: AppCompatActivity(),IFragmentAdmin  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team)

        val fragAdmin = this as IFragmentAdmin
        changeFragment(TeamRoomPostsFragment(fragAdmin), "TeamRoomPostsFragment")
        btnAddNewTeamPost.setOnClickListener{launchActivity(5)}
        teamRoomOptionTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {

                when (tab?.position) {
                    0 -> changeFragment(TeamRoomPostsFragment(fragAdmin), "TeamRoomPostsFragment")
                    1 -> changeFragment(TeamRoomFilesFragment(fragAdmin), "TeamRoomFilesFragment")
                    else -> changeFragment(TeamRoomPostsFragment(fragAdmin), "TeamRoomPostsFragment")
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    override fun changeFragment(fragment: Fragment, tag: String) {
        val currentFragment = supportFragmentManager.findFragmentByTag(tag)
        if (currentFragment == null || currentFragment.isVisible.not()) {
            supportFragmentManager.beginTransaction().replace(R.id.teamRoomFrameContainer, fragment, tag).commit()
        }
    }

    override fun launchActivity(type: Int){
        var intent : Intent = Intent(this,PostActivity::class.java)
        when(type){
            0 -> intent = Intent(this,PostActivity::class.java)
            5 -> intent = Intent(this,NewPostActivity::class.java)
        }

        startActivity(intent)
    }
}