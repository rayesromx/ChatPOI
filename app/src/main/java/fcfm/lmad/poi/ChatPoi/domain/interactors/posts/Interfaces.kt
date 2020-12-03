package fcfm.lmad.poi.ChatPoi.domain.interactors.posts

import fcfm.lmad.poi.ChatPoi.domain.entities.Team
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseWithInput
import fcfm.lmad.poi.ChatPoi.models.TeamPost

interface ICreateNewPostUseCase: IBaseUseCaseWithInput<TeamPost, TeamPost>{}
interface IListAllPostFromTeamUseCase: IBaseUseCaseWithInput<Team, List<TeamPost>>{}

//interface IGetLoggedUserUseCase: IBaseUseCase<User> {}
