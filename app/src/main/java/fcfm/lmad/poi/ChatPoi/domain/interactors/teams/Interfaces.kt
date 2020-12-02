package fcfm.lmad.poi.ChatPoi.domain.interactors.teams

import fcfm.lmad.poi.ChatPoi.domain.entities.Team
import fcfm.lmad.poi.ChatPoi.domain.entities.TeamContainer
import fcfm.lmad.poi.ChatPoi.domain.entities.TeamUser
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCase
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseWithInput

interface ISetupDefaultTeamsUseCase: IBaseUseCase<List<TeamContainer>> {}
interface IListSubteamsFromTeamUseCase:  IBaseUseCaseWithInput<String,List<TeamContainer>> {}
interface IGetTeamByIdUseCase: IBaseUseCaseWithInput<String,Team> {}
interface IGetTeamByNameUseCase: IBaseUseCaseWithInput<String,Team> {}
interface IAssociateUserWithTeamUseCase: IBaseUseCaseWithInput<TeamUser,TeamUser> {}
