package moe.notify.animenotifier.domain.interactors.impl;

import android.support.annotation.NonNull;

import moe.notify.animenotifier.domain.executor.Executor;
import moe.notify.animenotifier.domain.executor.MainThread;
import moe.notify.animenotifier.domain.interactors.GetAllAnimesInteractor;
import moe.notify.animenotifier.domain.interactors.base.AbstractInteractor;
import moe.notify.animenotifier.domain.model.animelist.AnimeList;
import moe.notify.animenotifier.domain.repository.AnimeRepository;


public class GetAllAnimesInteractorImpl extends AbstractInteractor implements GetAllAnimesInteractor {

    private Callback mCallback;
    private AnimeRepository mAnimeRepository;

    public GetAllAnimesInteractorImpl(Executor threadExecutor, MainThread mainThread, @NonNull AnimeRepository animeRepository, @NonNull Callback callback) {
        super(threadExecutor, mainThread);

        mAnimeRepository = animeRepository;
        mCallback = callback;

    }

    @Override
    public void run() {
        final AnimeList animeList = mAnimeRepository.getAnimeListByUser("scott");


        mainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onAnimeListRetrieved(animeList);
            }
        });
    }
}
