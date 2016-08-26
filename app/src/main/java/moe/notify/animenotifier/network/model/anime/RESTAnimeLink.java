package moe.notify.animenotifier.network.model.anime;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import moe.notify.animenotifier.domain.model.DomainModel;
import moe.notify.animenotifier.domain.model.anime.AnimeLink;
import moe.notify.animenotifier.network.model.NetworkModel;


public class RESTAnimeLink implements NetworkModel {

    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("title")
    @Expose
    public String title;


    @Override
    public void convertToDomainModel(DomainModel domainModel) {
        AnimeLink link = (AnimeLink) domainModel;
        link.title = title;
        link.url = url;
    }

    @Override
    public void convertToNetworkModel(DomainModel domainModel) {
        AnimeLink link = (AnimeLink) domainModel;
        title = link.title;
        url = link.url;
    }


}
