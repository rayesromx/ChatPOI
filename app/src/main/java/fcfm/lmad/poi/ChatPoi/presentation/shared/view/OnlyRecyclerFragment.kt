package fcfm.lmad.poi.ChatPoi.presentation.shared.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.domain.interactors.chat.RetrieveChatUserList
import fcfm.lmad.poi.ChatPoi.domain.interactors.user.ListAllUsers
import fcfm.lmad.poi.ChatPoi.presentation.chat.IChatContract
import fcfm.lmad.poi.ChatPoi.presentation.chat.adapter.UserAdapter
import fcfm.lmad.poi.ChatPoi.presentation.chat.presenter.ChatListPresenter
import kotlinx.android.synthetic.main.fragment_only_recycler.view.*


class OnlyRecyclerFragment(
    private val ctx: Context
): BaseFragment(ctx), IChatContract.IChatListFrag.IView {

    lateinit var presenter: ChatListPresenter
    lateinit var adapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        presenter = ChatListPresenter(
            RetrieveChatUserList(ListAllUsers())
        )
        presenter.attachView(this)
        presenter.getChatListOfUser()
        return rootView
    }

    override fun getFragmentLayoutID(): Int  = R.layout.fragment_only_recycler

    override fun displayUsers(list: List<User>) {
        adapter = UserAdapter(list,false)
        rootView.rv_onlyRv.layoutManager = LinearLayoutManager(ctx)
        rootView.rv_onlyRv.adapter = adapter
    }
}