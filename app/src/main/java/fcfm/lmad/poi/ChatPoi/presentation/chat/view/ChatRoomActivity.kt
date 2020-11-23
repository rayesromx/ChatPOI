package fcfm.lmad.poi.ChatPoi.presentation.chat.view

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.squareup.picasso.Picasso
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.domain.entities.Message
import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.domain.interactors.chat.RetrieveChatConversationInteractor
import fcfm.lmad.poi.ChatPoi.domain.interactors.chat.SendMessageInteractor
import fcfm.lmad.poi.ChatPoi.domain.interactors.user.RetrieveUserDataInteractor
import fcfm.lmad.poi.ChatPoi.fragments.*
import fcfm.lmad.poi.ChatPoi.presentation.chat.IChatContract
import fcfm.lmad.poi.ChatPoi.presentation.chat.adapter.ChatRoomChatAdapter
import fcfm.lmad.poi.ChatPoi.presentation.chat.presenter.ChatRoomPresenter
import fcfm.lmad.poi.ChatPoi.presentation.main.view.IFragmentAdmin
import fcfm.lmad.poi.ChatPoi.presentation.shared.view.BaseActivity
import kotlinx.android.synthetic.main.activity_chat_room.*

class ChatRoomActivity : BaseActivity(), IChatContract.IChatRoom.IView {

    lateinit var currentUser: User
    var userIdVisit: String = ""
    private lateinit var presenter: ChatRoomPresenter
    private lateinit var adapter: ChatRoomChatAdapter

    override  fun getLayout() = R.layout.activity_chat_room

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = ChatRoomPresenter(
            RetrieveUserDataInteractor(),
            SendMessageInteractor(),
            RetrieveChatConversationInteractor()
        )
        presenter.attachView(this)
        userIdVisit = intent.getStringExtra("visit_id")!!
        presenter.retrieveUserData(userIdVisit)
        btn_send_message.setOnClickListener{
            sendMessage()
            etxt_message_to_be_sent.setText("")
        }

        btn_attach_image.setOnClickListener{
           sendImage()
        }
        presenter.retrieveCurrentUserData()
    }

    override fun displayChatMessages(messages:List<Message>){
        adapter = ChatRoomChatAdapter(messages, currentUser,false)
        val manager = LinearLayoutManager(this)
        rv_chat_list.layoutManager = manager
        rv_chat_list.adapter = adapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 438 && resultCode == RESULT_OK && data!= null && data!!.data!=null){
            toast(this,"Se esta cargando la imagen")
            presenter.sendImage( data.data!!,userIdVisit)
            presenter.loadChatMessages(currentUser.uid,userIdVisit)
        }
    }

    override fun displayUserData(user: User) {
        txt_username.text = user.username
        Picasso.get().load(user.profile_img).into(img_user_image)
    }

    override fun displayCurrentUserData(user: User) {
        currentUser = user
        presenter.loadChatMessages(currentUser.uid,userIdVisit)
    }

    override fun sendMessage() {
        var message =  etxt_message_to_be_sent.text.toString()
        if(message.isNullOrBlank()){
            toast(this,"El mensaje no puede estar vacio")
            return
        }
        presenter.sendMessage(message,userIdVisit)
        presenter.loadChatMessages(currentUser.uid,userIdVisit)
    }

    override fun sendImage() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        startActivityForResult(Intent.createChooser(intent,"Selecciona la imagen"),438)
    }
}