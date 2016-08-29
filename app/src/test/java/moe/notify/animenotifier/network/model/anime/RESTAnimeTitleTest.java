package moe.notify.animenotifier.network.model.anime;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import moe.notify.animenotifier.domain.model.anime.AnimeTitle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by GOKU on 27/08/2016.
 */
public class RESTAnimeTitleTest {
    private RESTAnimeTitle restTitle;
    private AnimeTitle title;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        restTitle = new RESTAnimeTitle();
        restTitle.english = "Attack on Titan";
        restTitle.japanese = "進撃の巨人";
        restTitle.romaji = "Shingeki no Kyojin";
        restTitle.synonyms = new ArrayList<>();
        restTitle.synonyms.add("Snk");
        restTitle.synonyms.add("AoT");

        title = new AnimeTitle();
        title.english = "Attack on Titan";
        title.japanese = "進撃の巨人";
        title.romaji = "Shingeki no Kyojin";
        title.synonyms = new ArrayList<>();
    }

    @Test
    public void convertToDomainModel() throws Exception {
        AnimeTitle dummytitle = new AnimeTitle();
        restTitle.convertToDomainModel(dummytitle);
        assertEquals("Attack on Titan", dummytitle.english);
        assertEquals("進撃の巨人", dummytitle.japanese);
        assertEquals("Shingeki no Kyojin", dummytitle.romaji);
        assertEquals(2, dummytitle.synonyms.size());
        assertEquals("Snk", dummytitle.synonyms.get(0));
        assertEquals("AoT", dummytitle.synonyms.get(1));
    }

    @Test
    public void convertToNetworkModel() throws Exception {
        RESTAnimeTitle dummytitle = new RESTAnimeTitle();
        dummytitle.convertToNetworkModel(title);
        assertEquals("Attack on Titan", dummytitle.english);
        assertEquals("進撃の巨人", dummytitle.japanese);
        assertEquals("Shingeki no Kyojin", dummytitle.romaji);
        assertTrue(dummytitle.synonyms.isEmpty());
    }

}