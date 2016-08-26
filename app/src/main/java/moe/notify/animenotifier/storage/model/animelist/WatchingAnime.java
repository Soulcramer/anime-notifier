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
import moe.notify.animenotifier.storage.model.anime.AnimeTitle;

@ModelContainer
@Table(database = AnimeDatabase.class)
public class WatchingAnime extends BaseModel {

    @Column
    @ForeignKey
    public ForeignKeyContainer<AnimeProvider> animeProvider;
    @Column
    @ForeignKey
    public ForeignKeyContainer<AiringDate> airingDate;
    @Column
    public String preferredTitle;
    @Column
    @ForeignKey
    public ForeignKeyContainer<AnimeTitle> title;
    @Column
    public String image;
    @PrimaryKey
    @Column
    public Long id;
    @Column
    @ForeignKey
    public ForeignKeyContainer<Episodes> episodes;
//
//    List<AnimeList_WatchingAnime> animeLists;
//
//    @OneToMany(methods = OneToMany.Method.ALL, variableName = "animeLists")
//    public List<AnimeList_WatchingAnime> getAnimeLists() {
//        if ((animeLists == null) || animeLists.isEmpty()) {
//            animeLists = SQLite.select()
//                    .from(AnimeList_WatchingAnime.class)
//                    .where(AnimeList_Watching_Table.watching_id.eq(id))
//                    .queryList();
//        }
//        return animeLists;
//    }

    public void associateTitle(AnimeTitle title) {
        this.title = FlowManager.getContainerAdapter(AnimeTitle.class)
                .toForeignKeyContainer(title); // convenience conversion
    }

    //    public void associateAnimeList(AnimeList animeList) {
//        this.animeList = FlowManager.getContainerAdapter(AnimeList.class)
//                .toForeignKeyContainer(animeList); // convenience conversion
//    }
    public void associateEpisodes(Episodes episodes) {
        this.episodes = FlowManager.getContainerAdapter(Episodes.class)
                .toForeignKeyContainer(episodes); // convenience conversion
    }

    public void associateAnimeProvider(AnimeProvider animeProvider) {
        this.animeProvider = FlowManager.getContainerAdapter(AnimeProvider.class)
                .toForeignKeyContainer(animeProvider); // convenience conversion
    }

    public void associateAiringDate(AiringDate airingDate) {
        this.airingDate = FlowManager.getContainerAdapter(AiringDate.class)
                .toForeignKeyContainer(airingDate); // convenience conversion
    }
}
