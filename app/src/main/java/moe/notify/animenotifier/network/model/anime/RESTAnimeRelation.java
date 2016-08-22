package moe.notify.animenotifier.network.model.anime;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class RESTAnimeRelation {

    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("id")
    @Expose
    public long id;

}
