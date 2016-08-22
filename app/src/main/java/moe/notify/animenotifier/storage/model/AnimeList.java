package moe.notify.animenotifier.storage.model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import moe.notify.animenotifier.storage.database.AnimeDatabase;


@ModelContainer
@Table(database = AnimeDatabase.class)
public final class AnimeList extends BaseModel {

    @PrimaryKey
    @Column
    public String user;
    @PrimaryKey
    @Column
    public String listProvider;
    @Column
    public String listUrl;
    // FIXME: 21/08/2016 RElated to Anime FIXME
//    @Column
//    public List<Anime_AnimeList> animes;
    @Column
    public String cacheKey;
    @Column
    public String generated;
    @Column
    public String titleLanguage;


//    @OneToMany(methods = {OneToMany.Method.ALL}, variableName = "animes")
//    public List<Anime_AnimeList> getAnimes() {
//        if ((animes == null) || animes.isEmpty()) {
//            animes = SQLite.select()
//                    .from(Anime_AnimeList.class)
//                    .where(Anime_AnimeList_Table.animeList_user.eq(user))
//                    .queryList();
//        }
//        return animes;
//    }

}
