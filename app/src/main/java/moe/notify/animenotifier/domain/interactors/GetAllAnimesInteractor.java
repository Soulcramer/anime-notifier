package moe.notify.animenotifier.domain.interactors;

import moe.notify.animenotifier.domain.interactors.base.Interactor;
import moe.notify.animenotifier.domain.model.animelist.AnimeList;


public interface GetAllAnimesInteractor extends Interactor {

    interface Callback {
        void onAnimeListRetrieved(AnimeList animeList);
    }
}
