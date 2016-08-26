package moe.notify.animenotifier.storage.converters;

import java.util.ArrayList;
import java.util.List;

import moe.notify.animenotifier.storage.model.anime.AnimeSynonym;
import moe.notify.animenotifier.storage.model.anime.AnimeTitle;
import moe.notify.animenotifier.storage.model.animelist.AnimeList;
import moe.notify.animenotifier.storage.model.animelist.AnimeProvider;
import moe.notify.animenotifier.storage.model.animelist.Episodes;
import moe.notify.animenotifier.storage.model.animelist.NextEpisode;
import moe.notify.animenotifier.storage.model.animelist.WatchingAnime;
import timber.log.Timber;

/**
 * Created by GOKU on 24/08/2016.
 */

public final class StorageAnimeListModelConverter {

    public static void convertAnimeListToStorageModel(
            moe.notify.animenotifier.domain.model.animelist.AnimeList animelist,
            AnimeList resultAnimeList) {
        Timber.d("Convert domain AnimeList into storage AnimeList");
        resultAnimeList.user = animelist.user;
        resultAnimeList.listProvider = animelist.listProvider;
//        AnimeList_WatchingAnime result_AnimeListWatching = new AnimeList_WatchingAnime();
//        for (int i = 0; i < animelist.animes.size(); i++) {
//            WatchingAnime resultanime = new WatchingAnime();
//            convertWatchingToStorageModel(animelist.animes.get(i),resultanime);
//            result_AnimeListWatching.setAnimeList(resultAnimeList);;
//            result_AnimeListWatching.setWatchingAnime(resultanime);
//            result_AnimeListWatching.save();
//        }
        resultAnimeList.cacheKey = animelist.cacheKey;
        resultAnimeList.generated = animelist.generated;
        resultAnimeList.listUrl = animelist.listUrl;
        resultAnimeList.titleLanguage = animelist.titleLanguage;
        resultAnimeList.save();

    }

    public static void convertWatchingToStorageModel(
            moe.notify.animenotifier.domain.model.animelist.WatchingAnime anime,
            WatchingAnime result
    ) {
        result.id = anime.id;

        AnimeTitle resulTitle = new AnimeTitle();
        resulTitle.romaji = anime.title.romaji;
        resulTitle.japanese = anime.title.japanese;

        List<AnimeSynonym> resultSynonyms = new ArrayList<>();

        AnimeSynonym resulSynonym = new AnimeSynonym();
        for (int i = 0; i < anime.title.synonyms.size(); i++) {
            resulSynonym.name = anime.title.synonyms.get(i);
            resulSynonym.associateTitle(resulTitle);
            resulSynonym.save();
            resultSynonyms.add(resulSynonym);
        }
        resulTitle.synonyms = resultSynonyms;
        resulTitle.english = anime.title.english;
        resulTitle.save();
        result.associateTitle(resulTitle);

        result.image = anime.image;

        result.preferredTitle = anime.preferredTitle;

        Episodes resultEpisodes = new Episodes();
        resultEpisodes.mAvailable = anime.episodes.available;
        resultEpisodes.mMax = anime.episodes.max;
        resultEpisodes.mNext = anime.episodes.next;
        resultEpisodes.mOffset = anime.episodes.offset;
        resultEpisodes.mWatched = anime.episodes.watched;
        result.associateEpisodes(resultEpisodes);
        resultEpisodes.save();

        AnimeProvider resultProvider = new AnimeProvider();
        resultProvider.available = anime.animeProvider.available;

        NextEpisode resultNextEpisode = new NextEpisode();
        resultNextEpisode.url = anime.animeProvider.nextEpisode.url;
        resultNextEpisode.save();

        resultProvider.associateNextEpisode(resultNextEpisode);
        resultProvider.rssUrl = anime.animeProvider.rssUrl;
        resultProvider.type = anime.animeProvider.type;
        resultProvider.url = anime.animeProvider.url;
        resultProvider.save();
        result.associateAnimeProvider(resultProvider);


    }


    public static void convertAnimeListToDomainModel(AnimeList animelist, moe.notify.animenotifier.domain.model.animelist.AnimeList resultAnimeList) {

        Timber.d("Convert storage AnimeList into domain AnimeList");
        resultAnimeList.user = animelist.user;
        resultAnimeList.listProvider = animelist.listProvider;
//        List<WatchingAnime> resultAnimes = new ArrayList<>();
//        List<moe.notify.animenotifier.storage.model.animelist.WatchingAnime> animes = new ArrayList<>();
//        for (int i = 0; i < animelist.animes.size(); i++) {
//            Anime resultanime = new Anime();
////            animes = animelist.getAnimes()
////            convertListToDomainModel(animelist.getAnimes(),resultAnimes);
////            resultAnime_AnimeList.animeList = resultAnimeList;
////            resultAnime_AnimeList.anime = resultanime;
////            resultAnime_AnimeList.save();
//        }
//        resultAnimeList.cacheKey = animelist.cacheKey;
//        resultAnimeList.generated = animelist.generated;
//        resultAnimeList.listUrl = animelist.listUrl;
//        resultAnimeList.titleLanguage = animelist.titleLanguage;
//        resultAnimeList.save();

    }

    public static void convertToDomainModel(WatchingAnime anime, moe.notify.animenotifier.domain.model.animelist.WatchingAnime result) {
        result.id = anime.id;

        AnimeTitle title = anime.title.load();
        List<String> resultTitleSynonyms = new ArrayList<>();
        for (int i = 0; i < title.getSynonyms().size(); i++) {
            resultTitleSynonyms.add(title.getSynonyms().get(i).name);
        }
        moe.notify.animenotifier.domain.model.anime.AnimeTitle resultTitle = new moe.notify.animenotifier.domain.model.anime.AnimeTitle();
        resultTitle.romaji = title.romaji;
        resultTitle.japanese = title.japanese;
        resultTitle.english = title.english;
        resultTitle.synonyms = resultTitleSynonyms;

        result.title = resultTitle;


        result.image = anime.image;

        result.preferredTitle = anime.preferredTitle;

        moe.notify.animenotifier.domain.model.animelist.Episodes resultEpisodes = new moe.notify.animenotifier.domain.model.animelist.Episodes();
        Episodes episodes = anime.episodes.load();
        resultEpisodes.available = episodes.mAvailable;
        resultEpisodes.max = episodes.mMax;
        resultEpisodes.next = episodes.mNext;
        resultEpisodes.offset = episodes.mOffset;
        resultEpisodes.watched = episodes.mWatched;
        result.episodes = resultEpisodes;

        moe.notify.animenotifier.domain.model.animelist.AnimeProvider resultProvider = new moe.notify.animenotifier.domain.model.animelist.AnimeProvider();
        AnimeProvider provider = anime.animeProvider.load();
        resultProvider.available = provider.available;

        moe.notify.animenotifier.domain.model.animelist.NextEpisode resultNextEpisode = new moe.notify.animenotifier.domain.model.animelist.NextEpisode();
        NextEpisode nextEpisode = provider.nextEpisode.load();
        resultNextEpisode.url = nextEpisode.url;
        resultProvider.nextEpisode = resultNextEpisode;

        resultProvider.rssUrl = provider.rssUrl;
        resultProvider.type = provider.type;
        resultProvider.url = provider.url;
        result.animeProvider = resultProvider;


    }
}
