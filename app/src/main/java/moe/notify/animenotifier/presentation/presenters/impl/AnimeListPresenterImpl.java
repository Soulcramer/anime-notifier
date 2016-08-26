package moe.notify.animenotifier.presentation.presenters.impl;

import moe.notify.animenotifier.domain.executor.Executor;
import moe.notify.animenotifier.domain.executor.MainThread;
import moe.notify.animenotifier.domain.interactors.GetAllAnimesInteractor;
import moe.notify.animenotifier.domain.interactors.impl.GetAllAnimesInteractorImpl;
import moe.notify.animenotifier.domain.model.animelist.AnimeList;
import moe.notify.animenotifier.domain.repository.AnimeListRepository;
import moe.notify.animenotifier.presentation.presenters.AnimeListPresenter;
import moe.notify.animenotifier.presentation.presenters.base.AbstractPresenter;


public class AnimeListPresenterImpl extends AbstractPresenter implements AnimeListPresenter, GetAllAnimesInteractor.Callback {

    private final String username = "Scott";
    private AnimeListPresenter.View view;
    private AnimeListRepository animeListRepository;
    private GetAllAnimesInteractorImpl getAnimesInteractor;

    public AnimeListPresenterImpl(Executor executor, MainThread mainThread,
                                  View view, AnimeListRepository animeListRepository) {
        super(executor, mainThread);
        this.view = view;
        this.animeListRepository = animeListRepository;
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
        view.hideProgress();
    }

    @Override
    public void getAnimeList() {
        // get all costs
        getAnimesInteractor = new GetAllAnimesInteractorImpl(
                mExecutor,
                mMainThread,
                animeListRepository,
                this,
                username
        );

        getAnimesInteractor.execute();
        view.showProgress();
    }

    @Override
    public void onAnimeListRetrieved(AnimeList animeList) {
//        List<Anime> animes = AnimeConverter.convertAnimesToAnimes(animeList);
        view.showAnimeList(animeList);
        view.hideProgress();
    }


}
