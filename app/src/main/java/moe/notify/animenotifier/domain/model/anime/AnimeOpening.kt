package moe.notify.animenotifier.domain.model.anime


import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class AnimeOpening(
    open var similarity: Double = 0.toDouble(),
    open var permalink: String? = null,
    @PrimaryKey
    open var uri: String? = null,
    open var title: String? = null,
    open var likes: Int = 0,
    open var plays: Int = 0) : RealmObject()