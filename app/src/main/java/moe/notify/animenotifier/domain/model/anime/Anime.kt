package moe.notify.animenotifier.domain.model.anime

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import moe.notify.animenotifier.utils.RealmString
import java.util.Date


open class Anime(
    @PrimaryKey
    open var id: Long = 0,
    open var type: String? = null,
    open var title: AnimeTitle? = null,
    open var image: String? = null,
    open var airingStatus: String? = null,
    open var adult: Int = 0,
    open var watching: Int = 0,
    open var description: String? = null,
    open var startDate: Date? = null,
    open var endDate: Date? = null,
    open var youtubeId: String? = null,
    open var genres: RealmList<RealmString>? = RealmList(),
    open var source: String? = null,
    open var classification: String? = null,
    open var totalEpisodes: Int = 0,
    open var duration: Int = 0,
    open var hashtag: String? = null,
    open var anilistEdited: Long = 0,
    open var links: RealmList<AnimeLink>? = RealmList(),
    open var studios: RealmList<AnimeStudio>? = RealmList(),
    open var relations: RealmList<AnimeRelation>? = RealmList(),
    open var tracks: AnimeTracks? = null) : RealmObject()