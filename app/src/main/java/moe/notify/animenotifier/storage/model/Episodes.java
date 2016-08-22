package moe.notify.animenotifier.storage.model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import moe.notify.animenotifier.storage.database.AnimeDatabase;


@ModelContainer
@Table(database = AnimeDatabase.class)
public class Episodes extends BaseModel {

    // I should relearn how to SQL :thinking_face:

    @PrimaryKey
    @Column
    public long mMax;
    @PrimaryKey
    @Column
    public long mAvailable;
    @PrimaryKey
    @Column
    public long mWatched;
    @PrimaryKey
    @Column
    public long mNext;
    @PrimaryKey
    @Column
    public long mOffset;

}
