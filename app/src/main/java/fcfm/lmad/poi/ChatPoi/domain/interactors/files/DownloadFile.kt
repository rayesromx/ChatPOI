package fcfm.lmad.poi.ChatPoi.domain.interactors.files

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import fcfm.lmad.poi.ChatPoi.domain.dto.FileMsg
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack

class DownloadFile(
    private val ctx: Context
):IDownloadFileUseCase{
    override fun execute(input: FileMsg, listener: IBaseUseCaseCallBack<Long>) {
        var request = DownloadManager.Request(Uri.parse(input.msg.image_url))
            .setTitle(input.fileName)
            .setDescription(input.extension)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
            .setAllowedOverMetered(true)

        val dm = ctx.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val id = dm.enqueue(request)
        listener.onSuccess(id)
    }

}