package fcfm.lmad.poi.ChatPoi.infrastructure.repositories

import com.google.firebase.database.DataSnapshot
import fcfm.lmad.poi.ChatPoi.domain.IRepository
import fcfm.lmad.poi.ChatPoi.domain.entities.Team

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
}
