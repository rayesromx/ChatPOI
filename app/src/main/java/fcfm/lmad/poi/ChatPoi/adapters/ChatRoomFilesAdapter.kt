package fcfm.lmad.poi.ChatPoi.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fcfm.lmad.poi.ChatPoi.IFragmentAdmin
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.models.ChatRoomFile
import fcfm.lmad.poi.ChatPoi.models.ChatRoomMessage
import kotlinx.android.synthetic.main.item_view_alert.view.*

class ChatRoomFilesAdapter(
    private val chatRoomFileList: List<ChatRoomFile>,
    val fragAdmin: IFragmentAdmin
) : RecyclerView.Adapter<ChatRoomFilesAdapter.ChatRoomViewHolder>() {


    inner class ChatRoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(currentFile: ChatRoomFile) {
            itemView.chat_room_file_name.text = currentFile.name
            itemView.chat_room_file_time.text = currentFile.time
            //itemView.main_alert_image.text = currentAlert.image
            //itemView.setOnClickListener{ fragAdmin.launchActivity()}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatRoomViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_view_chat_room_file, parent, false)
        return ChatRoomViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatRoomViewHolder, position: Int) {
        holder.bindData(chatRoomFileList[position])
    }

    override fun getItemCount(): Int = chatRoomFileList.size
}