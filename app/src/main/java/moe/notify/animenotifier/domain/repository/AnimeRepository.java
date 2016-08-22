package moe.notify.animenotifier.domain.repository;

import java.util.List;

import moe.notify.animenotifier.domain.model.anime.Anime;
import moe.notify.animenotifier.domain.model.animelist.AnimeList;


public interface AnimeRepository {

    void insert(Anime anime);

    void update(Anime anime);

    Anime getAnimeById(long id);

    AnimeList getAnimeListByUser(String user);

    List<Anime> getAllAnimes();

    List<Anime> getAllUnsyncedAnimes();

    void updateAllAnimes();

    void markSynced(List<Anime> animes);

    void delete(Anime anime);

}
