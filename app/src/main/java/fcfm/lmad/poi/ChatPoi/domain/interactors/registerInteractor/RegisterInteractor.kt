package fcfm.lmad.poi.ChatPoi.domain.interactors.registerInteractor

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterInteractor:IRegisterInteractor {
    override fun signUp(
        userName: String,
        email: String,
        password: String,
        carrera:String,
        listener: IRegisterInteractor.IRegisterCallback
    ) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener{
            if(it.isSuccessful){
                var firebaseUserID = FirebaseAuth.getInstance().currentUser?.uid
                var refUser = FirebaseDatabase.getInstance().reference.child("Users").child(firebaseUserID!!)

                var userHasMap = HashMap<String,Any>()
                userHasMap["uid"] = firebaseUserID
                userHasMap["username"] = userName
                userHasMap["profile_img"] = "https://firebasestorage.googleapis.com/v0/b/chatlmad.appspot.com/o/blank-profile-picture.png?alt=media&token=0c8c9c63-ee4e-4dca-ab66-2ca978816e4d"
                userHasMap["status"] = "offline"
                userHasMap["search"] = userName.toLowerCase()
                userHasMap["username"] = userName
                userHasMap["group"] = carrera.toLowerCase()

                refUser.updateChildren(userHasMap).addOnCompleteListener{task->
                    if(task.isSuccessful){
                        val profileUpdates = UserProfileChangeRequest.Builder()
                            .setDisplayName(userName)
                            .build()
                        FirebaseAuth.getInstance().currentUser?.updateProfile(profileUpdates)?.addOnCompleteListener{itUpdate ->
                            if(itUpdate.isSuccessful){
                                listener.onRegisterSucces()
                            }
                        }
                    }else{
                        listener.onRegisterFailure(task.exception?.message.toString())
                    }
                }



            }else{
                listener.onRegisterFailure(it.exception?.message.toString())
            }
        }
    }
}