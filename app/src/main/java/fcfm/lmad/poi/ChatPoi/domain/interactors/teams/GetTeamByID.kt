package fcfm.lmad.poi.ChatPoi.domain.interactors.teams

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import fcfm.lmad.poi.ChatPoi.domain.entities.Team
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack

class GetTeamByID: IGetTeamByIdUseCase {
    override fun execute(input: String, listener: IBaseUseCaseCallBack<Team>) {
        val teamRef = FirebaseDatabase.getInstance().reference.child("Teams").child(input)
        teamRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var team = Team()
                for (item in snapshot.children) {
                    val steam = item.getValue(Team::class.java)
                    if(steam?.uid == input){
                        team = steam
                    }
                }
                listener.onSuccess(team)
            }

            override fun onCancelled(error: DatabaseError) {
                listener.onError(error.message)
            }
        })
    }
}