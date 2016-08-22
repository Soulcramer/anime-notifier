package moe.notify.animenotifier.storage;

import android.content.Context;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import moe.notify.animenotifier.domain.model.anime.Anime;
import moe.notify.animenotifier.domain.model.animelist.AnimeList;
import moe.notify.animenotifier.domain.repository.AnimeRepository;
import moe.notify.animenotifier.network.RestClient;
import moe.notify.animenotifier.network.converters.RESTAnimeListModelConverter;
import moe.notify.animenotifier.network.model.animelist.RESTAnimeList;
import moe.notify.animenotifier.network.services.SyncService;
import moe.notify.animenotifier.storage.converters.StorageModelConverter;
import moe.notify.animenotifier.storage.model.AnimeList_Table;
import moe.notify.animenotifier.storage.model.Anime_Table;
import moe.notify.animenotifier.sync.SyncAdapter;
import retrofit2.Response;
import timber.log.Timber;

import static moe.notify.animenotifier.storage.converters.StorageModelConverter.convertToStorageModel;


public class AnimeRepositoryImpl implements AnimeRepository {

    private final Context mContext;

    public AnimeRepositoryImpl(Context context) {
        mContext = context;
    }

    @Override
    public void insert(Anime item) {
        moe.notify.animenotifier.storage.model.Anime dbItem = new moe.notify.animenotifier.storage.model.Anime();
        convertToStorageModel(item, dbItem);

        // mark as unsynced
        dbItem.synced = false;
        dbItem.insert();


    }

    @Override
    public void update(Anime anime) {
        moe.notify.animenotifier.storage.model.Anime dbItem = new moe.notify.animenotifier.storage.model.Anime();
        convertToStorageModel(anime, dbItem);

        // mark as unsynced
        dbItem.synced = false;
        dbItem.update();

//        SyncAdapter.triggerSync(mContext);
    }

    @Override
    public Anime getAnimeById(long id) {
//        moe.notify.animenotifier.storage.model.Anime anime = SQLite
//                .select()
//                .from(moe.notify.animenotifier.storage.model.Anime.class)
//                .where(Anime_Table.id.eq(id))
//                .querySingle();
//        SyncAdapter.triggerSync(mContext);
        Anime resultAnime = new Anime();
//        StorageModelConverter.convertToDomainModel(anime, resultAnime);
        return resultAnime;
    }

    @Override
    public AnimeList getAnimeListByUser(String user) {
        moe.notify.animenotifier.storage.model.AnimeList savedAnimeList = SQLite
                .select()
                .from(moe.notify.animenotifier.storage.model.AnimeList.class)
                .where(AnimeList_Table.user.is(user))

                .querySingle();
        AnimeList animeList = new AnimeList();
        if (savedAnimeList == null) {
            savedAnimeList = new moe.notify.animenotifier.storage.model.AnimeList();
            SyncService syncService = RestClient.getService(SyncService.class);
            try {
                Response<RESTAnimeList> response = syncService.getAnimeListFromUser("Scott").execute();

                RESTAnimeListModelConverter.convertToDomainModel(response.body(), animeList);

                // everything went well, mark local cost items as synced
                if (response.isSuccessful()) {
                    StorageModelConverter.convertAnimeListToStorageModel(animeList, savedAnimeList);

//                mAnimeRepository.markSynced(mUnsyncedAnimes);
                }

            } catch (IOException e) {
                Timber.e(e, "A problem occur while trying to talk with the server");
            }
        }
        StorageModelConverter.convertAnimeListToDomainModel(savedAnimeList, animeList);

        return animeList;
    }

    @Override
    public List<Anime> getAllAnimes() {
        List<moe.notify.animenotifier.storage.model.Anime> animes = SQLite
                .select()
                .from(moe.notify.animenotifier.storage.model.Anime.class)
                .queryList();
//        if (animes.isEmpty()) {
//            SyncService syncService = RestClient.getService(SyncService.class);
//            try {
//                Response<RESTAnimeList> response = syncService.getAnimeListFromUser("Scott").execute();
//                AnimeList animeList = new AnimeList();
//                RESTAnimeListModelConverter.convertToDomainModel(response.body(), animeList);
//
//                // everything went well, mark local cost items as synced
//                if (response.isSuccessful()) {
//                    animes.add(StorageModelConverter.convertAnimeListToStorageModel(animeList));
//
////                mAnimeRepository.markSynced(mUnsyncedAnimes);
//                }
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//
//        }

        List<Anime> animes1 = new ArrayList<>();
        StorageModelConverter.convertListToDomainModel(animes, animes1);
        return animes1;


//        SyncAdapter.triggerSync(mContext);
    }


    @Override
    public List<Anime> getAllUnsyncedAnimes() {
        List<moe.notify.animenotifier.storage.model.Anime> animes = SQLite
                .select()
                .from(moe.notify.animenotifier.storage.model.Anime.class)
                .where(Anime_Table.synced.eq(false))
                .queryList();
        List<Anime> anime = new ArrayList<>();
        StorageModelConverter.convertListToDomainModel(animes, anime);
        return anime;
    }

    @Override
    public void updateAllAnimes() {

        SyncAdapter.triggerSync(mContext);

    }

    @Override
    public void markSynced(List<Anime> animes) {
        // we have to convert it to the database model before storing
//        List<moe.notify.animenotifier.storage.model.Anime> unsyncedAnimes =
//                StorageModelConverter.convertListToStorageModel(animes);
//
//        for (moe.notify.animenotifier.storage.model.Anime anime : unsyncedAnimes) {
//            anime.synced = true;
//            anime.update();
//        }
    }

    @Override
    public void delete(Anime anime) {
        moe.notify.animenotifier.storage.model.Anime dbItem = new moe.notify.animenotifier.storage.model.Anime();
        StorageModelConverter.convertToStorageModel(anime, dbItem);
        dbItem.delete();

        SyncAdapter.triggerSync(mContext);
    }
}
