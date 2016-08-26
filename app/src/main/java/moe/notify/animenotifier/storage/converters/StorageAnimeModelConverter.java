package moe.notify.animenotifier.storage.converters;

import java.util.ArrayList;
import java.util.List;

import moe.notify.animenotifier.storage.model.anime.Anime;
import moe.notify.animenotifier.storage.model.anime.AnimeLink;
import moe.notify.animenotifier.storage.model.anime.AnimeOpening;
import moe.notify.animenotifier.storage.model.anime.AnimeRelation;
import moe.notify.animenotifier.storage.model.anime.AnimeStudio;
import moe.notify.animenotifier.storage.model.anime.AnimeSynonym;
import moe.notify.animenotifier.storage.model.anime.AnimeTitle;
import moe.notify.animenotifier.storage.model.anime.AnimeTracks;
import moe.notify.animenotifier.storage.model.anime.Anime_AnimeRelation;


public final class StorageAnimeModelConverter {

    private StorageAnimeModelConverter() {
    }

//    All these boilerplate D:

    public static void convertToStorageModel(moe.notify.animenotifier.domain.model.anime.Anime anime, Anime result) {

        result.id = anime.id;
        result.type = anime.type;

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
        result.airingStatus = anime.airingStatus;
        result.adult = anime.adult != 0;
        result.watching = anime.watching;
        result.description = anime.description;
        result.startDate = anime.startDate;
        result.endDate = anime.endDate;
        result.youtubeId = anime.youtubeId;


//        AnimeGenre resultGenre = new AnimeGenre();
//        List<Anime_AnimeGenre> resultGenres = new ArrayList<>();
//        Anime_AnimeGenre resultAnime_AnimeGenre = new Anime_AnimeGenre();
//        for (int i = 0; i < anime.genres.size(); i++) {
//            resultGenre.name = anime.genres.get(i);
//            resultGenre.save();
//            resultAnime_AnimeGenre.anime = result;
//            resultAnime_AnimeGenre.animeGenre = resultGenre;
//            resultAnime_AnimeGenre.save();
//            resultGenres.add(resultAnime_AnimeGenre);
//        }
//        result.genres = resultGenres;
        result.source = anime.source;
        result.classification = anime.classification;
        result.totalEpisodes = anime.totalEpisodes;
        result.duration = anime.duration;

        result.hashtag = anime.hashtag;
        result.anilistEdited = anime.anilistEdited;


        AnimeLink resultLink = new AnimeLink();

        List<AnimeLink> resultLinks = new ArrayList<>();
        for (int i = 0; i < anime.links.size(); i++) {
            resultLink.url = anime.links.get(i).url;
            resultLink.title = anime.links.get(i).title;
            resultLink.associateAnime(result);
            resultLink.save();
            resultLinks.add(resultLink);
        }
        result.links = resultLinks;

        AnimeStudio resultStudio = new AnimeStudio();
        List<AnimeStudio> resulStudios = new ArrayList<>();
        for (int i = 0; i < anime.studios.size(); i++) {

            resultStudio.studioId = anime.studios.get(i).id;
            resultStudio.name = anime.studios.get(i).name;
            resultStudio.wiki = anime.studios.get(i).wiki;
            resultStudio.isMainStudio = anime.studios.get(i).mainStudio != 0;
            resultStudio.associateAnime(result);
            resultStudio.save();
            resulStudios.add(resultStudio);
        }
        result.studios = resulStudios;

        Anime_AnimeRelation resultAnime_AnimeRelation = new Anime_AnimeRelation();
        AnimeRelation resultRelation = new AnimeRelation();
        List<Anime_AnimeRelation> resulRelations = new ArrayList<>();
        for (int i = 0; i < anime.relations.size(); i++) {
            resultRelation.type = anime.relations.get(i).type;
            resultRelation.id = anime.relations.get(i).id;

            resultRelation.save();
            resultAnime_AnimeRelation.anime = result;
            resultAnime_AnimeRelation.animeRelation = resultRelation;
            resultAnime_AnimeRelation.save();
            resulRelations.add(resultAnime_AnimeRelation);
        }
        result.relations = resulRelations;

        AnimeTracks resultTracks = new AnimeTracks();
        AnimeOpening resultOpening = new AnimeOpening();
        resultOpening.likes = anime.tracks.opening.likes;
        resultOpening.permalink = anime.tracks.opening.permalink;
        resultOpening.plays = anime.tracks.opening.plays;
        resultOpening.similarity = anime.tracks.opening.similarity;
        resultOpening.title = anime.tracks.opening.title;
        resultOpening.uri = anime.tracks.opening.uri;
        resultOpening.save();


//        resultTracks.associateOpening(resultOpening);
//        resultTracks.save();

//        result.tracks = resultTracks;

        result.save();

    }

