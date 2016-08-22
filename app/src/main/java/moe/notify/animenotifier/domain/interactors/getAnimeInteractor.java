package moe.notify.animenotifier.domain.interactors;

import moe.notify.animenotifier.domain.interactors.base.Interactor;
import moe.notify.animenotifier.domain.model.anime.Anime;

/**
 * Created by GOKU on 22/08/2016.
 */

public interface GetAnimeInteractor extends Interactor {
    interface Callback {
        void onAnimeRetrieved(Anime anime);
    }

}
