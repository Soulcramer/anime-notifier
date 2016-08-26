package moe.notify.animenotifier.storage.model.anime;

import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import moe.notify.animenotifier.storage.database.AnimeDatabase;


@ModelContainer
@Table(database = AnimeDatabase.class)
public final class AnimeGenre extends BaseModel {

    @PrimaryKey
    public String name;


//    @Column
//    public List<Anime_AnimeGenre> animes;
//
//    public List<Anime_AnimeGenre> getAnimes() {
//        if ((animes == null) || animes.isEmpty()) {
//            animes = SQLite.select()
//                    .from(Anime_AnimeGenre.class)
//                    .where(Anime_AnimeGenre_Table.animeGenre_name.eq(name))
//                    .queryList();
//        }
//        return animes;
//    }
}
