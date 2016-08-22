package moe.notify.animenotifier.network.model.anime;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class RESTAiringDate {

    @SerializedName("remainingString")
    @Expose
    public String remainingString;
    @SerializedName("timeStamp")
    @Expose
    public long timeStamp;
    @SerializedName("remaining")
    @Expose
    public long remaining;

}
