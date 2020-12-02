package fcfm.lmad.poi.ChatPoi.domain.interactors.user

import com.google.firebase.auth.FirebaseAuth
import fcfm.lmad.poi.ChatPoi.domain.IRepository
import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack
import fcfm.lmad.poi.ChatPoi.infrastructure.repositories.FireBaseRepository

class ListAllUsers(
    private val repository: FireBaseRepository<User>
) :IListAllUsersUseCase{
    override fun execute(listener: IBaseUseCaseCallBack<List<User>>) {
        val firebaseUser = FirebaseAuth.getInstance().currentUser!!.uid

        repository.getAll(object:IRepository.IRepositoryListener<List<User>>{
            override fun onSuccess(data: List<User>) {
                val users = ArrayList<User>()
                for(user in data)
                {
                    if(user.uid != firebaseUser)
                        users.add(user)
                }
                listener.onSuccess(users)
            }

            override fun onError(error: String) {
                listener.onError(error)
            }
        })
    }
}