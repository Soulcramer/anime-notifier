package moe.notify.animenotifier.network.model.anime;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import moe.notify.animenotifier.domain.model.DomainModel;
import moe.notify.animenotifier.domain.model.anime.AnimeTitle;
import moe.notify.animenotifier.network.model.NetworkModel;


public class RESTAnimeTitle implements NetworkModel {
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

    @Override
    public void convertToDomainModel(DomainModel domainModel) {
        AnimeTitle title = (AnimeTitle) domainModel;
        title.romaji = romaji;
        title.japanese = japanese;
        title.english = english;
        if (title.synonyms == null) {
            title.synonyms = new ArrayList<>();
        } else {
            title.synonyms.clear();
        }
        title.synonyms.addAll(synonyms);
    }

    @Override
    public void convertToNetworkModel(DomainModel domainModel) {
        AnimeTitle title = (AnimeTitle) domainModel;
        romaji = title.romaji;
        japanese = title.japanese;
        english = title.english;
        if (title.synonyms == null) {
            title.synonyms = new ArrayList<>();
        }
        synonyms.clear();
        synonyms.addAll(title.synonyms);
    }
}