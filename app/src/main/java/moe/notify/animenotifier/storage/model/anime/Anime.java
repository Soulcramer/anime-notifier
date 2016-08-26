package moe.notify.animenotifier.storage.model.anime;

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
    public String hashtag;
    @Column
    public long anilistEdited;
//    @Column
//    @ForeignKey
//    public AnimeTracks tracks;



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

    public void associateTitle(AnimeTitle title) {
        this.title = FlowManager.getContainerAdapter(AnimeTitle.class)
                .toForeignKeyContainer(title); // convenience conversion
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



}
