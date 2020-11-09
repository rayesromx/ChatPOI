package fcfm.lmad.poi.ChatPoi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import fcfm.lmad.poi.ChatPoi.fragments.*
import kotlinx.android.synthetic.main.activity_chat_room.*
import kotlinx.android.synthetic.main.activity_main.*

class ChatRoomActivity : AppCompatActivity(),IFragmentAdmin  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_room)

        val fragAdmin = this as IFragmentAdmin
        changeFragment(ChatRoomChatFragment(fragAdmin), "ChatRoomChatFragment")

        chatRoomOptionTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {

                when (tab?.position) {
                    0 -> changeFragment(ChatRoomChatFragment(fragAdmin), "ChatRoomChatFragment")
                    1 -> changeFragment(ChatRoomFilesFragment(fragAdmin), "ChatRoomFilesFragment")
                    else -> changeFragment(ChatRoomChatFragment(fragAdmin), "ChatRoomChatFragment")
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
            supportFragmentManager.beginTransaction().replace(R.id.chatRoomFrameContainer, fragment, tag).commit()
        }
    }

    override fun launchActivity(){
        var intent = Intent(this,ChatRoomActivity::class.java)

        startActivity(intent)
    }
}