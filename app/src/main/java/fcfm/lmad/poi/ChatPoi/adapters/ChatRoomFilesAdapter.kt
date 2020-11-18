package fcfm.lmad.poi.ChatPoi.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fcfm.lmad.poi.ChatPoi.presentation.main.view.IFragmentAdmin
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.models.RoomFile
import kotlinx.android.synthetic.main.item_view_chat_room_file.view.*

class ChatRoomFilesAdapter(
    private val roomFileList: List<RoomFile>,
    val fragAdmin: IFragmentAdmin
) : RecyclerView.Adapter<ChatRoomFilesAdapter.ChatRoomViewHolder>() {


    inner class ChatRoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(currentFile: RoomFile) {
            itemView.chat_room_file_name.text = currentFile.name
            itemView.chat_room_file_time.text = currentFile.time
            //itemView.main_alert_image.text = currentAlert.image
            itemView.setOnClickListener{ fragAdmin.launchActivity(1)}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatRoomViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_view_chat_room_file, parent, false)
        return ChatRoomViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatRoomViewHolder, position: Int) {
        holder.bindData(roomFileList[position])
    }

    override fun getItemCount(): Int = roomFileList.size
}