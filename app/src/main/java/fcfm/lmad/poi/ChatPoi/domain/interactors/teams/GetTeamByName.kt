package fcfm.lmad.poi.ChatPoi.domain.interactors.teams

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import fcfm.lmad.poi.ChatPoi.domain.entities.Team
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack

class RetrieveTeamByName: IGetTeamByNameUseCase {
    override fun execute(input: String, listener: IBaseUseCaseCallBack<Team>) {
        val ref = FirebaseDatabase.getInstance().reference
        ref.child("Teams").orderByChild("group").equalTo(input.toLowerCase())
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){
                        var team = Team()
                        for (item in snapshot.children) {
                            val teamS = item.getValue(Team::class.java)!!
                            if(teamS.name == input){
                                team = teamS
                                break
                            }
                        }
                        listener.onSuccess(team)
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    listener.onError(error.message)
                }
            })
    }
}