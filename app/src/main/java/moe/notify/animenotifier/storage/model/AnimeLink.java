package moe.notify.animenotifier.storage.model;

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
public final class AnimeLink extends BaseModel {

    @PrimaryKey
    @Column
    public String url;

    @Column
    public String title;

    @ForeignKey
    public ForeignKeyContainer<Anime> anime;

    public void associateAnime(Anime anime) {
        this.anime = FlowManager.getContainerAdapter(Anime.class)
                .toForeignKeyContainer(anime); // convenience conversion
    }

}
