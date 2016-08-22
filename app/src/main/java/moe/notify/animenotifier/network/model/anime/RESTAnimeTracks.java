package moe.notify.animenotifier.network.model.anime;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class RESTAnimeTracks {

    @SerializedName("opening")
    @Expose
    public RESTAnimeOpening opening;

}
