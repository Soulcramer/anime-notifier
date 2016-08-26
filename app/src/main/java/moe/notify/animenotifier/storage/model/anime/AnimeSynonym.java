package moe.notify.animenotifier.storage.model.anime;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.container.ForeignKeyContainer;

import moe.notify.animenotifier.storage.database.AnimeDatabase;

@ModelContainer
@Table(database = AnimeDatabase.class)
public final class AnimeSynonym extends BaseModel {

    @PrimaryKey
    @Column
    public String name;


    @Column
    @ForeignKey(saveForeignKeyModel = false)
    ForeignKeyContainer<AnimeTitle> animeTitle;

    public void associateTitle(AnimeTitle title) {
        animeTitle = FlowManager.getContainerAdapter(AnimeTitle.class)
                .toForeignKeyContainer(title); // convenience conversion
    }

}
