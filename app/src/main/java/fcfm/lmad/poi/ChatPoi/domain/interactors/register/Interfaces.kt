package fcfm.lmad.poi.ChatPoi.domain.interactors.register

import fcfm.lmad.poi.ChatPoi.domain.dto.RegisterData
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseWithInput

interface IRegisterUserUseCase: IBaseUseCaseWithInput<RegisterData, Boolean> {}