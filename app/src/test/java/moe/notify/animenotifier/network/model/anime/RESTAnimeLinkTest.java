package moe.notify.animenotifier.network.model.anime;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import moe.notify.animenotifier.domain.model.anime.AnimeLink;

import static junit.framework.Assert.assertEquals;

public class RESTAnimeLinkTest {


    private RESTAnimeLink restLink;
    private AnimeLink link;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        restLink = new RESTAnimeLink();
        restLink.title = "restTitle";
        restLink.url = "restUrl";

        link = new AnimeLink();
        link.title = "domainTitle";
        link.url = "domainUrl";

    }

    @Test
    public void testConvertToDomainModel() throws Exception {
        AnimeLink dummyAnimeLink = new AnimeLink();
        restLink.convertToDomainModel(dummyAnimeLink);
        assertEquals("restTitle", dummyAnimeLink.title);
        assertEquals("restUrl", dummyAnimeLink.url);

    }

    @Test
    public void convertToNetworkModel() throws Exception {
        RESTAnimeLink dummyRESTAnimeLink = new RESTAnimeLink();
        dummyRESTAnimeLink.convertToNetworkModel(link);
        assertEquals("domainTitle", dummyRESTAnimeLink.title);
        assertEquals("domainUrl", dummyRESTAnimeLink.url);
    }

}