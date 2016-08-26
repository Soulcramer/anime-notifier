package moe.notify.animenotifier.network.converters;

import java.util.ArrayList;

import moe.notify.animenotifier.domain.model.anime.AnimeTitle;
import moe.notify.animenotifier.domain.model.animelist.AiringDate;
import moe.notify.animenotifier.domain.model.animelist.AnimeList;
import moe.notify.animenotifier.domain.model.animelist.AnimeProvider;
import moe.notify.animenotifier.domain.model.animelist.Episodes;
import moe.notify.animenotifier.domain.model.animelist.NextEpisode;
import moe.notify.animenotifier.domain.model.animelist.WatchingAnime;
import moe.notify.animenotifier.network.model.anime.RESTAnimeTitle;
import moe.notify.animenotifier.network.model.animelist.RESTAnimeList;
import moe.notify.animenotifier.network.model.animelist.RESTWatchingAnime;


public final class RESTAnimeListModelConverter {


    private RESTAnimeListModelConverter() {
    }

    public static void convertToDomainModel(RESTAnimeList animeList, AnimeList resultAnimeList) {
        resultAnimeList.user = animeList.user;
        resultAnimeList.listProvider = animeList.listProvider;
        resultAnimeList.listUrl = animeList.listUrl;
        int watchingLenght = animeList.watchings.size();
        for (int i = 0; i < watchingLenght; i++) {
            WatchingAnime watching = new WatchingAnime();
            RESTAnimeListModelConverter.convertWatchingToDomainModel(animeList.watchings.get(i), watching);
            resultAnimeList.animes.add(watching);
        }
        resultAnimeList.cacheKey = animeList.cacheKey;
        resultAnimeList.generated = animeList.generated;
        resultAnimeList.titleLanguage = animeList.titleLanguage;
    }

    public static void convertWatchingToDomainModel(RESTWatchingAnime anime, WatchingAnime resultAnime) {
        resultAnime.id = anime.id;


        RESTAnimeTitle title = anime.title;
        resultAnime.title = new AnimeTitle();
        resultAnime.title.synonyms = new ArrayList<>();
        for (int i = 0; i < title.synonyms.size(); i++) {
            resultAnime.title.synonyms.add(title.synonyms.get(i));
        }
        resultAnime.title.romaji = title.romaji;
        resultAnime.title.japanese = title.romaji;
        resultAnime.title.english = title.english;

        resultAnime.image = anime.image;

        resultAnime.episodes = new Episodes();
        resultAnime.episodes.available = anime.episodes.available;
        resultAnime.episodes.max = anime.episodes.max;
        resultAnime.episodes.next = anime.episodes.next;
        resultAnime.episodes.offset = anime.episodes.offset;
        resultAnime.episodes.watched = anime.episodes.watched;


        resultAnime.animeProvider = new AnimeProvider();
        resultAnime.animeProvider.available = anime.animeProvider.available;
        resultAnime.animeProvider.nextEpisode = new NextEpisode();
        resultAnime.animeProvider.nextEpisode.url = (anime.animeProvider.nextEpisode == null) ? "" : anime.animeProvider.nextEpisode.url;
        resultAnime.animeProvider.rssUrl = anime.animeProvider.rssUrl;
        resultAnime.animeProvider.type = anime.animeProvider.type;
        resultAnime.animeProvider.url = anime.animeProvider.url;

        resultAnime.airingDate = new AiringDate();
        resultAnime.airingDate.remaining = anime.airingDate.remaining;
        resultAnime.airingDate.remainingString = anime.airingDate.remainingString;
        resultAnime.airingDate.timeStamp = anime.airingDate.timeStamp;

        resultAnime.preferredTitle = anime.preferredTitle;
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
