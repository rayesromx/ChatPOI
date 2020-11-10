package fcfm.lmad.poi.ChatPoi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import fcfm.lmad.poi.ChatPoi.adapters.NewChatPeopleListAdapter
import fcfm.lmad.poi.ChatPoi.adapters.PostCommentsAdapter
import fcfm.lmad.poi.ChatPoi.models.People
import fcfm.lmad.poi.ChatPoi.models.TeamPost
import kotlinx.android.synthetic.main.activity_new_chat.*
import kotlinx.android.synthetic.main.activity_post.*
import kotlinx.android.synthetic.main.activity_post.rvPost

class NewChatActivity : AppCompatActivity(),IFragmentAdmin {

    lateinit var adapter: NewChatPeopleListAdapter
    val modelList = mutableListOf<People>()
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_chat)

        for(i in 1..4) {
            modelList.add(People("Jorge campos", "xxx@gmail.com", ""))
            modelList.add(People("Luis Ramos", "xxx@gmail.com", ""))
            modelList.add(People("Rafael Rosas", "xxx@gmail.com", ""))
            modelList.add(People("Julian Morales", "xxx@gmail.com", ""))
            modelList.add(People("Juan Martinez", "xxx@gmail.com", ""))
            modelList.add(People("Angelica Garcia", "xxx@gmail.com", ""))
            modelList.add(People("Karla Garcia", "xxx@gmail.com", ""))
            modelList.add(People("Ruben Roman", "xxx@gmail.com", ""))
            modelList.add(People("Raul Ramon Ramirez", "xxx@gmail.com", ""))
        }
        linearLayoutManager = LinearLayoutManager(this)
        rvPeopleToChat.layoutManager = linearLayoutManager

        adapter = NewChatPeopleListAdapter(modelList,this)
        rvPeopleToChat.adapter = adapter
    }

    override fun changeFragment(fragment: Fragment, tag: String) {
        val currentFragment = supportFragmentManager.findFragmentByTag(tag)
        if (currentFragment == null || currentFragment.isVisible.not()) {
            supportFragmentManager.beginTransaction().replace(R.id.teamRoomFrameContainer, fragment, tag).commit()
        }
    }

    override fun launchActivity(type: Int){

    }
}