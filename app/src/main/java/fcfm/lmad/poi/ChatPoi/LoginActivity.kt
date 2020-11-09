package fcfm.lmad.poi.ChatPoi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Oculta el teclado para que no se abra automaticamente al iniciar el activity si esta presente un editText
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        btnLogin.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        btnSignup.setOnClickListener {

            startActivity(Intent(this, RegisterActivity::class.java))
        }


    }
}