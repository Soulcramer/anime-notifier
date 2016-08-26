package moe.notify.animenotifier.storage.model.anime;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import moe.notify.animenotifier.storage.database.AnimeDatabase;


@Table(database = AnimeDatabase.class)
public class AnimeOpening extends BaseModel {

    @Column
    public float similarity;
    @Column
    public String permalink;
    @Column
    public String uri;
    @PrimaryKey
    @Column
    public String title;
    @Column
    public int likes;
    @Column
    public int plays;
}
