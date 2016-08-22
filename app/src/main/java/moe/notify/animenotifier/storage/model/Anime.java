package moe.notify.animenotifier.storage.model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.OneToMany;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.container.ForeignKeyContainer;

import java.util.Date;
import java.util.List;

import moe.notify.animenotifier.storage.database.AnimeDatabase;


@ModelContainer
@Table(database = AnimeDatabase.class)
public final class Anime extends BaseModel {


    @Column
    public boolean synced;
    @Column
    @ForeignKey
    public ForeignKeyContainer<AnimeTitle> title;

    // FIXME: 21/08/2016 Cannot build if we're doing a many to many relationship with animeLists.
//    public List<Anime_AnimeList> animeLists;

//    @ForeignKey
//    public ForeignKeyContainer<AnimeList> animeList;

    public List<AnimeLink> links;

//    public List<Anime_AnimeGenre> genres;

    public List<Anime_AnimeRelation> relations;

    public List<AnimeStudio> studios;
    @PrimaryKey
    public long id;
    @Column
    public String type;
    @Column
    public String image;
    @Column
    public String airingStatus;
    @Column
    public boolean adult;
    @Column
    public int watching;
    @Column
    public String description;
    @Column
    public Date startDate;
    @Column
    public Date endDate;
    @Column
    public String youtubeId;
    @Column
    public String source;
    @Column
    public String classification;
    @Column
    public int totalEpisodes;
    @Column
    public int duration;
    @Column
    public String preferredTitle;
    @Column
    @ForeignKey(saveForeignKeyModel = false)
    public ForeignKeyContainer<Episodes> episodes;
    @Column
    public String hashtag;
    @Column
    public long anilistEdited;
    @Column
    @ForeignKey(saveForeignKeyModel = false)
    public ForeignKeyContainer<AnimeProvider> animeProvider;
    @Column
    @ForeignKey(saveForeignKeyModel = false)
    public ForeignKeyContainer<AiringDate> airingDate;
//    @Column
//    @ForeignKey
//    public AnimeTracks tracks;


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


    @OneToMany(methods = {OneToMany.Method.ALL}, variableName = "links")
    public List<AnimeLink> getLinks() {
        if ((links == null) || links.isEmpty()) {
            links = SQLite.select()
                    .from(AnimeLink.class)
                    .where(AnimeLink_Table.anime_id.eq(id))
                    .queryList();
        }
        return links;
    }

    @OneToMany(methods = {OneToMany.Method.ALL}, variableName = "relations")
    public List<Anime_AnimeRelation> getRelations() {
        if ((relations == null) || relations.isEmpty()) {
            relations = SQLite.select()
                    .from(Anime_AnimeRelation.class)
                    .where(Anime_AnimeRelation_Table.anime_id.eq(id))
                    .queryList();
        }
        return relations;
    }

    @OneToMany(methods = {OneToMany.Method.ALL}, variableName = "studios")
    public List<AnimeStudio> getStudios() {
        if ((studios == null) || studios.isEmpty()) {
            studios = SQLite.select()
                    .from(AnimeStudio.class)
                    .where(AnimeStudio_Table.anime_id.eq(id))
                    .queryList();
        }
        return studios;
    }

//    @OneToMany(methods = {OneToMany.Method.ALL}, variableName = "genres")
//    public List<Anime_AnimeGenre> getGenres() {
//        if ((genres == null) || genres.isEmpty()) {
//            genres = SQLite.select()
//                    .from(Anime_AnimeGenre.class)
//                    .where(Anime_AnimeGenre_Table.anime_id.eq(id))
//                    .queryList();
//        }
//        return genres;
//    }

//    @OneToMany(methods = {OneToMany.Method.ALL}, variableName = "animeLists")
//    public List<Anime_AnimeList> getAnimeLists() {
//        if ((animeLists == null) || animeLists.isEmpty()) {
//            animeLists = SQLite.select()
//                    .from(Anime_AnimeList.class)
//                    .where(Anime_AnimeList_Table.anime_id.eq(id))
//                    .queryList();
//        }
//        return animeLists;
//    }


}
