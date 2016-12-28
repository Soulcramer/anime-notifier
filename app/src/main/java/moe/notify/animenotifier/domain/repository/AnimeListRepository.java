package moe.notify.animenotifier.domain.repository;

import java.util.List;
import moe.notify.animenotifier.domain.model.animelist.AnimeList;

public interface AnimeListRepository {

    void insert(AnimeList animeList);

    void update(AnimeList animeList);

    AnimeList getByUser(String user);

    List<AnimeList> getAll();

    List<AnimeList> getAllUnsynced();


    void markSynced(List<AnimeList> animeLists);

    void delete(AnimeList animeList);

}
