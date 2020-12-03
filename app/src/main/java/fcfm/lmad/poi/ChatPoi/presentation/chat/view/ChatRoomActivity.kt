package fcfm.lmad.poi.ChatPoi.presentation.chat.view

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.data.CustomSessionState
import fcfm.lmad.poi.ChatPoi.domain.entities.Message
import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.domain.interactors.chat.*
import fcfm.lmad.poi.ChatPoi.domain.interactors.files.DownloadFile
import fcfm.lmad.poi.ChatPoi.domain.interactors.user.SearchUserById
import fcfm.lmad.poi.ChatPoi.infrastructure.repositories.ChatRoomRepository
import fcfm.lmad.poi.ChatPoi.infrastructure.repositories.MessageRepository
import fcfm.lmad.poi.ChatPoi.infrastructure.repositories.UserRepository
import fcfm.lmad.poi.ChatPoi.presentation.chat.IChatContract
import fcfm.lmad.poi.ChatPoi.presentation.chat.adapter.ChatRoomChatAdapter
import fcfm.lmad.poi.ChatPoi.presentation.chat.presenter.ChatRoomPresenter
import fcfm.lmad.poi.ChatPoi.presentation.shared.view.BaseActivity
import kotlinx.android.synthetic.main.activity_chat_room.*

class ChatRoomActivity : BaseActivity(), IChatContract.IChatRoom.IView {

    private lateinit var presenter: ChatRoomPresenter
    private lateinit var adapter: ChatRoomChatAdapter
    private lateinit var ctx:Context

    override  fun getLayout() = R.layout.activity_chat_room

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ctx = this
        presenter = ChatRoomPresenter(
            GetCharRoomData(ChatRoomRepository()),
            SearchUserById(UserRepository()),
            SendMessage(MessageRepository()),
            RetrieveChatConversation(MessageRepository()),
            DownloadFile(ctx),
            SendImage(MessageRepository())
        )
        presenter.attachView(this)
        btn_send_message.setOnClickListener{
            sendMessage()
            etxt_message_to_be_sent.setText("")
        }

        btn_attach_image.setOnClickListener{
           sendImage()
        }
        presenter.retrieveChatRoomData(CustomSessionState.currentChatRoom.uid)
    }

    override fun refreshChatRoomTabLayout(users:List<User>){
        presenter.chatUsers.clear()
        presenter.chatUsers.addAll(users)
        if(users.size == 2){
            var id = 0
            if(users[0].uid == CustomSessionState.loggedUser.uid)
                id = 1
            txt_username.text = CustomSessionState.currentChatRoom.name
            if(CustomSessionState.currentChatRoom.name.isBlank())
                txt_username.text = users[id].username

            Picasso.get().load(users[id].profile_img).into(img_user_image)
            img_user_image.visibility = View.VISIBLE
        }else{
            img_user_image.visibility = View.GONE
            txt_username.text = CustomSessionState.currentChatRoom.name
            if(CustomSessionState.currentChatRoom.name.isBlank())
                txt_username.text ="Chat grupal"
        }
    }

    override fun sendMessage() {
        val message =  etxt_message_to_be_sent.text.toString()
        if(message.isNullOrBlank()){
            toast(this,"El mensaje no puede estar vacio")
            return
        }
        presenter.sendMessage(message,CustomSessionState.currentChatRoom)
        presenter.loadChatMessages(CustomSessionState.currentChatRoom.uid)
    }

    override fun displayChatMessages(messages:List<Message>){
       adapter = ChatRoomChatAdapter(this,messages,
               CustomSessionState.loggedUser, presenter.chatUsers,
               CustomSessionState.currentChatRoom.users.size > 2)
        val manager = LinearLayoutManager(this)
        rv_chat_list.layoutManager = manager
        rv_chat_list.adapter = adapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var filename:String = "archivo"
        if(requestCode == 438 && resultCode == RESULT_OK && data!= null && data.data!=null){
            toast(this,"Se esta cargando la imagen")
            data.data?.let { returnUri ->
                contentResolver.query(returnUri, null, null, null, null)
            }?.use { cursor ->
                /*
                 * Get the column indexes of the data in the Cursor,
                 * move to the first row in the Cursor, get the data,
                 * and display it.
                 */
                val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                cursor.moveToFirst()
                filename = cursor.getString(nameIndex)
            }
            presenter.sendImage(filename, data.data!!,CustomSessionState.currentChatRoom.uid)
            presenter.loadChatMessages(CustomSessionState.currentChatRoom.uid)
        }
    }

    override fun displayUserData(user: User) {
        txt_username.text = user.username
        Picasso.get().load(user.profile_img).into(img_user_image)
    }

    override fun sendImage() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "*/*"
        startActivityForResult(Intent.createChooser(intent,"Selecciona el archivo"),438)
    }
    override fun startDownloadingUrl(msg:Message){
        presenter.startDownloadingUrl(msg)
    }
    override fun onDownloadFile(downloadId:Long){
        var br = object:BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                var id = intent?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
                if(id == downloadId){
                    toast(ctx,"Descarga completa")
                }
            }
        }

        registerReceiver(br, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
    }
}