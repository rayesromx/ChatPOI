package fcfm.lmad.poi.ChatPoi.domain.interactors.login

import fcfm.lmad.poi.ChatPoi.domain.dto.LoginData
import fcfm.lmad.poi.ChatPoi.domain.entities.User
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCase
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseWithInput

interface ICheckLoggedInUseCase: IBaseUseCase<Boolean> {}
interface ILogOutUseCase: IBaseUseCase<Boolean> {}
interface ILogInUseCase: IBaseUseCaseWithInput<LoginData, Boolean> {}
interface IGetLoggedUserDataUseCase: IBaseUseCase<User> {}