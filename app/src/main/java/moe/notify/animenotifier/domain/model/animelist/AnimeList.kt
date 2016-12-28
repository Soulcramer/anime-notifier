package moe.notify.animenotifier.domain.model.animelist

import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class AnimeList(
    @PrimaryKey
    open var user: String? = null,
    open var listProvider: String? = null,
    open var listUrl: String? = null,
    @SerializedName("watching")
    open var animes: RealmList<WatchingAnime> = RealmList(),
    open var cacheKey: String? = null,
    open var generated: String? = null,
    open var titleLanguage: String? = null) : RealmObject()
