package moe.notify.animenotifier.network.model.anime;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class RESTAnimeTitle {
    @SerializedName("romaji")
    @Expose
    public String romaji;
    @SerializedName("japanese")
    @Expose
    public String japanese;
    @SerializedName("synonyms")
    @Expose
    public List<String> synonyms = new ArrayList<>();
    @SerializedName("english")
    @Expose
    public String english;

}