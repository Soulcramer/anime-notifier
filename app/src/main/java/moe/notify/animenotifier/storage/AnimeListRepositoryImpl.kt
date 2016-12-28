package moe.notify.animenotifier.storage

import android.content.Context
import io.realm.Realm
import io.realm.RealmList
import moe.notify.animenotifier.domain.model.animelist.AnimeList
import moe.notify.animenotifier.domain.repository.AnimeListRepository
import moe.notify.animenotifier.network.RestClient
import moe.notify.animenotifier.network.services.SyncService
import moe.notify.animenotifier.sync.SyncAdapter
import timber.log.Timber
import java.io.IOException
import java.util.ArrayList

open class AnimeListRepositoryImpl : AnimeListRepository {

  private val context: Context?

  constructor(context: Context) {
    this.context = context
  }

  constructor() {
    context = null
  }

  override fun insert(animeList: AnimeList) {
    Realm.getDefaultInstance().use { realmInstance ->
      realmInstance.executeTransaction { realm ->
        realm.insert(animeList)
      }
    }
  }

  override fun update(animeList: AnimeList) {
    Realm.getDefaultInstance().use { realmInstance ->
      realmInstance.executeTransaction { realm ->
        realm.insertOrUpdate(animeList)
      }
    }
  }

  override fun getByUser(user: String): AnimeList {
    var realm: Realm? = null
    var animeList: AnimeList = AnimeList()
    try {
      realm = Realm.getDefaultInstance()
      val savedAnimeList = realm.where(AnimeList::class.java).equalTo("user", user).findFirst()
      if (savedAnimeList == null) {
        val syncService = RestClient.getService(SyncService::class.java)
        try {
          val response = syncService.getAnimeListFromUser("Scott").execute()
          if (response.isSuccessful) {
            animeList = response.body()
            realm.executeTransaction { realm ->
              realm.insert(animeList)
            }
          }
        } catch (e: IOException) {
          Timber.e(e, "A problem occur while trying get the animeList of the user:%l,", user)
        }

      } else {
        // TODO 27/12/2016 Return a default Anime
//          StorageAnimeModelConverter.convertToDomainModel(savedAnimeList, anime);
        val syncService = RestClient.getService(SyncService::class.java)
        try {
          val response = syncService.getAnimeListFromUser("Scott").execute()
          if (response.isSuccessful) {
            animeList = response.body()
            realm.executeTransactionAsync { realm ->
              realm.insert(animeList)
            }
          }
        } catch (e: IOException) {
          Timber.e(e, "A problem occur while trying get the animeList of the user:%l,", user)
        }
        animeList = realm.copyFromRealm(savedAnimeList)
      }
    } catch (e: Exception) {
      Timber.e(e, "meh")
    } finally {
      if (realm != null) {
        realm.close()
      }
    }
    return animeList
  }

  override fun getAll(): RealmList<AnimeList>? {
    val animes: RealmList<AnimeList> = RealmList()
    Realm.getDefaultInstance().use { realmInstance ->
      val savedAnimes = realmInstance.where(AnimeList::class.java).findAll()
      if (savedAnimes != null) {
        animes.addAll(savedAnimes)
      }
      return animes
    }
  }

  override fun getAllUnsynced(): List<AnimeList> {
    // When we will be able to push data to the server.
    return ArrayList()
  }

  override fun markSynced(animeLists: List<AnimeList>) {
    // When we will be able to push data to the server.
  }

  override fun delete(animeList: AnimeList) {
    Realm.getDefaultInstance().use { realmInstance ->
      val savedAnimes = realmInstance.where(AnimeList::class.java).equalTo("user",
          animeList.user).findFirst()
      if (savedAnimes != null) {
        realmInstance.executeTransaction { realm ->
          animeList.deleteFromRealm()
        }
      }
    }
    SyncAdapter.triggerSync(context)
  }
}

