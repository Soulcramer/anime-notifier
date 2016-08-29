package moe.notify.animenotifier.network.model.anime;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import moe.notify.animenotifier.domain.model.anime.AnimeOpening;

import static junit.framework.Assert.assertEquals;

/**
 * Created by GOKU on 27/08/2016.
 */
public class RESTAnimeOpeningTest {

    private RESTAnimeOpening restOpening;
    private AnimeOpening opening;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        restOpening = new RESTAnimeOpening();
        restOpening.similarity = 0.256;
        restOpening.permalink = "restPermalink";
        restOpening.uri = "restUri";
        restOpening.title = "restTitle";
        restOpening.likes = 10;
        restOpening.plays = 11;

        opening = new AnimeOpening();
        opening.similarity = 0.125;
        opening.permalink = "domainPermalink";
        opening.uri = "domainUri";
        opening.title = "domainTitle";
        opening.likes = 5;
        opening.plays = 6;
    }

    @Test
    public void convertToDomainModel() throws Exception {
        AnimeOpening dummyAnimeOpening = new AnimeOpening();
        restOpening.convertToDomainModel(dummyAnimeOpening);
        assertEquals(0.256, dummyAnimeOpening.similarity);
        assertEquals("restPermalink", dummyAnimeOpening.permalink);
        assertEquals("restUri", dummyAnimeOpening.uri);
        assertEquals("restTitle", dummyAnimeOpening.title);
        assertEquals(10, dummyAnimeOpening.likes);
        assertEquals(11, dummyAnimeOpening.plays);
    }

    @Test
    public void convertToNetworkModel() throws Exception {
        RESTAnimeOpening dummyRESTAnimeOpening = new RESTAnimeOpening();
        dummyRESTAnimeOpening.convertToNetworkModel(opening);
        assertEquals(0.125, dummyRESTAnimeOpening.similarity);
        assertEquals("domainPermalink", dummyRESTAnimeOpening.permalink);
        assertEquals("domainUri", dummyRESTAnimeOpening.uri);
        assertEquals("domainTitle", dummyRESTAnimeOpening.title);
        assertEquals(5, dummyRESTAnimeOpening.likes);
        assertEquals(6, dummyRESTAnimeOpening.plays);
    }

}