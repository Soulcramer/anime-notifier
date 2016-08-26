package moe.notify.animenotifier.storage.model.animelist;

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
public class AnimeProvider extends BaseModel {

    @Column
    @ForeignKey(saveForeignKeyModel = false)
    public ForeignKeyContainer<NextEpisode> nextEpisode;
    @Column
    public long available;
    @Column
    @PrimaryKey
    public String url;
    @Column
    public String type;
    @Column
    public String rssUrl;

    public void associateNextEpisode(NextEpisode nextEpisode) {
        this.nextEpisode = FlowManager.getContainerAdapter(NextEpisode.class)
                .toForeignKeyContainer(nextEpisode); // convenience conversion
    }
}
