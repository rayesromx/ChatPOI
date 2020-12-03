package fcfm.lmad.poi.ChatPoi.presentation.chat.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.data.CustomSessionState
import fcfm.lmad.poi.ChatPoi.domain.entities.ChatRoom
import fcfm.lmad.poi.ChatPoi.domain.interactors.chat.RetrieveChatRoomsOfUser
import fcfm.lmad.poi.ChatPoi.infrastructure.repositories.ChatRoomRepository
import fcfm.lmad.poi.ChatPoi.presentation.chat.IChatContract
import fcfm.lmad.poi.ChatPoi.presentation.chat.adapter.ChatRoomListAdapter
import fcfm.lmad.poi.ChatPoi.presentation.chat.presenter.ChatListPresenter
import fcfm.lmad.poi.ChatPoi.presentation.shared.view.BaseFragment
import kotlinx.android.synthetic.main.main_chats_fragment.view.*

class MainChatsFragment(
    private val ctx: Context
): BaseFragment(ctx), IChatContract.IChatListFrag.IView {

    lateinit var presenter: ChatListPresenter
    lateinit var adapter: ChatRoomListAdapter
    override fun getFragmentLayoutID() = R.layout.main_chats_fragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater,container, savedInstanceState)

        presenter = ChatListPresenter(
            RetrieveChatRoomsOfUser(ChatRoomRepository())
        )
        presenter.attachView(this)
        rootView.btn_add_new_chat.setOnClickListener{
            navigateToNewChat()
        }
        presenter.getListOfChats(CustomSessionState.loggedUser.uid)
        return rootView
    }

    override fun displayChatRooms(list: List<ChatRoom>) {
        if(list.isEmpty())
            rootView.txt_no_data.visibility = View.VISIBLE
        else
            rootView.txt_no_data.visibility = View.GONE

        adapter = ChatRoomListAdapter(list)
        rootView.rv_main_chat_frag.layoutManager = LinearLayoutManager(ctx)
        rootView.rv_main_chat_frag.adapter = adapter
    }

    override fun navigateToNewChat() {
        val intent = Intent(ctx, NewChatActivity::class.java)
        ctx.startActivity(intent)
    }


}