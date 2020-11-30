package fcfm.lmad.poi.ChatPoi.domain

import fcfm.lmad.poi.ChatPoi.domain.entities.BaseEntity

interface IRepository<T> where T: BaseEntity {
    interface IRepositoryListener<R> {
        fun onSuccess(data: R)
        fun onError(error: String)
    }

    fun getAll(listener:IRepositoryListener<List<T>>)
    fun getById(id:String,listener:IRepositoryListener<T>)
    fun getByCustomParam(paramKey:String, paramValue:String,listener:IRepositoryListener<List<T>>)

    fun save(model: T,listener:IRepositoryListener<String>)
}