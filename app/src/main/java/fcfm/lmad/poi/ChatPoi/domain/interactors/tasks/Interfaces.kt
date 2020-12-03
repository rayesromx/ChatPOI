package fcfm.lmad.poi.ChatPoi.domain.interactors.tasks

import fcfm.lmad.poi.ChatPoi.domain.entities.CompletedTask
import fcfm.lmad.poi.ChatPoi.domain.entities.Task
import fcfm.lmad.poi.ChatPoi.domain.interactors.IBaseUseCaseWithInput

interface ICreateNewTaskUseCase : IBaseUseCaseWithInput<Task, Task>{}
interface IGetGroupTasksUseCase : IBaseUseCaseWithInput<String, List<Task>>{}
interface ISetTaskAsCompletedByUserUseCase: IBaseUseCaseWithInput<CompletedTask, CompletedTask>{}
interface ICompletedTasksUseCase : IBaseUseCaseWithInput<String, List<CompletedTask>>{}