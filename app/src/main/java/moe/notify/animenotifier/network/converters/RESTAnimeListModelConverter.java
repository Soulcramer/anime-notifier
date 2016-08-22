package moe.notify.animenotifier.network.converters;

import moe.notify.animenotifier.domain.model.animelist.AnimeList;
import moe.notify.animenotifier.network.model.animelist.RESTAnimeList;


public final class RESTAnimeListModelConverter {


    private RESTAnimeListModelConverter() {
    }

    public static void convertToDomainModel(RESTAnimeList animeList, AnimeList resultAnimeList) {
        resultAnimeList.user = animeList.user;
        resultAnimeList.listProvider = animeList.listProvider;
        resultAnimeList.listUrl = animeList.listUrl;
        RESTAnimeModelConverter.convertListToDomainModel(animeList.animes, resultAnimeList.animes);
        resultAnimeList.cacheKey = animeList.cacheKey;
        resultAnimeList.generated = animeList.generated;
        resultAnimeList.titleLanguage = animeList.titleLanguage;
    }

    // TODO: 17/08/2016 complete convertToDomainModel
    public static void convertToRestModel(AnimeList animeList, RESTAnimeList resultAnimeList) {
//        resultAnimeList.user = animeList.user;
//        resultAnimeList.listProvider = animeList.listProvider;
//        resultAnimeList.listUrl = animeList.listUrl;
//        RESTAnimeModelConverter.convertListToDomainModel(animeList.animes,resultAnimeList.animes);
//        resultAnimeList.cacheKey = animeList.cacheKey;
//        resultAnimeList.generated = animeList.generated;
//        resultAnimeList.titleLanguage = animeList.titleLanguage;
    }
}
