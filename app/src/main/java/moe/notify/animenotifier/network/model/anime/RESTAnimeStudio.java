package moe.notify.animenotifier.network.model.anime;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import moe.notify.animenotifier.domain.model.DomainModel;
import moe.notify.animenotifier.domain.model.anime.AnimeStudio;
import moe.notify.animenotifier.network.model.NetworkModel;


public class RESTAnimeStudio implements NetworkModel {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("wiki")
    @Expose
    public String wiki;
    @SerializedName("isMainStudio")
    @Expose
    public int isMainStudio;
    @SerializedName("id")
    @Expose
    public long id;


    @Override
    public void convertToDomainModel(DomainModel domainModel) {
        AnimeStudio studio = (AnimeStudio) domainModel;
        studio.id = id;
        studio.mainStudio = isMainStudio;
        studio.name = name;
        studio.wiki = wiki;
    }

    @Override
    public void convertToNetworkModel(DomainModel domainModel) {
        AnimeStudio studio = (AnimeStudio) domainModel;
        id = studio.id;
        isMainStudio = studio.mainStudio;
        name = studio.name;
        wiki = studio.wiki;

    }
}
