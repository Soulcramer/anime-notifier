package moe.notify.animenotifier.network.model.anime;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import moe.notify.animenotifier.domain.model.anime.AnimeStudio;

import static org.junit.Assert.assertEquals;

/**
 * Created by GOKU on 27/08/2016.
 */
public class RESTAnimeStudioTest {
    private RESTAnimeStudio restStudio;
    private AnimeStudio studio;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        restStudio = new RESTAnimeStudio();
        restStudio.id = 123456L;
        restStudio.isMainStudio = 0;
        restStudio.name = "restName";
        restStudio.wiki = "restWiki";

        studio = new AnimeStudio();
        studio.id = 654321L;
        studio.mainStudio = 1;
        studio.name = "domainName";
        studio.wiki = "domainWiki";
    }

    @Test
    public void convertToDomainModel() throws Exception {
        AnimeStudio dummyStudio = new AnimeStudio();
        restStudio.convertToDomainModel(dummyStudio);
        assertEquals(123456L, dummyStudio.id);
        assertEquals(0, dummyStudio.mainStudio);
        assertEquals("restName", dummyStudio.name);
        assertEquals("restWiki", dummyStudio.wiki);
    }

    @Test
    public void convertToNetworkModel() throws Exception {
        RESTAnimeStudio dummyStudio = new RESTAnimeStudio();
        dummyStudio.convertToNetworkModel(studio);
        assertEquals(654321L, dummyStudio.id);
        assertEquals(1, dummyStudio.isMainStudio);
        assertEquals("domainName", dummyStudio.name);
        assertEquals("domainWiki", dummyStudio.wiki);
    }

}