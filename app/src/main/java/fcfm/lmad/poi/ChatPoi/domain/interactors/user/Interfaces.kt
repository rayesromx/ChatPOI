package fcfm.lmad.poi.ChatPoi.domain.interactors.user

import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCase
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseWithInput

interface IGetLoggedUserUseCase: IBaseUseCase<User> {}
interface IListAllUsersUseCase: IBaseUseCase<List<User>> {}
interface ISearchUserByUsernameUseCase: IBaseUseCaseWithInput<String,List<User>> {}
interface ISearchUserByIdUseCase: IBaseUseCaseWithInput<String,User> {}
interface IUpdateUserUseCase: IBaseUseCaseWithInput<User,User> {}