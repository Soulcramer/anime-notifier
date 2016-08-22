package moe.notify.animenotifier.network.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class RESTListProviders {

    @SerializedName("MyAnimeList")
    @Expose
    public RESTMyAnimeList mMyAnimeList;
    @SerializedName("HummingBird")
    @Expose
    public RESTHummingBird mHummingBird;
    @SerializedName("AniList")
    @Expose
    public RESTAniList mAniList;
    @SerializedName("AnimePlanet")
    @Expose
    public RESTAnimePlanet mAnimePlanet;
}
