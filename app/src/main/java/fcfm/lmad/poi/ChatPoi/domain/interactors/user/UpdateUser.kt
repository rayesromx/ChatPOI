package fcfm.lmad.poi.ChatPoi.domain.interactors.user

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import fcfm.lmad.poi.ChatPoi.domain.IRepository
import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack
import fcfm.lmad.poi.ChatPoi.infrastructure.repositories.FireBaseRepository

class UpdateUser(
    private val repository: FireBaseRepository<User>
) :IUpdateUserUseCase {
    override fun execute(input: User, listener: IBaseUseCaseCallBack<User>) {
        repository.getById(input.uid,object:IRepository.IRepositoryListener<User?>{
            override fun onSuccess(data: User?) {
                if(data!=null){
                    repository.save(input,object: IRepository.IRepositoryListener<String>{
                        override fun onSuccess(data: String) {
                            listener.onSuccess(input)
                        }

                        override fun onError(error: String) {
                            listener.onError(error)
                        }
                    })
                }
            }
            override fun onError(error: String) {
               listener.onError(error)
            }
        })
    }
}