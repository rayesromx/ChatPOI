package fcfm.lmad.poi.ChatPoi.presentation.tasks.presenter

import android.net.Uri
import fcfm.lmad.poi.ChatPoi.domain.dto.FileMsg
import fcfm.lmad.poi.ChatPoi.domain.entities.CompletedTask
import fcfm.lmad.poi.ChatPoi.domain.entities.Task
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack
import fcfm.lmad.poi.ChatPoi.domain.interactors.files.SendFile
import fcfm.lmad.poi.ChatPoi.domain.interactors.tasks.SetTaskAsCompletedByUser
import fcfm.lmad.poi.ChatPoi.presentation.shared.presenter.BasePresenter
import fcfm.lmad.poi.ChatPoi.presentation.tasks.ITaskContract

class TaskPresenter(
    private val setTaskAsCompletedByUser: SetTaskAsCompletedByUser,
    private val sendFile: SendFile
): BasePresenter<ITaskContract.IView>(), ITaskContract.IPresenter{
    override fun setTaskAsCompleted(task: CompletedTask){
        setTaskAsCompletedByUser.execute(task, object: IBaseUseCaseCallBack<CompletedTask> {
            override fun onSuccess(data: CompletedTask?) {
                if(!isViewAttached()) return
                view!!.onTaskCompleted(data!!)
            }

            override fun onError(error: String) {
                if(!isViewAttached()) return
                view!!.showError(error)
            }
        })
    }

    override fun sendFile(fm:FileMsg,task:Task) {
        sendFile.execute(fm, object:IBaseUseCaseCallBack<String>{
            override fun onSuccess(data: String?) {
                TODO("Not yet implemented")
            }

            override fun onError(error: String) {
                TODO("Not yet implemented")
            }

        })
    }

    override fun loadAttachments(task: Task) {
        TODO("Not yet implemented")
    }
}