    public static void convertToDomainModel(Anime anime, moe.notify.animenotifier.domain.model.anime.Anime result) {

        result.id = anime.id;
        result.type = anime.type;

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
        result.airingStatus = anime.airingStatus;
        result.adult = anime.adult ? 1 : 0;
        result.watching = anime.watching;
        result.description = anime.description;
        result.startDate = anime.startDate;
        result.endDate = anime.endDate;
        result.youtubeId = anime.youtubeId;


//        List<String> resultGenres = new ArrayList<>();
//        List<Anime_AnimeGenre> genres = anime.getGenres();
//        for (int i = 0; i < genres.size(); i++) {
//            resultGenres.add(genres.get(i).animeGenre.name);
//        }
//        result.genres = resultGenres;

        result.source = anime.source;

        result.classification = anime.classification;

        result.totalEpisodes = anime.totalEpisodes;

        result.duration = anime.duration;

        result.hashtag = anime.hashtag;

        result.anilistEdited = anime.anilistEdited;

        List<moe.notify.animenotifier.domain.model.anime.AnimeLink> resultLinks = new ArrayList<>();
        List<AnimeLink> links = anime.getLinks();
        moe.notify.animenotifier.domain.model.anime.AnimeLink resultLink = new moe.notify.animenotifier.domain.model.anime.AnimeLink();
        for (int i = 0; i < links.size(); i++) {
            resultLink.title = links.get(i).title;
            resultLink.url = links.get(i).url;
            resultLinks.add(resultLink);
        }
        result.links = resultLinks;

        List<moe.notify.animenotifier.domain.model.anime.AnimeStudio> resultStudios = new ArrayList<>();
        List<AnimeStudio> studios = anime.getStudios();
        moe.notify.animenotifier.domain.model.anime.AnimeStudio resultStudio = new moe.notify.animenotifier.domain.model.anime.AnimeStudio();
        for (int i = 0; i < studios.size(); i++) {
            resultStudio.name = studios.get(i).name;
            resultStudio.id = studios.get(i).studioId;
            resultStudio.mainStudio = studios.get(i).isMainStudio ? 1 : 0;
            resultStudio.wiki = studios.get(i).wiki;
            resultStudios.add(resultStudio);
        }
        result.studios = resultStudios;

        List<moe.notify.animenotifier.domain.model.anime.AnimeRelation> resultRelations = new ArrayList<>();
        List<Anime_AnimeRelation> relations = anime.getRelations();
        moe.notify.animenotifier.domain.model.anime.AnimeRelation resultRelation = new moe.notify.animenotifier.domain.model.anime.AnimeRelation();
        for (int i = 0; i < relations.size(); i++) {
            resultRelation.id = relations.get(i).animeRelation.id;
            resultRelation.type = relations.get(i).animeRelation.type;
            resultRelations.add(resultRelation);
        }

        result.relations = resultRelations;


//        AnimeTracks tracks = anime.tracks;
//        moe.notify.animenotifier.domain.model.anime.AnimeTracks resultTracks = new moe.notify.animenotifier.domain.model.anime.AnimeTracks();
//        AnimeOpening opening = tracks.opening.load();
//        moe.notify.animenotifier.domain.model.anime.AnimeOpening resultOpening = new moe.notify.animenotifier.domain.model.anime.AnimeOpening();
//        resultOpening.likes = opening.likes;
//        resultOpening.permalink = opening.permalink;
//        resultOpening.plays = opening.plays;
//        resultOpening.similarity = opening.similarity;
//        resultOpening.title = opening.title;
//        resultOpening.uri = opening.uri;
//        resultTracks.opening = resultOpening;
//        result.tracks = resultTracks;
    }

    public static void convertListToDomainModel(List<Anime> animes, List<moe.notify.animenotifier.domain.model.anime.Anime> resultList) {

        for (int i = 0; i < animes.size(); i++) {
            convertToDomainModel(animes.get(i), resultList.get(i));
        }

        // cleanup
        animes.clear();

    }


    public static void convertListToStorageModel(List<moe.notify.animenotifier.domain.model.anime.Anime> animes, List<Anime> resultList) {
        List<Anime> convertedCosts = new ArrayList<>();

        for (int i = 0; i < animes.size(); i++) {
            convertToStorageModel(animes.get(i), resultList.get(i));
        }


        // cleanup
        animes.clear();
        animes = null;

    }



}
