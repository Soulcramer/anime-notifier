package moe.notify.animenotifier.presentation.presenters.impl;

import moe.notify.animenotifier.domain.executor.Executor;
import moe.notify.animenotifier.domain.executor.MainThread;
import moe.notify.animenotifier.domain.interactors.GetAnimeInteractor;
import moe.notify.animenotifier.domain.interactors.impl.GetAnimeInteractorImpl;
import moe.notify.animenotifier.domain.model.anime.Anime;
import moe.notify.animenotifier.domain.repository.AnimeRepository;
import moe.notify.animenotifier.presentation.presenters.AnimePresenter;
import moe.notify.animenotifier.presentation.presenters.base.AbstractPresenter;

/**
 * Created by GOKU on 22/08/2016.
 */

public class AnimePresenterImpl extends AbstractPresenter implements AnimePresenter, GetAnimeInteractor.Callback {


    private final View view;
    private final AnimeRepository animeRepository;
    private GetAnimeInteractorImpl getAnimeInteractor;
    private long animeId;

    public AnimePresenterImpl(Executor executor, MainThread mainThread, View view, AnimeRepository animeRepository) {
        super(executor, mainThread);
        this.view = view;
        this.animeRepository = animeRepository;
        animeId = 0;
    }

    @Override
    public void getAnime(long animeId) {

        getAnimeInteractor = new GetAnimeInteractorImpl(
                mExecutor,
                mMainThread,
                animeRepository,
                this,
                animeId

        );
        this.animeId = animeId;

        getAnimeInteractor.execute();
    }

    @Override
    public void resume() {
        if (animeId != 0) {
            getAnime(animeId);
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {
        getAnimeInteractor.cancel();
    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onAnimeRetrieved(Anime anime) {
        view.showAnime(anime);
    }
}
