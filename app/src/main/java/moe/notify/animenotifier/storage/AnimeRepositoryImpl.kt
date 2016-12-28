package moe.notify.animenotifier.storage

import android.content.Context
import io.realm.Realm
import io.realm.RealmList
import moe.notify.animenotifier.domain.model.anime.Anime
import moe.notify.animenotifier.domain.repository.AnimeRepository
import moe.notify.animenotifier.network.RestClient
import moe.notify.animenotifier.network.services.SyncService
import moe.notify.animenotifier.sync.SyncAdapter
import timber.log.Timber
import java.io.IOException


class AnimeRepositoryImpl : AnimeRepository {

  private val context: Context?

  constructor(context: Context) {
    this.context = context
  }

  constructor() {
    context = null
  }

  override fun insert(anime: Anime) {
    Realm.getDefaultInstance().use { realmInstance ->
      realmInstance.executeTransaction { realm ->
        realm.insertOrUpdate(anime)
      }
    }
  }

  override fun update(anime: Anime) {
    Realm.getDefaultInstance().use { realmInstance ->
      realmInstance.executeTransaction { realm ->
        realm.insertOrUpdate(anime)
      }
    }
  }

  override fun getById(id: Long): Anime {
    Realm.getDefaultInstance().use { realmInstance ->
      var savedAnime = realmInstance.where(Anime::class.java).equalTo("id", id).findFirst()
      if (savedAnime == null) {
        savedAnime = Anime()
        val syncService = RestClient.getService(SyncService::class.java)
        try {
          val response = syncService.getAnimeById(id).execute()
          if (response.isSuccessful) {
            realmInstance.executeTransaction { realm ->
              realmInstance.insert(savedAnime)
            }
          }
        } catch (e: IOException) {
          Timber.e(e, "A problem occur while trying get the anime with the id:%l,", id)
        }

      } else {
        // TODO 27/12/2016 Return a default Anime
//          StorageAnimeModelConverter.convertToDomainModel(savedAnime, anime);
      }
      return savedAnime
    }

  }

  override fun getAll(): RealmList<Anime> {
    val animes: RealmList<Anime> = RealmList()
    Realm.getDefaultInstance().use { realmInstance ->
      val savedAnimes = realmInstance.where(Anime::class.java).findAll()
      if (savedAnimes != null) {
        animes.addAll(savedAnimes)
      }
      return animes
    }

  }

//  override fun getAllUnsynced(): List<Anime> {
//    val animes: List<moe.notify.animenotifier.storage.model.anime.Anime>? = /*SQLite
//                .select()
//                .from(moe.notify.animenotifier.storage.model.anime.Anime.class)
//                .where(Anime_Table.synced.eq(false))
//                .queryList();*/null
//    val anime = ArrayList<Anime>()
//    if (animes != null) {
//      //            StorageAnimeModelConverter.convertListToDomainModel(animes, anime);
//    }
//    return anime
//  }

  override fun updateAll() {

    SyncAdapter.triggerSync(context)
  }

  override fun markSynced(animes: List<Anime>) {
    // we have to convert it to the database model before storing
    //        List<moe.notify.animenotifier.storage.model.anime.Anime> unsyncedAnimes =
    //                StorageAnimeModelConverter.convertListToStorageModel(animes);
    //
    //        for (moe.notify.animenotifier.storage.model.anime.Anime anime : unsyncedAnimes) {
    //            anime.synced = true;
    //            anime.update();
    //        }
  }

  override fun delete(anime: Anime) {
    Realm.getDefaultInstance().use { realmInstance ->
      val savedAnimes = realmInstance.where(Anime::class.java).equalTo("id", anime.id).findFirst()
      if (savedAnimes != null) {
        realmInstance.executeTransaction { realm ->
          anime.deleteFromRealm()
        }
      }
    }
    SyncAdapter.triggerSync(context)
  }
}
