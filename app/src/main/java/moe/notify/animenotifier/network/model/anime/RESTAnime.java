package moe.notify.animenotifier.network.model.anime;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class RESTAnime {


    @SerializedName("id")
    @Expose
    public long id;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("title")
    @Expose
    public RESTAnimeTitle title;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("airingStatus")
    @Expose
    public String airingStatus;
    @SerializedName("adult")
    @Expose
    public int adult;
    @SerializedName("watching")
    @Expose
    public int watching;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("startDate")
    @Expose
    public String startDate;
    @SerializedName("endDate")
    @Expose
    public String endDate;
    @SerializedName("youtubeId")
    @Expose
    public String youtubeId;
    @SerializedName("genres")
    @Expose
    public List<String> genres = new ArrayList<>();
    @SerializedName("source")
    @Expose
    public String source;
    @SerializedName("classification")
    @Expose
    public String classification;
    @SerializedName("totalEpisodes")
    @Expose
    public int totalEpisodes;
    @SerializedName("duration")
    @Expose
    public int duration;
    @SerializedName("preferredTitle")
    @Expose
    public String preferredTitle;
    @SerializedName("hashtag")
    @Expose
    public String hashtag;
    @SerializedName("anilistEdited")
    @Expose
    public long anilistEdited;
    @SerializedName("links")
    @Expose
    public List<RESTAnimeLink> links = new ArrayList<>();
    @SerializedName("studios")
    @Expose
    public List<RESTAnimeStudio> studios = new ArrayList<>();
    @SerializedName("relations")
    @Expose
    public List<RESTAnimeRelation> relations = new ArrayList<>();
    @SerializedName("pageGenerated")
    @Expose
    public String pageGenerated;
    @SerializedName("tracks")
    @Expose
    public RESTAnimeTracks tracks;


}
