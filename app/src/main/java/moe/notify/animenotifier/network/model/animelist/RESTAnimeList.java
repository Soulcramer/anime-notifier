package moe.notify.animenotifier.network.model.animelist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import moe.notify.animenotifier.network.model.anime.RESTAnime;


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
    public List<RESTAnime> animes = new ArrayList<>();
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
