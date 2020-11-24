package fcfm.lmad.poi.ChatPoi.domain.interactors.user

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack

class UpdateUser:IUpdateUserUseCase {
    override fun execute(input: User, listener: IBaseUseCaseCallBack<User>) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        var ref = FirebaseDatabase.getInstance().reference.child("Users").child(currentUser!!.uid)
        input.uid = currentUser.uid
        ref.updateChildren(input.getHastMap())
        listener.onSuccess(input)
    }
}