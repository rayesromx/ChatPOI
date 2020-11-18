package fcfm.lmad.poi.ChatPoi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import fcfm.lmad.poi.ChatPoi.presentation.main.view.IFragmentAdmin
import fcfm.lmad.poi.ChatPoi.presentation.main.view.MainActivity

class NewSubTeamActivity : AppCompatActivity(), IFragmentAdmin {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_sub_team)
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
            0 -> intent = Intent(this, MainActivity::class.java)
        }

        startActivity(intent)
    }
}