package fcfm.lmad.poi.ChatPoi.domain.interactors.userInteractor
import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseInteractorCallBack

interface IListUsersInteractor {
    interface IListUsersCallback : IBaseInteractorCallBack<List<User>> {}
    fun retrieveAllUsers(listener: IListUsersCallback)
    fun searchUser(searchedUser:String, listener: IListUsersCallback)
}