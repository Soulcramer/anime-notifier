package moe.notify.animenotifier.presentation.presenters;

import moe.notify.animenotifier.domain.model.anime.Anime;
import moe.notify.animenotifier.presentation.presenters.base.BasePresenter;
import moe.notify.animenotifier.presentation.ui.BaseView;

/**
 * Created by GOKU on 22/08/2016.
 */

public interface AnimePresenter extends BasePresenter {

    void getAnime(long anime);


    interface View extends BaseView {

        /**
         * Show a complete set of information about the anime.
         *
         * @param anime the anime to be shown.
         */
        void showAnime(Anime anime);
    }
}
