package moe.notify.animenotifier.storage.model.anime;

import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import moe.notify.animenotifier.storage.database.AnimeDatabase;

@ModelContainer
@Table(database = AnimeDatabase.class)
public class AnimeTracks extends BaseModel {

    @PrimaryKey
    @ForeignKey
    public AnimeOpening opening;

}
