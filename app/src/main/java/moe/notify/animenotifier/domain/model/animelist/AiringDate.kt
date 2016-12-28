package moe.notify.animenotifier.domain.model.animelist

import io.realm.RealmObject
import io.realm.annotations.Index
import io.realm.annotations.PrimaryKey


open class AiringDate(
    open var remainingString: String? = null,
    @PrimaryKey
    @Index
    open var timeStamp: Long = 0,
    open var remaining: Double = 0.0) : RealmObject()
