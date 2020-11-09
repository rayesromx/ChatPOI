package fcfm.lmad.poi.ChatPoi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import fcfm.lmad.poi.ChatPoi.adapters.ChatRoomChatAdapter
import fcfm.lmad.poi.ChatPoi.adapters.PostCommentsAdapter
import fcfm.lmad.poi.ChatPoi.models.ChatRoomMessage
import fcfm.lmad.poi.ChatPoi.models.TeamPost
import kotlinx.android.synthetic.main.activity_post.*

class PostActivity : AppCompatActivity() ,IFragmentAdmin{

    lateinit var adapter: PostCommentsAdapter
    val modelList = mutableListOf<TeamPost>()
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        team_post_title.text = "Duda con POO: Herencia y polimorfismo"
        team_post_time.text = "Hace 10 mins"
        team_post_message.text = "Hola,no vengo a preguntar ninguna duda respecto a conceptos específicos de la POO,si no que os venia a comentar una cosa que me pasa al programar (con POO claro) : A veces me lío y me bloqueo bastante, a ver si me podéis ayudar: Mi problema esta en que por ejemplo cuando yo creo una clase y después al hacer una instancia, es como que no se como interpretar el objeto, me explico: Yo si por ejemplo creo un objeto es como que lo tengo representar como tal, osea a no me vale decir vale creo esta, esta y esta propiedad y que tengan este, este y este comportamiento, es como que tengo que representarlo gráficamente y ahí es cuando me lió, porque ya me dirás tu como intento representar un objeto que contiene clases internas o cosas de esas que a lo mejor si se pueden intentar entenderlas gráficamente pero que en verdad me parecen una perdida de tiempo, (cuando digo gráficamente es como por ejemplo imaginarte un coche con sus propiedades, ruedas, ancho... y su comportamiento pero como si fueran imágenes). ¿Vosotros cuando creáis una clase o instanciais un objeto, lo tenéis mecanizado ya y no os coméis la cabeza, o intentáis representar el objeto de alguna otra manera? Pd: Lo siento si no entendeis bien la pregunta, pero es algo que me se hacia muy difícil de explicar."

        for(i in 1..20)
            modelList.add(TeamPost("Jorge campos","Que onda? que duda tienes?","Que onda? que duda tienes?","","HAce 2 mins"))

        linearLayoutManager = LinearLayoutManager(this)
        rvPost.layoutManager = linearLayoutManager

        adapter = PostCommentsAdapter(modelList,this)
        rvPost.adapter = adapter

    }

    override fun changeFragment(fragment: Fragment, tag: String) {
        val currentFragment = supportFragmentManager.findFragmentByTag(tag)
        if (currentFragment == null || currentFragment.isVisible.not()) {
            supportFragmentManager.beginTransaction().replace(R.id.chatRoomFrameContainer, fragment, tag).commit()
        }
    }

    override fun launchActivity(type: Int){
        Toast.makeText(this, "Abriendo archivo...", Toast.LENGTH_SHORT).show()
    }

}