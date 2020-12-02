package fcfm.lmad.poi.ChatPoi.domain.interactors.user

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import fcfm.lmad.poi.ChatPoi.domain.IRepository
import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseCallBack
import fcfm.lmad.poi.ChatPoi.infrastructure.repositories.FireBaseRepository

class SearchUserByUsername(
        private val repository: FireBaseRepository<User>
) :ISearchUserByUsernameUseCase {
    override fun execute(input: String, listener: IBaseUseCaseCallBack<List<User>>) {
        val firebaseUser = FirebaseAuth.getInstance().currentUser!!.uid
        repository.getByCustomParam("search",input,object: IRepository.IRepositoryListener<List<User>>{
            override fun onSuccess(data: List<User>) {
                val users = ArrayList<User>()
                for(user in data)
                {
                    if(user.uid != firebaseUser && user.search.contains(input) )
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