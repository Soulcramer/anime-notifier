package moe.notify.animenotifier.network.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class RESTUser {


    @SerializedName("nick")
    @Expose
    public String nick;
    @SerializedName("language")
    @Expose
    public String language;
    @SerializedName("tagline")
    @Expose
    public String tagline;
    @SerializedName("website")
    @Expose
    public String website;
    @SerializedName("providers")
    @Expose
    public RESTProviders providers;
    @SerializedName("listProviders")
    @Expose
    public RESTListProviders listProviders;
    @SerializedName("registered")
    @Expose
    public String registered;
    @SerializedName("sortBy")
    @Expose
    public String sortBy;
    @SerializedName("role")
    @Expose
    public String role;
    @SerializedName("twitter")
    @Expose
    public String twitter;
    @SerializedName("osu")
    @Expose
    public String osu;
    @SerializedName("titleLanguage")
    @Expose
    public String titleLanguage;
    @SerializedName("osuDetails")
    @Expose
    public RESTOsuDetails osuDetails;
    @SerializedName("avatar")
    @Expose
    public String avatar;
    @SerializedName("following")
    @Expose
    public long following;
    @SerializedName("notificationsEnabled")
    @Expose
    public boolean notificationsEnabled;
}
