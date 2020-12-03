package fcfm.lmad.poi.ChatPoi.infrastructure.repositories

import com.google.firebase.database.DataSnapshot
import fcfm.lmad.poi.ChatPoi.models.TeamPost

class TeamPostRepository: FireBaseRepository<TeamPost>("TeamPost") {
    override fun getValue(item: DataSnapshot) = item.getValue(TeamPost::class.java)
}

