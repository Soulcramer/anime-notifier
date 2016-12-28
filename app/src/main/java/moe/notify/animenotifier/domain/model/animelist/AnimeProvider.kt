package moe.notify.animenotifier.domain.model.animelist

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class AnimeProvider(
    open var nextEpisode: NextEpisode? = null,
    open var available: Long = 0,
    @PrimaryKey open var url: String? = null,
    open var type: String? = null,
    open var rssUrl: String? = null) : RealmObject()