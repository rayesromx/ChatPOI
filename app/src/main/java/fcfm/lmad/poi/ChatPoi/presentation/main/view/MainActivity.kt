package fcfm.lmad.poi.ChatPoi.presentation.main.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import fcfm.lmad.poi.ChatPoi.presentation.shared.view.BaseActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso
import fcfm.lmad.poi.ChatPoi.*
import fcfm.lmad.poi.ChatPoi.data.CustomSessionState
import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.domain.interactors.login.GetLoggedUserData
import fcfm.lmad.poi.ChatPoi.domain.interactors.login.LogOut
import fcfm.lmad.poi.ChatPoi.domain.interactors.user.UpdateUser
import fcfm.lmad.poi.ChatPoi.fragments.MainAlertsFragment
import fcfm.lmad.poi.ChatPoi.infrastructure.repositories.UserRepository
import fcfm.lmad.poi.ChatPoi.presentation.chat.view.MainChatsFragment
import fcfm.lmad.poi.ChatPoi.presentation.login.view.LoginActivity
import fcfm.lmad.poi.ChatPoi.presentation.main.IMainContract
import fcfm.lmad.poi.ChatPoi.presentation.main.presenter.MainPresenter
import fcfm.lmad.poi.ChatPoi.presentation.tasks.view.MainTasksFragment
import fcfm.lmad.poi.ChatPoi.presentation.teams.view.MainTeamsFragment
import kotlinx.android.synthetic.main.activity_main.*

interface IFragmentAdmin{
    fun launchActivity(type: Int)
}

class MainActivity : BaseActivity(), IMainContract.IMainView {

    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = MainPresenter(
            LogOut(),
            GetLoggedUserData(),
            UpdateUser(UserRepository())
        )

        presenter.attachView(this)

        view_pager_container.adapter =  ViewPager2Adapter(this)
        TabLayoutMediator(tab_layout_main, view_pager_container) { tab, position ->
            when(position){
                0 -> {
                    tab.text ="Alertas"
                    tab.setIcon(R.drawable.ic_baseline_notifications_24)
                }
                1 -> {
                    tab.text = "Chats"
                    tab.setIcon(R.drawable.ic_forum_24px)
                }
                2 -> {
                    tab.text ="Grupos"
                    tab.setIcon(R.drawable.ic_people_black_18dp)
                }
                3 -> {
                    tab.text ="Tareas"
                    tab.setIcon(R.drawable.ic_baseline_work_24)
                }
            }
            view_pager_container.setCurrentItem(tab.position, true)
        }.attach()

        btn_main_logout.setOnClickListener{
            logOut()
        }
        img_user_image.setOnClickListener{
            CustomSessionState.canDecrypt = !CustomSessionState.canDecrypt
        }
        presenter.refreshUserData()
    }

    override fun getLayout() = R.layout.activity_main

    override fun refreshUserData(user: User?) {
        txt_username.text = user!!.username
        Picasso.get().load(user.profile_img).into(img_user_image)
    }

    override fun logOut() {
        presenter.logOut()
        var intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    internal class ViewPager2Adapter(activity:AppCompatActivity) : FragmentStateAdapter(activity){
        private val fragments = ArrayList<Fragment>()
        init {
            fragments.add(MainAlertsFragment())
            fragments.add(MainChatsFragment(activity))
            fragments.add(MainTeamsFragment(activity))
            fragments.add(MainTasksFragment(activity))
        }
        override fun getItemCount(): Int = fragments.size
        override fun createFragment(position: Int): Fragment = fragments[position]
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