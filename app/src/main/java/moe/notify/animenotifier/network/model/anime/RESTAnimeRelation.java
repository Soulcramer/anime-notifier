package moe.notify.animenotifier.network.model.anime;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import moe.notify.animenotifier.domain.model.DomainModel;
import moe.notify.animenotifier.domain.model.anime.AnimeRelation;
import moe.notify.animenotifier.network.model.NetworkModel;


public class RESTAnimeRelation implements NetworkModel {

    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("id")
    @Expose
    public long id;

    @Override
    public void convertToDomainModel(DomainModel domainModel) {
        AnimeRelation relation = (AnimeRelation) domainModel;
        relation.id = id;
        relation.type = type;
    }

    @Override
    public void convertToNetworkModel(DomainModel domainModel) {
        AnimeRelation relation = (AnimeRelation) domainModel;
        id = relation.id;
        type = relation.type;
    }
}
