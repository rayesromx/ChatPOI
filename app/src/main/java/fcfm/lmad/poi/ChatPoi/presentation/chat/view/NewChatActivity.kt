package fcfm.lmad.poi.ChatPoi.presentation.chat.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.data.CustomSessionState
import fcfm.lmad.poi.ChatPoi.presentation.chat.adapter.NewChatSelectableListAdapter
import fcfm.lmad.poi.ChatPoi.domain.dto.SelectableUser
import fcfm.lmad.poi.ChatPoi.domain.entities.ChatRoom
import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.domain.interactors.chat.CreateNewChatRoom
import fcfm.lmad.poi.ChatPoi.domain.interactors.user.ListAllUsers
import fcfm.lmad.poi.ChatPoi.infrastructure.repositories.ChatRoomRepository
import fcfm.lmad.poi.ChatPoi.infrastructure.repositories.UserRepository
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
            ListAllUsers(UserRepository()),
            CreateNewChatRoom(ChatRoomRepository())
        )
        presenter.attachView(this)
        presenter.getListOfChats()
        btn_start_chat.setOnClickListener{
            val users = ArrayList<String>()
            for (user in selectableUsers){
                if(user.isSelected)
                    users.add(user.user.uid)
            }
            users.add(CustomSessionState.loggedUser.uid)
            val chatRoom = ChatRoom()
            chatRoom.users = users

            chatRoom.name = etxt_chat_room_name.text.toString()
            if(chatRoom.name.isBlank()) {
                if (users.size > 2)
                    chatRoom.name = "Chat grupal"
                else{
                    var id = 0
                    if(users[0] == CustomSessionState.loggedUser.uid)
                        id = 1
                    for(su in selectableUsers){
                        if(su.user.uid == users[id])
                            chatRoom.name = su.user.username
                    }
                }
            }

            presenter.startNewChatRoom(chatRoom)
        }
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

    override fun startChat(chatRoom:ChatRoom){
        val intent = Intent(this, ChatRoomActivity::class.java)
        startActivity(intent)
    }


}