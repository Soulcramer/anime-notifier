package moe.notify.animenotifier.network.model.anime;

import org.junit.Before;
import org.junit.Test;

import moe.notify.animenotifier.domain.model.anime.AnimeRelation;

import static org.junit.Assert.assertEquals;

/**
 * Created by GOKU on 27/08/2016.
 */
public class RESTAnimeRelationTest {
    private RESTAnimeRelation restAnimeRelation;
    private AnimeRelation domainRelation;

    @Before
    public void setUp() throws Exception {
        restAnimeRelation = new RESTAnimeRelation();
        restAnimeRelation.id = 1111111111L;
        restAnimeRelation.type = "restRelation";

        domainRelation = new AnimeRelation();
        domainRelation.id = 1111111112L;
        domainRelation.type = "domainRelation";
    }

    @Test
    public void convertToDomainModel() throws Exception {
        AnimeRelation dummyRelation = new AnimeRelation();
        restAnimeRelation.convertToDomainModel(dummyRelation);
        assertEquals(1111111111L, dummyRelation.id);
        assertEquals("restRelation", dummyRelation.type);
    }

    @Test
    public void convertToNetworkModel() throws Exception {
        RESTAnimeRelation dummyRelation = new RESTAnimeRelation();
        dummyRelation.convertToNetworkModel(domainRelation);
        assertEquals(1111111112L, dummyRelation.id);
        assertEquals("domainRelation", dummyRelation.type);

    }

}