package fcfm.lmad.poi.ChatPoi.infrastructure.repositories

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import fcfm.lmad.poi.ChatPoi.domain.IRepository
import fcfm.lmad.poi.ChatPoi.domain.entities.Team
import fcfm.lmad.poi.ChatPoi.domain.entities.TeamContainer

class TeamRepository: FireBaseRepository<Team>("Teams") {
    override fun getValue(item: DataSnapshot) = item.getValue(Team::class.java)

    private fun getSecondTableChild(model:Team) =getTableRef().child(model.parent).child(model.uid)

    override fun save(model: Team, listener: IRepository.IRepositoryListener<String>) {
        if(model.parent.isBlank())
            super.save(model, listener)
        else{
            getByCustomParam(getFirsTableChild(model.parent),"name",model.name,object:IRepository.IRepositoryListener<List<Team>>{
                override fun onSuccess(data: List<Team>) {
                    if(data.isEmpty()){
                        if(model.uid.isBlank())
                            model.uid = dbReference.push().key!!

                        save(getSecondTableChild(model),model,listener)
                    }
                }

                override fun onError(error: String) {
                    listener.onError(error)
                }
            })
        }
    }

    fun getTeamsList(mainTeam:String, listener:IRepository.IRepositoryListener<List<TeamContainer>>){

        getTableRef().addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val teamList = ArrayList<TeamContainer>()
                for (item in snapshot.children) {
                    val team = item.getValue(Team::class.java)
                    if(team!!.group != mainTeam)
                       continue
                    val tc = TeamContainer()
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
