package moe.notify.animenotifier.presentation.presenters.impl;

import moe.notify.animenotifier.domain.executor.Executor;
import moe.notify.animenotifier.domain.executor.MainThread;
import moe.notify.animenotifier.domain.interactors.GetAllAnimesInteractor;
import moe.notify.animenotifier.domain.interactors.impl.GetAllAnimesInteractorImpl;
import moe.notify.animenotifier.domain.model.animelist.AnimeList;
import moe.notify.animenotifier.domain.repository.AnimeRepository;
import moe.notify.animenotifier.presentation.presenters.AnimeListPresenter;
import moe.notify.animenotifier.presentation.presenters.base.AbstractPresenter;


public class AnimeListPresenterImpl extends AbstractPresenter implements AnimeListPresenter, GetAllAnimesInteractor.Callback {

    private AnimeListPresenter.View mView;
    private AnimeRepository mAnimeRepository;
    private GetAllAnimesInteractorImpl getAnimesInteractor;

    public AnimeListPresenterImpl(Executor executor, MainThread mainThread,
                                  View view, AnimeRepository animeRepository) {
        super(executor, mainThread);
        mView = view;
        mAnimeRepository = animeRepository;
    }

    @Override
    public void resume() {
        getAnimeList();
    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {
        getAnimesInteractor.cancel();
    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(String message) {
        mView.hideProgress();
    }

    @Override
    public void getAnimeList() {
        // get all costs
        getAnimesInteractor = new GetAllAnimesInteractorImpl(
                mExecutor,
                mMainThread,
                mAnimeRepository,
                this
        );

        getAnimesInteractor.execute();
        mView.showProgress();
    }

    @Override
    public void onAnimeListRetrieved(AnimeList animeList) {
//        List<Anime> animes = AnimeConverter.convertAnimesToAnimes(animeList);
        mView.showAnimeList(animeList);
        mView.hideProgress();
    }


}
