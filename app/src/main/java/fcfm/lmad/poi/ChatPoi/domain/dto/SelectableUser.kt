package fcfm.lmad.poi.ChatPoi.domain.dto

import fcfm.lmad.poi.ChatPoi.domain.entities.User

data class SelectableUser(val user: User, var isSelected:Boolean)