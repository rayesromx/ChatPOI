package fcfm.lmad.poi.ChatPoi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import fcfm.lmad.poi.ChatPoi.fragments.MainAlertsFragment
import fcfm.lmad.poi.ChatPoi.fragments.MainChatsFragment
import fcfm.lmad.poi.ChatPoi.fragments.MainTasksFragment
import fcfm.lmad.poi.ChatPoi.fragments.MainTeamsFragment
import kotlinx.android.synthetic.main.activity_main.*

interface IFragmentAdmin{
    fun changeFragment(fragment: Fragment, tag: String)
    fun launchActivity(type: Int)
}

class MainActivity : AppCompatActivity(),IFragmentAdmin {

    private var statusChatDemo: String = "0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var ctx = this
        val fragAdmin = this as IFragmentAdmin
        changeFragment(MainAlertsFragment(fragAdmin), "MainAlertsFragment")
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> changeFragment(MainAlertsFragment(fragAdmin), "MainAlertsFragment")
                    1 -> changeFragment(MainChatsFragment(fragAdmin), "MainChatsFragment")
                    2 -> changeFragment(MainTeamsFragment(ctx,fragAdmin), "MainTeamsFragment")
                    3 -> changeFragment(MainTasksFragment(fragAdmin), "MainTasksFragment")
                    else -> changeFragment(MainChatsFragment(fragAdmin), "MainChatsFragment")
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
            supportFragmentManager.beginTransaction().replace(R.id.frameContainer, fragment, tag).commit()
        }
    }

    override fun launchActivity(type: Int){
        var intent : Intent = Intent(this,ChatRoomActivity::class.java)
        when(type){
            0 -> intent = Intent(this,ChatRoomActivity::class.java)
            1 -> intent = Intent(this,ChatRoomActivity::class.java) //descarga de archivo
            3 -> intent = Intent(this,TaskActivity::class.java)
            5 -> intent = Intent(this,TeamActivity::class.java)
            6 -> intent = Intent(this,NewChatActivity::class.java)
            7 -> intent = Intent(this,NewSubTeamActivity::class.java)
            8 -> intent = Intent(this,NewTaskActivity::class.java)
            9-> intent = Intent(this,NewPostActivity::class.java)
        }

        intent.putExtra("dato",statusChatDemo)
        if(statusChatDemo == "1")
            statusChatDemo = "0"
        else
            statusChatDemo = "1"

        startActivity(intent)
    }
}