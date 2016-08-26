package moe.notify.animenotifier.domain.model.animelist;

import moe.notify.animenotifier.domain.model.anime.AnimeTitle;

/**
 * Created by GOKU on 24/08/2016.
 */

public class WatchingAnime {


    public AnimeProvider animeProvider;
    public AiringDate airingDate;
    public String preferredTitle;
    public AnimeTitle title;
    public String image;
    public Long id;
    public Episodes episodes;
}
