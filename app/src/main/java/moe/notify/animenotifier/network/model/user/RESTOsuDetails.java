package moe.notify.animenotifier.network.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class RESTOsuDetails {

    @SerializedName("pp")
    @Expose
    public double mPp;
    @SerializedName("nick")
    @Expose
    public String mNick;
    @SerializedName("level")
    @Expose
    public double mLevel;
    @SerializedName("accuracy")
    @Expose
    public double mAccuracy;
    @SerializedName("playCount")
    @Expose
    public long mPlayCount;
}
