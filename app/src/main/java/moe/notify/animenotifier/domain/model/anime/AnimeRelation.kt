package moe.notify.animenotifier.domain.model.anime


import io.realm.RealmObject

open class AnimeRelation(
    open var type: String? = null,
    open var id: Long = 0) : RealmObject()
