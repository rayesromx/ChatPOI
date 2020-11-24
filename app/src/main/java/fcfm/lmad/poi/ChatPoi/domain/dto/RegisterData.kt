package fcfm.lmad.poi.ChatPoi.domain.dto

import fcfm.lmad.poi.ChatPoi.domain.entities.User

data class RegisterData (
        val user: User,
        val password: String
)