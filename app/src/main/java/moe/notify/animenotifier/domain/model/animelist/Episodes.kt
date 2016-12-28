package moe.notify.animenotifier.domain.model.animelist

import io.realm.RealmObject


open class Episodes(
    open var max: Long = 0,
    open var available: Long = 0,
    open var watched: Long = 0,
    open var next: Long = 0,
    open var offset: Long = 0) : RealmObject()