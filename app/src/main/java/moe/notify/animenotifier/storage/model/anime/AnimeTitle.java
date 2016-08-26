package moe.notify.animenotifier.storage.model.anime;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.OneToMany;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

import moe.notify.animenotifier.storage.database.AnimeDatabase;


@ModelContainer
@Table(database = AnimeDatabase.class)
public final class AnimeTitle extends BaseModel {

    @Column
    public String japanese;
    @Column
    public String english;
    public List<AnimeSynonym> synonyms;
    @PrimaryKey
    @Column
    public String romaji;

    @OneToMany(methods = {OneToMany.Method.ALL}, variableName = "synonyms")
    public List<AnimeSynonym> getSynonyms() {
        if ((synonyms == null) || synonyms.isEmpty()) {
            synonyms = SQLite.select()
                    .from(AnimeSynonym.class)
                    .where(AnimeSynonym_Table.animeTitle_romaji.eq(romaji))
                    .queryList();
        }
        return synonyms;
    }

}
