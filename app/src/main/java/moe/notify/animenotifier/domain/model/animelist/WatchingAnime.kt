package moe.notify.animenotifier.domain.model.animelist

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import moe.notify.animenotifier.domain.model.anime.AnimeTitle


open class WatchingAnime(
    open var animeProvider: AnimeProvider? = null,
    open var airingDate: AiringDate? = null,
    open var preferredTitle: String? = null,
    open var title: AnimeTitle? = null,
    open var image: String? = null,
    @PrimaryKey
    open var id: Long? = null,
    open var episodes: Episodes? = null) : RealmObject()