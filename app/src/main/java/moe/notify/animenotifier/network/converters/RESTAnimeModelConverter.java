package moe.notify.animenotifier.network.converters;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import moe.notify.animenotifier.domain.model.anime.AiringDate;
import moe.notify.animenotifier.domain.model.anime.Anime;
import moe.notify.animenotifier.domain.model.anime.AnimeLink;
import moe.notify.animenotifier.domain.model.anime.AnimeOpening;
import moe.notify.animenotifier.domain.model.anime.AnimeProvider;
import moe.notify.animenotifier.domain.model.anime.AnimeRelation;
import moe.notify.animenotifier.domain.model.anime.AnimeStudio;
import moe.notify.animenotifier.domain.model.anime.AnimeTitle;
import moe.notify.animenotifier.domain.model.anime.AnimeTracks;
import moe.notify.animenotifier.domain.model.anime.Episodes;
import moe.notify.animenotifier.domain.model.anime.NextEpisode;
import moe.notify.animenotifier.network.model.anime.RESTAnime;
import moe.notify.animenotifier.network.model.anime.RESTAnimeLink;
import moe.notify.animenotifier.network.model.anime.RESTAnimeRelation;
import moe.notify.animenotifier.network.model.anime.RESTAnimeStudio;
import moe.notify.animenotifier.network.model.anime.RESTAnimeTitle;
import moe.notify.animenotifier.utils.DateUtils;


public final class RESTAnimeModelConverter {
    private RESTAnimeModelConverter() {
    }

//        public static RESTAnime convertToDomainModel(Anime anime) {

//            String desc = anime.getDescription();
//            double amount = anime.getAmount();
//            String category = anime.getCategory();
//            Date date = anime.getDate();
//            long id = anime.getId();
//
//            return new RESTAnime(id, category, desc, date, amount);
//        }

    public static void convertToDomainModel(RESTAnime anime, Anime resultAnime) {

        resultAnime.id = anime.id;
        resultAnime.type = anime.type;


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
        resultAnime.airingStatus = anime.airingStatus;
        resultAnime.adult = anime.adult;
        resultAnime.watching = anime.watching;
        resultAnime.description = anime.description;
        resultAnime.description.replace("<br>", "");
        resultAnime.startDate = (anime.startDate == null) ? new Date() : DateUtils.iso8601ToDate(anime.startDate);
        resultAnime.endDate = (anime.endDate == null) ? new Date() : DateUtils.iso8601ToDate(anime.endDate);
        resultAnime.youtubeId = anime.youtubeId;

        resultAnime.genres = anime.genres;

        resultAnime.source = anime.source;
        resultAnime.classification = anime.classification;
        resultAnime.totalEpisodes = anime.totalEpisodes;
        resultAnime.duration = anime.duration;
        resultAnime.preferredTitle = anime.preferredTitle;

        if (anime.episodes != null) {
            resultAnime.episodes = new Episodes();
            resultAnime.episodes.available = anime.episodes.available;
            resultAnime.episodes.max = anime.episodes.max;
            resultAnime.episodes.next = anime.episodes.next;
            resultAnime.episodes.offset = anime.episodes.offset;
            resultAnime.episodes.watched = anime.episodes.watched;
        }

        resultAnime.hashtag = anime.hashtag;
        resultAnime.anilistEdited = anime.anilistEdited;

        if (anime.animeProvider != null) {
            resultAnime.animeProvider = new AnimeProvider();
            resultAnime.animeProvider.available = anime.animeProvider.available;
            resultAnime.animeProvider.nextEpisode = new NextEpisode();
            resultAnime.animeProvider.nextEpisode.url = (anime.animeProvider.nextEpisode == null) ? "" : anime.animeProvider.nextEpisode.url;
            resultAnime.animeProvider.rssUrl = anime.animeProvider.rssUrl;
            resultAnime.animeProvider.type = anime.animeProvider.type;
            resultAnime.animeProvider.url = anime.animeProvider.url;
        }

        if (anime.airingDate != null) {
            resultAnime.airingDate = new AiringDate();
            resultAnime.airingDate.remaining = anime.airingDate.remaining;
            resultAnime.airingDate.remainingString = anime.airingDate.remainingString;
            resultAnime.airingDate.timeStamp = anime.airingDate.timeStamp;
        }

        List<RESTAnimeLink> links = anime.links;
        resultAnime.links = new ArrayList<>();
        for (int i = 0; i < links.size(); i++) {
            AnimeLink resultLink = new AnimeLink();
            resultLink.title = links.get(i).title;
            resultLink.url = links.get(i).url;
            resultAnime.links.add(resultLink);
        }

        List<RESTAnimeStudio> studios = anime.studios;
        resultAnime.studios = new ArrayList<>();
        for (int i = 0; i < studios.size(); i++) {
            AnimeStudio resultStudio = new AnimeStudio();
            resultStudio.name = studios.get(i).name;
            resultStudio.id = studios.get(i).id;
            resultStudio.mainStudio = studios.get(i).isMainStudio;
            resultStudio.wiki = studios.get(i).wiki;
            resultAnime.studios.add(resultStudio);
        }

        List<RESTAnimeRelation> relations = anime.relations;
        resultAnime.relations = new ArrayList<>();
        for (int i = 0; i < relations.size(); i++) {
            AnimeRelation resultRelation = new AnimeRelation();
            resultRelation.id = relations.get(i).id;
            resultRelation.type = relations.get(i).type;
            resultAnime.relations.add(resultRelation);
        }

        resultAnime.tracks = new AnimeTracks();
        resultAnime.tracks.opening = new AnimeOpening();
        if ((anime.tracks == null) || (anime.tracks.opening == null)) {
            resultAnime.tracks.opening.likes = 0;
            resultAnime.tracks.opening.permalink = "";
            resultAnime.tracks.opening.plays = 0;
            resultAnime.tracks.opening.similarity = 0;
            resultAnime.tracks.opening.title = "No Opening available";
            resultAnime.tracks.opening.uri = "";
        } else {
            resultAnime.tracks.opening.likes = anime.tracks.opening.likes;
            resultAnime.tracks.opening.permalink = anime.tracks.opening.permalink;
            resultAnime.tracks.opening.plays = anime.tracks.opening.plays;
            resultAnime.tracks.opening.similarity = anime.tracks.opening.similarity;
            resultAnime.tracks.opening.title = anime.tracks.opening.title;
            resultAnime.tracks.opening.uri = anime.tracks.opening.uri;
        }

    }

    public static void convertListToDomainModel(List<RESTAnime> animes, List<Anime> resultAnimes) {


        for (int i = 0; i < animes.size(); i++) {
            Anime anime = new Anime();
            RESTAnimeModelConverter.convertToDomainModel(animes.get(i), anime);
            resultAnimes.add(anime);
        }
//
//        // cleanup
        animes.clear();
        animes = null;
    }

    // TODO: 17/08/2016 add convertToRestModel and convertListToRestModel

}
