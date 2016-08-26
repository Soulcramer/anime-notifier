package moe.notify.animenotifier.storage.model.anime;

import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import moe.notify.animenotifier.storage.database.AnimeDatabase;


@Table(
        database = AnimeDatabase.class
)
@ModelContainer
public final class Anime_AnimeGenre extends BaseModel {


//    @ForeignKey
//    @PrimaryKey
//    public AnimeGenre animeGenre;

    @ForeignKey
    @PrimaryKey
    public Anime anime;

}
