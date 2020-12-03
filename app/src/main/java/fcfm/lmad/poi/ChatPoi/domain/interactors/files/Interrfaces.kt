package fcfm.lmad.poi.ChatPoi.domain.interactors.files

import fcfm.lmad.poi.ChatPoi.domain.dto.FileMsg
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseWithInput

interface ISendFileUseCase : IBaseUseCaseWithInput<FileMsg, String>{}
interface IDownloadFileUseCase : IBaseUseCaseWithInput<FileMsg, Long>{}

