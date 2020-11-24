package fcfm.lmad.poi.ChatPoi.domain.interactors.teams

import com.google.firebase.database.FirebaseDatabase
import fcfm.lmad.poi.ChatPoi.domain.entities.TeamUser
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack

class AssociateUserWithTeam : IAssociateUserWithTeamUseCase {
    override fun execute(input: TeamUser, listener: IBaseUseCaseCallBack<TeamUser>) {
        val ref = FirebaseDatabase.getInstance().reference
        input.id = ref.push().key!!
        ref.child("TeamUser").child(input.id).setValue(input.getHastMap()).addOnCompleteListener{
            if(it.isSuccessful){
                listener.onSuccess(input)
            }
            else{
                listener.onError(it.exception?.message!!)
            }
        }
    }
}