package moe.notify.animenotifier.domain.repository;

import java.util.List;
import moe.notify.animenotifier.domain.model.anime.Anime;


public interface AnimeRepository {

    void insert(Anime anime);

    void update(Anime anime);

    Anime getById(long id);

    List<Anime> getAll();

    void updateAll();

    void markSynced(List<Anime> animes);

    void delete(Anime anime);

}
