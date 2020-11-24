package fcfm.lmad.poi.ChatPoi.domain.interactors.teams

import com.google.firebase.database.*
import fcfm.lmad.poi.ChatPoi.domain.entities.Team
import fcfm.lmad.poi.ChatPoi.domain.entities.TeamContainer
import fcfm.lmad.poi.ChatPoi.domain.entities.TeamUser
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCase
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack

class SetupDefaultTeams: ISetupDefaultTeamsUseCase {
    override fun execute(listener: IBaseUseCaseCallBack<List<TeamContainer>>) {
        val dbReference = FirebaseDatabase.getInstance().reference
        val teams = getTeamsList()

        for(team in teams) {
            dbReference.child("Teams").orderByChild("name").equalTo(team.name).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(!snapshot.exists()){
                        val key = dbReference.push().key!!
                        team.id = key
                        dbReference.child("Teams").child(key).setValue(team.getHastMap())
                            .addOnCompleteListener {
                                if (!it.isSuccessful)
                                    listener.onError(it.exception?.message!!)
                                else{
                                    var subTeam = Team("General")
                                    subTeam.parent = team.id
                                    createSubTeam(subTeam,listener)
                                }
                            }
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    listener.onError(error.message)
                }
            })
        }
    }

    private fun createSubTeam(team:Team ,listener: IBaseUseCaseCallBack<List<TeamContainer>>) {
        val dbReference = FirebaseDatabase.getInstance().reference
        val key = dbReference.push().key!!
        team.id = key
        dbReference.child("Teams").child(team.parent)
                .child(key).setValue(team.getHastMap())
                .addOnCompleteListener {
                    if (!it.isSuccessful)
                        listener.onError(it.exception?.message!!)
                }
    }

    private fun getTeamsList():List<Team>{
        val teams = ArrayList<Team>()
        teams.add(Team("LMAD"))
        teams.add(Team("LCC"))
        teams.add(Team("LSTI"))
        teams.add(Team("LM"))
        teams.add(Team("LF"))
        teams.add(Team("LA"))
        return teams
    }
}