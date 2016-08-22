package moe.notify.animenotifier.presentation.presenters;

import moe.notify.animenotifier.domain.model.animelist.AnimeList;
import moe.notify.animenotifier.presentation.presenters.base.BasePresenter;
import moe.notify.animenotifier.presentation.ui.BaseView;


public interface AnimeListPresenter extends BasePresenter {

    void getAnimeList();

    interface View extends BaseView {

        void showAnimeList(AnimeList animes);

        void onClickDownload(long animeId, int position);

    }


}
