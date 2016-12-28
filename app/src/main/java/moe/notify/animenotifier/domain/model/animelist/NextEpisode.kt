package moe.notify.animenotifier.domain.model.animelist

import io.realm.RealmObject
import io.realm.annotations.Index
import io.realm.annotations.PrimaryKey


open class NextEpisode(
    @PrimaryKey
    @Index
    open var url: String? = null) : RealmObject()