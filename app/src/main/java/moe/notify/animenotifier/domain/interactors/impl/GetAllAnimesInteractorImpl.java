package moe.notify.animenotifier.domain.interactors.impl;

import android.support.annotation.NonNull;

import moe.notify.animenotifier.domain.executor.Executor;
import moe.notify.animenotifier.domain.executor.MainThread;
import moe.notify.animenotifier.domain.interactors.GetAllAnimesInteractor;
import moe.notify.animenotifier.domain.interactors.base.AbstractInteractor;
import moe.notify.animenotifier.domain.model.animelist.AnimeList;
import moe.notify.animenotifier.domain.repository.AnimeListRepository;


public class GetAllAnimesInteractorImpl extends AbstractInteractor implements GetAllAnimesInteractor {

    private Callback mCallback;
    private AnimeListRepository animeListRepository;
    private String username;

    public GetAllAnimesInteractorImpl(Executor threadExecutor, MainThread mainThread, @NonNull AnimeListRepository animeListRepository, @NonNull Callback callback, String username) {
        super(threadExecutor, mainThread);

        this.animeListRepository = animeListRepository;
        mCallback = callback;
        this.username = username;

    }

    @Override
    public void run() {
        final AnimeList animeList = animeListRepository.getByUser(username);


        mainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onAnimeListRetrieved(animeList);
            }
        });
    }
}
