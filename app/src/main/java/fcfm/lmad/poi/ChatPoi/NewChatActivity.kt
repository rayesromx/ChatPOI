package fcfm.lmad.poi.ChatPoi

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import fcfm.lmad.poi.ChatPoi.presentation.chat.adapter.NewChatSelectableListAdapter
import fcfm.lmad.poi.ChatPoi.domain.dto.SelectableUser
import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.domain.interactors.chat.RetrieveChatUserList
import fcfm.lmad.poi.ChatPoi.domain.interactors.user.ListAllUsers
import fcfm.lmad.poi.ChatPoi.presentation.chat.IChatContract
import fcfm.lmad.poi.ChatPoi.presentation.chat.presenter.NewChatPresenter
import fcfm.lmad.poi.ChatPoi.presentation.shared.view.BaseActivity
import kotlinx.android.synthetic.main.activity_new_chat.*

class NewChatActivity : BaseActivity(), IChatContract.INewChatList.IView {

    private lateinit var presenter: NewChatPresenter
    private lateinit var selectableUsers: ArrayList<SelectableUser>
    lateinit var adapter: NewChatSelectableListAdapter

    override fun getLayout() = R.layout.activity_new_chat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = NewChatPresenter(
            ListAllUsers()
        )
        presenter.attachView(this)
        presenter.getListOfChats()
    }

    override fun displayUsers(list: List<User>) {
        selectableUsers = ArrayList()
        for(user in list){
            selectableUsers.add(SelectableUser(user,false))
        }
        adapter = NewChatSelectableListAdapter(this,selectableUsers)
        rv_selectable_user_list.layoutManager = LinearLayoutManager(this)
        rv_selectable_user_list.adapter = adapter
    }
}