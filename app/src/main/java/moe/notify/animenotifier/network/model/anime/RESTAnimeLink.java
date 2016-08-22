package moe.notify.animenotifier.network.model.anime;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class RESTAnimeLink {

    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("title")
    @Expose
    public String title;
}
