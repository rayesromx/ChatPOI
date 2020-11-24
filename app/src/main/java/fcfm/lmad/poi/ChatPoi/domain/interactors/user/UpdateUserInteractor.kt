package fcfm.lmad.poi.ChatPoi.domain.interactors.user

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import fcfm.lmad.poi.ChatPoi.domain.entities.User

class UpdateUserInteractor:IUpdateUserInteractor {
    override fun updateUserStatus(user: User, listener: IUpdateUserInteractor.IUpdateUserInteractorCallBack) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        var ref = FirebaseDatabase.getInstance().reference.child("Users").child(currentUser!!.uid)
        user.uid = currentUser!!.uid
        ref.updateChildren(user.getHastMap())
        listener.onSuccess(user)
    }
}