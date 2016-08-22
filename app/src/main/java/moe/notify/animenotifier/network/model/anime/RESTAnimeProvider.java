package moe.notify.animenotifier.network.model.anime;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class RESTAnimeProvider {
    @SerializedName("nextEpisode")
    @Expose
    public RESTNextEpisode nextEpisode;
    @SerializedName("available")
    @Expose
    public long available;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("rssUrl")
    @Expose
    public String rssUrl;
}
