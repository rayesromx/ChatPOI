package fcfm.lmad.poi.ChatPoi.domain.interactors.teams

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import fcfm.lmad.poi.ChatPoi.domain.entities.Team
import fcfm.lmad.poi.ChatPoi.domain.entities.TeamContainer
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack

class ListTeams: IListTeamsUseCase {
    override fun execute(listener: IBaseUseCaseCallBack<List<TeamContainer>>) {
        val teamRef = FirebaseDatabase.getInstance().reference.child("Teams")
        teamRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var teamList = ArrayList<TeamContainer>()
                for (item in snapshot.children) {
                    val team = item.getValue(Team::class.java)
                    var tc = TeamContainer()
                    tc.team = team
                    tc.childTeams = ArrayList()
                    for (subItem in item.children) {
                        if(!subItem.child("parent").exists())
                            continue
                        val subTeam = subItem.getValue(Team::class.java)
                        tc.childTeams!!.add(subTeam!!)
                    }
                    teamList.add(tc)
                }
                listener.onSuccess(teamList)
            }

            override fun onCancelled(error: DatabaseError) {
                listener.onError(error.message)
            }
        })
    }
}