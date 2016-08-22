package moe.notify.animenotifier.storage.model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import moe.notify.animenotifier.storage.database.AnimeDatabase;

@Table(database = AnimeDatabase.class)
@ModelContainer
public final class Anime_AnimeList extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    int id;
//
//    @ForeignKey
//    @Unique
//    ForeignKeyContainer<Anime> anime;

    // FIXME: 21/08/2016 Related to Anime Fixme
//    @ForeignKey
//    @Unique
//    ForeignKeyContainer<AnimeList>  animeList;
//
//
//    public void associateAnime(Anime anime) {
//        this.anime = FlowManager.getContainerAdapter(Anime.class)
//                .toForeignKeyContainer(anime); // convenience conversion
//    }
//
//    public void associateAnimeList(AnimeList animeList) {
//        this.animeList = FlowManager.getContainerAdapter(AnimeList.class)
//                .toForeignKeyContainer(animeList); // convenience conversion
//    }


}
