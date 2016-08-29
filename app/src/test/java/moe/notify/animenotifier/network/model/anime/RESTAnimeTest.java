package moe.notify.animenotifier.network.model.anime;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import moe.notify.animenotifier.domain.model.anime.Anime;
import moe.notify.animenotifier.domain.model.anime.AnimeTitle;
import moe.notify.animenotifier.domain.model.anime.AnimeTracks;

/**
 * Created by GOKU on 28/08/2016.
 */
public class RESTAnimeTest {
    private RESTAnime restAnime;
    private Anime anime;

    @Before
    public void setUp() throws Exception {
        restAnime = new RESTAnime();
        restAnime.id = 111111L;
        restAnime.description = "restDescription";
        restAnime.title = new RESTAnimeTitle();
        restAnime.adult = 0;
        restAnime.airingStatus = "airing";
        restAnime.anilistEdited = 101010L;
        restAnime.classification = "restClassification";
        restAnime.duration = 21;
        restAnime.endDate = new Date().toString();
        restAnime.startDate = new Date().toString();
        restAnime.genres = new ArrayList<>();
        restAnime.hashtag = "#rest";
        restAnime.image = "restImage";
        restAnime.links = new ArrayList<>();
        restAnime.relations = new ArrayList<>();
        restAnime.studios = new ArrayList<>();
        restAnime.source = "manga";
        restAnime.totalEpisodes = 25;
        restAnime.tracks = new RESTAnimeTracks();
        restAnime.type = "restType";
        restAnime.watching = 5;
        restAnime.youtubeId = "123456";

        anime = new Anime();
        anime.id = 111110L;
        anime.description = "domainDescription";
        anime.title = new AnimeTitle();
        anime.adult = 0;
        anime.airingStatus = "finish airing";
        anime.anilistEdited = 101011L;
        anime.classification = "domainClassification";
        anime.duration = 21;
        anime.endDate = new Date();
        anime.startDate = new Date();
        anime.genres = new ArrayList<>();
        anime.hashtag = "#domain";
        anime.image = "domainImage";
        anime.links = new ArrayList<>();
        anime.relations = new ArrayList<>();
        anime.studios = new ArrayList<>();
        anime.source = "novel";
        anime.totalEpisodes = 4;
        anime.tracks = new AnimeTracks();
        anime.type = "domainType";
        anime.watching = 10;
        anime.youtubeId = "654321";
    }

    @Test
    public void convertToDomainModel() throws Exception {
        Anime dummyAnime = new Anime();
        restAnime.convertToDomainModel(dummyAnime);
        Assert.assertEquals(111111L, dummyAnime.id);
    }

    @Test
    public void convertToNetworkModel() throws Exception {
        RESTAnime dummyAnime = new RESTAnime();
        dummyAnime.convertToNetworkModel(anime);
        Assert.assertEquals(111110L, dummyAnime.id);
    }

}