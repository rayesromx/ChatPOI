package fcfm.lmad.poi.ChatPoi.presentation.chat.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.squareup.picasso.Picasso
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.domain.interactors.user.RetrieveUserDataInteractor
import fcfm.lmad.poi.ChatPoi.fragments.*
import fcfm.lmad.poi.ChatPoi.presentation.chat.IChatContract
import fcfm.lmad.poi.ChatPoi.presentation.chat.presenter.ChatRoomPresenter
import fcfm.lmad.poi.ChatPoi.presentation.main.view.IFragmentAdmin
import fcfm.lmad.poi.ChatPoi.presentation.shared.view.BaseActivity
import kotlinx.android.synthetic.main.activity_chat_room.*

class ChatRoomActivity : BaseActivity(), IChatContract.IChatRoom.IView {

    var userIdVisit: String = ""
    private lateinit var presenter: ChatRoomPresenter

    override  fun getLayout() = R.layout.activity_chat_room

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = ChatRoomPresenter(
            RetrieveUserDataInteractor()
        )
        presenter.attachView(this)
        userIdVisit = intent.getStringExtra("visit_id")!!

        presenter.retrieveUserData(userIdVisit)

    }

    override fun displayUserData(user: User) {
        txt_username.text = user.username
        Picasso.get().load(user.profile_img).into(img_user_image)
    }
}