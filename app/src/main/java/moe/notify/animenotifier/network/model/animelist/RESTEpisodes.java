package moe.notify.animenotifier.network.model.animelist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class RESTEpisodes {
    @SerializedName("max")
    @Expose
    public long max;
    @SerializedName("available")
    @Expose
    public long available;
    @SerializedName("watched")
    @Expose
    public long watched;
    @SerializedName("next")
    @Expose
    public long next;
    @SerializedName("offset")
    @Expose
    public long offset;
}
