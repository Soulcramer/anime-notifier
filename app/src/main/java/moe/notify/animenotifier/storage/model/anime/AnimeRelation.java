package moe.notify.animenotifier.storage.model.anime;

import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

import moe.notify.animenotifier.storage.database.AnimeDatabase;


@ModelContainer
@Table(database = AnimeDatabase.class)
public final class AnimeRelation extends BaseModel {

    @PrimaryKey
    public String type;

    @PrimaryKey
    public long id;


    public List<Anime_AnimeRelation> animes;

    public List<Anime_AnimeRelation> getAnimes() {
        if ((animes == null) || animes.isEmpty()) {
            animes = SQLite.select()
                    .from(Anime_AnimeRelation.class)
                    .where(Anime_AnimeRelation_Table.animeRelation_id.eq(id))
                    .and(Anime_AnimeRelation_Table.animeRelation_type.eq(type))
                    .queryList();
        }
        return animes;
    }

}
