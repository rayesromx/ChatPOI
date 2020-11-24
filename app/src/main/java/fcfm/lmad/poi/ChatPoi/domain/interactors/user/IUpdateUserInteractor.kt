package fcfm.lmad.poi.ChatPoi.domain.interactors.user

import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseInteractorCallBack

interface IUpdateUserInteractor{
    interface IUpdateUserInteractorCallBack: IBaseInteractorCallBack<User> {}
    fun updateUserStatus(user:User,listener:IUpdateUserInteractorCallBack)
}