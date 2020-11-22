package fcfm.lmad.poi.ChatPoi.presentation.singleChat.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.domain.interactors.userInteractor.ListUsersInteractor
import fcfm.lmad.poi.ChatPoi.presentation.shared.view.BaseFragment
import fcfm.lmad.poi.ChatPoi.presentation.singleChat.ISingleChatContract
import fcfm.lmad.poi.ChatPoi.presentation.singleChat.adapter.UserAdapter
import fcfm.lmad.poi.ChatPoi.presentation.singleChat.presenter.SearchFragPresenter
import kotlinx.android.synthetic.main.fragment_chat_user_search.*
import kotlinx.android.synthetic.main.fragment_chat_user_search.view.*


class ChatUserSearchFragment(
    private val ctx: Context
): BaseFragment(ctx), ISingleChatContract.ISearchFrag.IView {

     private lateinit var adapter: UserAdapter
     private lateinit var presenter: SearchFragPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        presenter = SearchFragPresenter(
            ListUsersInteractor()
        )
        presenter.attachView(this)
        presenter.retrieveAllUsers()
        displayUserList(ArrayList())
        rootView.btn_search_user.setOnClickListener{
            searchUser(rootView.etxt_search_user.text.toString())
        }
        return rootView
    }

    override fun getFragmentLayoutID() = R.layout.fragment_chat_user_search

    override fun displayUserList(userList: List<User>) {
        adapter = UserAdapter(userList, false)
        val layoutManager = LinearLayoutManager(activity)
        rootView.rv_search_user.layoutManager = layoutManager
        rootView.rv_search_user.adapter = adapter
    }

    override fun searchUser(searchedUsername: String) {
        presenter.searchUser(searchedUsername.trim())
    }
}