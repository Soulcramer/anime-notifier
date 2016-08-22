package moe.notify.animenotifier.domain.model.animelist;

import java.util.ArrayList;
import java.util.List;

import moe.notify.animenotifier.domain.model.anime.Anime;


public class AnimeList {

    public String user;
    public String listProvider;
    public String listUrl;
    public List<Anime> animes = new ArrayList<>();
    public String cacheKey;
    public String generated;
    public String titleLanguage;


}
