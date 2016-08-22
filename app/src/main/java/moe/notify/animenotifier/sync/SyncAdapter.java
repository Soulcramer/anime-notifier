package moe.notify.animenotifier.sync;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;

import com.freezingwind.animereleasenotifier.R;

import java.util.List;

import moe.notify.animenotifier.domain.model.anime.Anime;
import moe.notify.animenotifier.domain.repository.AnimeRepository;
import moe.notify.animenotifier.storage.AnimeRepositoryImpl;
import moe.notify.animenotifier.utils.AuthUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 *  * Handle the transfer of data between a server and an
 *  * app, using the Android sync adapter framework.
 *  
 */
public class SyncAdapter extends AbstractThreadedSyncAdapter {
    private Context mContext;

    private AnimeRepository mAnimeRepository;

    private List<Anime> mUnsyncedAnimes;
    private Anime mUnsyncedAnime;
    private Callback<Void> mResponseCallback = new Callback<Void>() {
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
            Timber.i("UPLOAD SUCCESS: %d", response.code());

            if (response.isSuccessful()) {
                mAnimeRepository.markSynced(mUnsyncedAnimes);
            }
        }

        @Override
        public void onFailure(Call<Void> call, Throwable t) {
            Timber.e("UPLOAD FAIL");

            // try to sync again
            SyncResult syncResult = new SyncResult();
        }
    };

    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        mContext = context;
        mAnimeRepository = new AnimeRepositoryImpl(mContext);
    }

    public SyncAdapter(Context context, boolean autoInitialize, boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);
        mContext = context;
        mAnimeRepository = new AnimeRepositoryImpl(mContext);
    }

    /**
     * This method will start a sync adapter that will upload data to the server.
     */
    public static void triggerSync(Context context) {
        // TODO sync adapter is forced for debugging purposes, remove this in production
        // Pass the settings flags by inserting them in a bundle
        Bundle settingsBundle = new Bundle();
        settingsBundle.putBoolean(
                ContentResolver.SYNC_EXTRAS_MANUAL, true);
        settingsBundle.putBoolean(
                ContentResolver.SYNC_EXTRAS_EXPEDITED, true);

        // request a sync using sync adapter
        AuthUtils.createDummyAccount(context);
        Account account = AuthUtils.getAccount(context);
        ContentResolver.requestSync(account, context.getString(R.string.stub_content_authority), settingsBundle);
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider,
                              SyncResult syncResult) {
        Timber.i("STARTING SYNC...");

        // initialize the services we will use
//        moe.notify.animenotifier.network.services.SyncService syncService = RestClient.getService(moe.notify.animenotifier.network.services.SyncService.class);
//
//        // TODO: get the real user's name
////        Payload payload = new Payload("default");
//
//        // get all unsynced data
////        mUnsyncedAnimes = mAnimeRepository.getAllUnsyncedAnimes();
////        mUnsyncedAnime = mAnimeRepository.getAnimeById(16498);
////        for (Anime anime : mUnsyncedAnimes) {
////
////            // convert to models suitable for transferring over network
////            RESTAnime restAnime = RESTAnimeModelConverter.convertToDomainModel(anime);
////            payload.addAnime(restAnime);
////        }
//
//
//        // run the upload
//        try {
//            Response<RESTAnime> response = syncService.getAnimeById(16498).execute();
//
//            Timber.i("DOWNLOAD SUCCESS: %d", response.code());
//            Anime anime = RESTAnimeModelConverter.convertToDomainModel(response.body());
//
//            // everything went well, mark local cost items as synced
//            if (response.isSuccessful()) {
//                mAnimeRepository.update(anime);
////                mAnimeRepository.markSynced(mUnsyncedAnimes);
//            }
//
//        } catch (IOException e) { // something went wrong
//            Timber.e("DOWNLOAD FAIL");
//            // make it a soft error so the framework does the exponential backoff
//            syncResult.stats.numIoExceptions += 1;
//
//            Timber.d("Restarting sync in %d seconds", syncResult.delayUntil);
//        }
    }
}
