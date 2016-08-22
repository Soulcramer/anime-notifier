package moe.notify.animenotifier.network.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class RESTProviders {


    @SerializedName("airingDate")
    @Expose
    public String mAiringDate;
    @SerializedName("anime")
    @Expose
    public String mAnime;
    @SerializedName("list")
    @Expose
    public String mList;
}
