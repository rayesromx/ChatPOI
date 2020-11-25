package fcfm.lmad.poi.ChatPoi.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import fcfm.lmad.poi.ChatPoi.NewChatActivity
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.domain.interactors.chat.RetrieveChatUserList
import fcfm.lmad.poi.ChatPoi.domain.interactors.user.ListAllUsers
import fcfm.lmad.poi.ChatPoi.presentation.chat.IChatContract
import fcfm.lmad.poi.ChatPoi.presentation.chat.adapter.UserAdapter
import fcfm.lmad.poi.ChatPoi.presentation.chat.presenter.ChatListPresenter
import fcfm.lmad.poi.ChatPoi.presentation.shared.view.BaseFragment
import kotlinx.android.synthetic.main.main_chats_fragment.view.*

class MainChatsFragment(
    private val ctx: Context
): BaseFragment(ctx), IChatContract.IChatListFrag.IView {

    lateinit var presenter: ChatListPresenter
    lateinit var adapter: UserAdapter
    override fun getFragmentLayoutID() = R.layout.main_chats_fragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater,container, savedInstanceState)

        presenter = ChatListPresenter(
            RetrieveChatUserList(ListAllUsers())
        )
        presenter.attachView(this)
        presenter.getListOfChats()
        rootView.btn_add_new_chat.setOnClickListener{
            navigateToNewChat()
        }

        return rootView
    }

    override fun displayUsers(list: List<User>) {
        if(list.isEmpty())
            rootView.txt_no_data.visibility = View.VISIBLE
        else
            rootView.txt_no_data.visibility = View.GONE

        adapter = UserAdapter(list,false)
        rootView.rv_main_chat_frag.layoutManager = LinearLayoutManager(ctx)
        rootView.rv_main_chat_frag.adapter = adapter
    }

    override fun navigateToNewChat() {
        val intent = Intent(ctx, NewChatActivity::class.java)
        ctx.startActivity(intent)
    }
}