package moe.notify.animenotifier.domain.interactors.impl;

import android.support.annotation.NonNull;
import moe.notify.animenotifier.domain.executor.Executor;
import moe.notify.animenotifier.domain.executor.MainThread;
import moe.notify.animenotifier.domain.interactors.GetAnimeInteractor;
import moe.notify.animenotifier.domain.interactors.base.AbstractInteractor;
import moe.notify.animenotifier.domain.model.anime.Anime;
import moe.notify.animenotifier.domain.repository.AnimeRepository;


public class GetAnimeInteractorImpl extends AbstractInteractor implements GetAnimeInteractor {

  private final Callback callback;
  private final AnimeRepository animeRepository;
  private final long animeId;

  public GetAnimeInteractorImpl(Executor threadExecutor, MainThread mainThread,
      @NonNull AnimeRepository animeRepository, @NonNull Callback callback, long animeId) {
        super(threadExecutor, mainThread);
        this.animeRepository = animeRepository;
        this.callback = callback;
        this.animeId = animeId;
    }

    @Override
    public void run() {
        final Anime anime = animeRepository.getById(animeId);

        mainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onAnimeRetrieved(anime);
            }
        });
    }
}
