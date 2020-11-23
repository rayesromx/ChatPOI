package fcfm.lmad.poi.ChatPoi.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import fcfm.lmad.poi.ChatPoi.BlankFragment
import fcfm.lmad.poi.ChatPoi.BlankFragment2
import fcfm.lmad.poi.ChatPoi.presentation.chat.view.ChatUserSearchFragment
import fcfm.lmad.poi.ChatPoi.viewModels.MainChatsViewModel
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.adapters.MainChatsFragmentAdapter
import fcfm.lmad.poi.ChatPoi.presentation.shared.view.BaseFragment
import kotlinx.android.synthetic.main.main_chats_fragment.view.*

class MainChatsFragment(
    private val ctx: Context
): BaseFragment(ctx) {
    lateinit var adapter: MainChatsFragmentAdapter
    private lateinit var viewModel: MainChatsViewModel

    override fun getFragmentLayoutID() = R.layout.main_chats_fragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater,container, savedInstanceState)

        rootView.view_pager_chat_container.adapter = ViewPager2Adapter(this,ctx!!)
        TabLayoutMediator(rootView.tab_layout_main_chat, rootView.view_pager_chat_container) { tab, position ->
            when(position){
                0 -> {
                    tab.text ="Chats"
                    tab.setIcon(R.drawable.ic_forum_24px)
                }
                1 -> {
                    tab.text = "Busqueda"
                    tab.setIcon(R.drawable.ic_forum_24px)
                }
            }
            rootView.view_pager_chat_container.setCurrentItem(tab.position, true)
        }.attach()
        return rootView
    }

    internal class ViewPager2Adapter(frag: Fragment,ctx:Context) : FragmentStateAdapter(frag){
        private val fragments = ArrayList<Fragment>()
        init {
            fragments.add(BlankFragment())
            fragments.add(ChatUserSearchFragment(ctx))
        }
        override fun getItemCount(): Int = fragments.size
        override fun createFragment(position: Int): Fragment = fragments[position]
    }
}