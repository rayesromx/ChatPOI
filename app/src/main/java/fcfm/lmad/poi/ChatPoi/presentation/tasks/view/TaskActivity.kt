package fcfm.lmad.poi.ChatPoi.presentation.tasks.view

import android.content.Intent
import android.os.Bundle
import android.provider.OpenableColumns
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.data.CustomSessionState
import fcfm.lmad.poi.ChatPoi.domain.dto.FileMsg
import fcfm.lmad.poi.ChatPoi.domain.entities.CompletedTask
import fcfm.lmad.poi.ChatPoi.domain.interactors.files.SendFile
import fcfm.lmad.poi.ChatPoi.domain.interactors.tasks.SetTaskAsCompletedByUser
import fcfm.lmad.poi.ChatPoi.domain.interactors.user.UpdateUser
import fcfm.lmad.poi.ChatPoi.infrastructure.repositories.CompletedTaskRepository
import fcfm.lmad.poi.ChatPoi.infrastructure.repositories.FireBaseRepository
import fcfm.lmad.poi.ChatPoi.infrastructure.repositories.UserRepository
import fcfm.lmad.poi.ChatPoi.presentation.shared.view.BaseActivity
import fcfm.lmad.poi.ChatPoi.presentation.tasks.ITaskContract
import fcfm.lmad.poi.ChatPoi.presentation.tasks.presenter.TaskPresenter
import kotlinx.android.synthetic.main.activity_task.*

class TaskActivity : BaseActivity(), ITaskContract.IView {

    lateinit var presenter : TaskPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = TaskPresenter(
            SetTaskAsCompletedByUser(CompletedTaskRepository()),
            SendFile(),
            UpdateUser(UserRepository())
        )

        presenter.attachView(this)

        if(CustomSessionState.currentCompletedTasks.taskIds.contains(CustomSessionState.currentTask.uid))
        {
            txt_current_task_title.text = "Tarea completada"
            btn_complete_task.isEnabled = false
        }
        else {
            txt_current_task_title.text = "Detalles de la tarea"
            btn_complete_task.isEnabled = true
        }
        txt_current_task_name.text = CustomSessionState.currentTask.name
        txt_current_task_due_date.text = "Vence el ${CustomSessionState.currentTask.dueDate}"
        txt_current_task_description.text = CustomSessionState.currentTask.description


        btn_complete_task.setOnClickListener{
            CustomSessionState.currentCompletedTasks.userUid = CustomSessionState.loggedUser.uid
            CustomSessionState.currentCompletedTasks.taskIds.add(CustomSessionState.currentTask.uid)
            presenter.setTaskAsCompleted(CustomSessionState.currentCompletedTasks)
        }
    }

    override fun getLayout() = R.layout.activity_task
    override fun onTaskCompleted(task: CompletedTask) {
        CustomSessionState.loggedUser.stars += CustomSessionState.currentTask.points.toInt()
        presenter.updateUser()
        finish()
    }

    override fun uploadFile() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "*/*"
        startActivityForResult(Intent.createChooser(intent,"Selecciona el archivo"),438)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var fm =FileMsg()
        fm.fileName =  "archivo"
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
                fm.fileName = cursor.getString(nameIndex)
            }
            fm.filePath = data.data!!
            presenter.sendFile(fm, CustomSessionState.currentTask)
            presenter.loadAttachments(CustomSessionState.currentTask)
        }
    }

}