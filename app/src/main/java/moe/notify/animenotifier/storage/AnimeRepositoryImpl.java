package moe.notify.animenotifier.storage;

import android.content.Context;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import moe.notify.animenotifier.domain.model.anime.Anime;
import moe.notify.animenotifier.domain.repository.AnimeRepository;
import moe.notify.animenotifier.network.RestClient;
import moe.notify.animenotifier.network.converters.RESTAnimeModelConverter;
import moe.notify.animenotifier.network.model.anime.RESTAnime;
import moe.notify.animenotifier.network.services.SyncService;
import moe.notify.animenotifier.storage.converters.StorageAnimeModelConverter;
import moe.notify.animenotifier.storage.model.anime.Anime_Table;
import moe.notify.animenotifier.sync.SyncAdapter;
import retrofit2.Response;
import timber.log.Timber;

import static moe.notify.animenotifier.storage.converters.StorageAnimeModelConverter.convertToStorageModel;


public class AnimeRepositoryImpl implements AnimeRepository {

    private final Context mContext;

    public AnimeRepositoryImpl(Context context) {
        mContext = context;
    }

    @Override
    public void insert(Anime item) {
        moe.notify.animenotifier.storage.model.anime.Anime dbItem = new moe.notify.animenotifier.storage.model.anime.Anime();
        convertToStorageModel(item, dbItem);

        // mark as unsynced
        dbItem.synced = false;
        dbItem.insert();


    }

    @Override
    public void update(Anime anime) {
        moe.notify.animenotifier.storage.model.anime.Anime dbItem = new moe.notify.animenotifier.storage.model.anime.Anime();
        convertToStorageModel(anime, dbItem);

        // mark as unsynced
        dbItem.synced = false;
        dbItem.update();

//        SyncAdapter.triggerSync(mContext);
    }

    @Override
    public Anime getById(long id) {
        moe.notify.animenotifier.storage.model.anime.Anime savedAnime = SQLite
                .select()
                .from(moe.notify.animenotifier.storage.model.anime.Anime.class)
                .where(Anime_Table.id.eq(id))
                .querySingle();
        Anime anime = new Anime();
        if (savedAnime == null) {
//            savedAnime = new moe.notify.animenotifier.storage.model.anime.Anime();

            SyncService syncService = RestClient.getService(SyncService.class);
            try {
                Response<RESTAnime> response = syncService.getAnimeById(id).execute();

                RESTAnimeModelConverter.convertToDomainModel(response.body(), anime);

                // everything went well, mark local cost items as synced
                if (response.isSuccessful()) {
//                    StorageAnimeModelConverter.convertToStorageModel(anime, savedAnime);

//                mAnimeRepository.markSynced(mUnsyncedAnimes);
                }

            } catch (IOException e) {
                Timber.e(e, "A problem occur while trying to talk with the server");
            }
        } else {
            StorageAnimeModelConverter.convertToDomainModel(savedAnime, anime);
        }
        return anime;
    }


    @Override
    public List<Anime> getAll() {
        List<moe.notify.animenotifier.storage.model.anime.Anime> animes = SQLite
                .select()
                .from(moe.notify.animenotifier.storage.model.anime.Anime.class)
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
//                    animes.add(StorageAnimeModelConverter.convertAnimeListToStorageModel(animeList));
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
        StorageAnimeModelConverter.convertListToDomainModel(animes, animes1);
        return animes1;


//        SyncAdapter.triggerSync(mContext);
    }


    @Override
    public List<Anime> getAllUnsynced() {
        List<moe.notify.animenotifier.storage.model.anime.Anime> animes = /*SQLite
                .select()
                .from(moe.notify.animenotifier.storage.model.anime.Anime.class)
                .where(Anime_Table.synced.eq(false))
                .queryList();*/null;
        List<Anime> anime = new ArrayList<>();
        if (animes != null) {
            StorageAnimeModelConverter.convertListToDomainModel(animes, anime);
        }
        return anime;
    }

    @Override
    public void updateAll() {

        SyncAdapter.triggerSync(mContext);

    }

    @Override
    public void markSynced(List<Anime> animes) {
        // we have to convert it to the database model before storing
//        List<moe.notify.animenotifier.storage.model.anime.Anime> unsyncedAnimes =
//                StorageAnimeModelConverter.convertListToStorageModel(animes);
//
//        for (moe.notify.animenotifier.storage.model.anime.Anime anime : unsyncedAnimes) {
//            anime.synced = true;
//            anime.update();
//        }
    }

    @Override
    public void delete(Anime anime) {
        moe.notify.animenotifier.storage.model.anime.Anime dbItem = new moe.notify.animenotifier.storage.model.anime.Anime();
        StorageAnimeModelConverter.convertToStorageModel(anime, dbItem);
        dbItem.delete();

        SyncAdapter.triggerSync(mContext);
    }
}
