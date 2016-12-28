package moe.notify.animenotifier.domain.model.anime

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class AnimeStudio(
    open var name: String? = null,
    open var wiki: String? = null,
    open var mainStudio: Int = 0,
    @PrimaryKey
    open var id: Long = 0) : RealmObject()