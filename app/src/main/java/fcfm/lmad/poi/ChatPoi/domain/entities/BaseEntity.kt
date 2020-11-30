package fcfm.lmad.poi.ChatPoi.domain.entities

abstract class BaseEntity {
    var uid: String = ""
    abstract fun getHastMap():HashMap<String,Any?>
}