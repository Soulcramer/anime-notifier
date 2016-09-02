package moe.notify.animenotifier.storage;

import android.content.Context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import moe.notify.animenotifier.domain.model.animelist.AnimeList;
import moe.notify.animenotifier.domain.repository.AnimeListRepository;
import moe.notify.animenotifier.network.RestClient;
import moe.notify.animenotifier.network.converters.RESTAnimeListModelConverter;
import moe.notify.animenotifier.network.model.animelist.RESTAnimeList;
import moe.notify.animenotifier.network.services.SyncService;
import moe.notify.animenotifier.storage.converters.StorageAnimeListModelConverter;
import retrofit2.Response;
import timber.log.Timber;


public class AnimeListRepositoryImpl implements AnimeListRepository {

    private final Context context;

    public AnimeListRepositoryImpl(Context context) {
        this.context = context;
    }

    public AnimeListRepositoryImpl() {
        context = null;
    }

    @Override
    public void insert(AnimeList item) {
        moe.notify.animenotifier.storage.model.animelist.AnimeList dbItem = new moe.notify.animenotifier.storage.model.animelist.AnimeList();
        StorageAnimeListModelConverter.convertAnimeListToStorageModel(item, dbItem);

        dbItem.insert();
    }

    @Override
    public void update(AnimeList item) {
        moe.notify.animenotifier.storage.model.animelist.AnimeList dbItem = new moe.notify.animenotifier.storage.model.animelist.AnimeList();
        StorageAnimeListModelConverter.convertAnimeListToStorageModel(item, dbItem);

        dbItem.update();
    }

    @Override
    public AnimeList getByUser(String user) {
        moe.notify.animenotifier.storage.model.animelist.AnimeList savedAnimeList = /*SQLite
                .select()
                .from(moe.notify.animenotifier.storage.model.animelist.AnimeList.class)
                .where(AnimeList_Watching_Table.animeList_user.is(user))
                .querySingle();*/null;

        AnimeList animeList = new AnimeList();
        if (savedAnimeList == null) {
            savedAnimeList = new moe.notify.animenotifier.storage.model.animelist.AnimeList();
            SyncService syncService = RestClient.getService(SyncService.class);
            try {
                Response<RESTAnimeList> response = syncService.getAnimeListFromUser("Scott").execute();

                RESTAnimeListModelConverter.convertToDomainModel(response.body(), animeList);

                // everything went well, lets update
                if (response.isSuccessful()) {
                    StorageAnimeListModelConverter.convertAnimeListToStorageModel(animeList, savedAnimeList);

//                mAnimeRepository.markSynced(mUnsyncedAnimes);
                }

            } catch (IOException e) {
                Timber.e(e, "A problem occur while trying to talk with the server");
            }
        }
        StorageAnimeListModelConverter.convertAnimeListToDomainModel(savedAnimeList, animeList);

        return animeList;
    }

    @Override
    public List<AnimeList> getAll() {
//        List<moe.notify.animenotifier.storage.model.animelist.AnimeList> savedAnimeList = SQLite
//                .select()
//                .from(moe.notify.animenotifier.storage.model.animelist.AnimeList.class)
//                .queryList();
//        List<AnimeList> animeList = new ArrayList<>();
//
//        StorageAnimeListModelConverter.convertAnimeListToDomainModel(savedAnimeList, animeList);
        return null;
    }

    @Override
    public List<AnimeList> getAllUnsynced() {
        // When we will be able to push data to the server.
        return new ArrayList<>();
    }

    @Override
    public void markSynced(List<AnimeList> animeLists) {
        // When we will be able to push data to the server.
    }

    @Override
    public void delete(AnimeList animeList) {

    }
}
