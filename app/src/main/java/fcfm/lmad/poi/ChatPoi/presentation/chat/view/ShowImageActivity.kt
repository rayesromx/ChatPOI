package fcfm.lmad.poi.ChatPoi.presentation.chat.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.data.CustomSessionState
import kotlinx.android.synthetic.main.activity_show_image.*

class ShowImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_image)

        Picasso.get().load(CustomSessionState.currentImage).into(image_View_large)

    }
}