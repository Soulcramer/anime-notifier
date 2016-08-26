package moe.notify.animenotifier.network.model.animelist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import moe.notify.animenotifier.network.model.anime.RESTAnimeTitle;

/**
 * Created by GOKU on 24/08/2016.
 */

public class RESTWatchingAnime {


    @SerializedName("animeProvider")
    @Expose
    public RESTAnimeProvider animeProvider;
    @SerializedName("airingDate")
    @Expose
    public RESTAiringDate airingDate;
    @SerializedName("preferredTitle")
    @Expose
    public String preferredTitle;
    @SerializedName("title")
    @Expose
    public RESTAnimeTitle title;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("id")
    @Expose
    public Long id;
    @SerializedName("episodes")
    @Expose
    public RESTEpisodes episodes;
}
