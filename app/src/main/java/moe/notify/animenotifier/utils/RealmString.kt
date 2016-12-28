package moe.notify.animenotifier.utils

import io.realm.RealmObject

open class RealmString(
    open var value: String? = null
) : RealmObject()