package moe.notify.animenotifier.domain.model.anime


import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class AnimeLink(
    @PrimaryKey
    open var url: String? = null,
    open var title: String? = null) : RealmObject()