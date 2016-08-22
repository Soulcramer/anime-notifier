package moe.notify.animenotifier.storage.model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import moe.notify.animenotifier.storage.database.AnimeDatabase;


@ModelContainer
@Table(database = AnimeDatabase.class)
public class AiringDate extends BaseModel {

    @PrimaryKey
    @Column
    public long mTimeStamp;
    @Column
    public String mRemainingString;
    @Column
    public long mRemaining;

}
