package fcfm.lmad.poi.ChatPoi.domain.interactors.user

import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseInteractorCallBack

interface IRetrieveUserDataInteractor {
    interface IRetrieveUserDataInteractorCallback : IBaseInteractorCallBack<User> {}
    fun retrieveUser(userId:String, listener: IRetrieveUserDataInteractorCallback)
}