package moe.notify.animenotifier.network.model.anime;

import org.junit.Before;
import org.junit.Test;

import moe.notify.animenotifier.domain.model.anime.AnimeTracks;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

/**
 * Created by GOKU on 27/08/2016.
 */
public class RESTAnimeTracksTest {
    private RESTAnimeTracks restTracks;
    private AnimeTracks tracks;

    @Before
    public void setUp() throws Exception {
        restTracks = new RESTAnimeTracks();
        tracks = new AnimeTracks();
    }

    @Test
    public void convertToDomainModel() throws Exception {
        AnimeTracks dummyTracks = new AnimeTracks();
        assertNull(dummyTracks.opening);
        assertNull(restTracks.opening);
        restTracks.convertToDomainModel(dummyTracks);
        assertNotNull(dummyTracks.opening);
        assertNotNull(restTracks.opening);
    }

    @Test
    public void convertToNetworkModel() throws Exception {
        RESTAnimeTracks dummyTracks = new RESTAnimeTracks();
        assertNull(dummyTracks.opening);
        assertNull(tracks.opening);
        dummyTracks.convertToNetworkModel(tracks);
        assertNotNull(dummyTracks.opening);
        assertNotNull(tracks.opening);
    }

}