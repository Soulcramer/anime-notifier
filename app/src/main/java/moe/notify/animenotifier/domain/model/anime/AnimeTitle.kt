package moe.notify.animenotifier.domain.model.anime

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import moe.notify.animenotifier.utils.RealmString


open class AnimeTitle(
    @PrimaryKey
    open var romaji: String? = null,
    open var japanese: String? = null,
    open var synonyms: RealmList<RealmString>? = RealmList(),
    open var english: String? = null) : RealmObject()