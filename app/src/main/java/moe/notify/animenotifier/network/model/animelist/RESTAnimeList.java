package moe.notify.animenotifier.network.model.animelist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class RESTAnimeList {

    @SerializedName("user")
    @Expose
    public String user;
    @SerializedName("listProvider")
    @Expose
    public String listProvider;
    @SerializedName("listUrl")
    @Expose
    public String listUrl;
    @SerializedName("watching")
    @Expose
    public List<RESTWatchingAnime> watchings = new ArrayList<>();
    @SerializedName("cacheKey")
    @Expose
    public String cacheKey;
    @SerializedName("generated")
    @Expose
    public String generated;
    @SerializedName("titleLanguage")
    @Expose
    public String titleLanguage;

}
