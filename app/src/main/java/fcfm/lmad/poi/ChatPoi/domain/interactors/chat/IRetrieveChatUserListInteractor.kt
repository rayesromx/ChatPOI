package fcfm.lmad.poi.ChatPoi.domain.interactors.chat

import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseInteractorCallBack

interface IRetrieveChatUserListInteractor {
    interface IRetrieveChatUserListICallback: IBaseInteractorCallBack<List<User>> {}
    fun getChatuserList(listener:IRetrieveChatUserListICallback)
}