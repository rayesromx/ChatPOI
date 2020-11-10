package fcfm.lmad.poi.ChatPoi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import fcfm.lmad.poi.ChatPoi.fragments.*
import kotlinx.android.synthetic.main.activity_chat_room.*

class ChatRoomActivity : AppCompatActivity(),IFragmentAdmin  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_room)

        var dato: String? = intent.getStringExtra("dato")
        var esGrupal: Boolean = false
        if(dato == "1")
            esGrupal = true

        if(!esGrupal) {
            chatRoomToolBarTitle.text = "Chat con {persona}"
            iconCamera.visibility = View.VISIBLE
        }
        else {
            chatRoomToolBarTitle.text = "Chat grupal"
            iconCamera.visibility = View.GONE
        }
        val fragAdmin = this as IFragmentAdmin
        changeFragment(ChatRoomChatFragment(fragAdmin,esGrupal), "ChatRoomChatFragment")

        chatRoomOptionTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {

                when (tab?.position) {
                    0 -> changeFragment(ChatRoomChatFragment(fragAdmin,esGrupal), "ChatRoomChatFragment")
                    1 -> changeFragment(ChatRoomFilesFragment(fragAdmin), "ChatRoomFilesFragment")
                    else -> changeFragment(ChatRoomChatFragment(fragAdmin,esGrupal), "ChatRoomChatFragment")
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

    override fun launchActivity(type: Int){
        Toast.makeText(this, "Abriendo archivo...", Toast.LENGTH_SHORT).show()
    }
}