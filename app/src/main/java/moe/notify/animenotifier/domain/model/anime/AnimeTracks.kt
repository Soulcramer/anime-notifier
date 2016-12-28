package moe.notify.animenotifier.domain.model.anime


import io.realm.RealmObject

open class AnimeTracks(
    open var opening: AnimeOpening? = null
) : RealmObject()