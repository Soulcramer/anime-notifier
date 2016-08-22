package moe.notify.animenotifier.network.model.anime;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class RESTAnimeStudio {

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


}
