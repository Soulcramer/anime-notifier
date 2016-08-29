package moe.notify.animenotifier.network.model.anime;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import moe.notify.animenotifier.domain.model.DomainModel;
import moe.notify.animenotifier.domain.model.anime.Anime;
import moe.notify.animenotifier.domain.model.anime.AnimeLink;
import moe.notify.animenotifier.domain.model.anime.AnimeRelation;
import moe.notify.animenotifier.domain.model.anime.AnimeStudio;
import moe.notify.animenotifier.domain.model.anime.AnimeTitle;
import moe.notify.animenotifier.domain.model.anime.AnimeTracks;
import moe.notify.animenotifier.network.model.NetworkModel;
import moe.notify.animenotifier.utils.DateUtils;


public class RESTAnime implements NetworkModel {


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


    @Override
    public void convertToDomainModel(DomainModel domainModel) {
        Anime anime = (Anime) domainModel;
        anime.id = id;
        if (anime.title == null) {
            anime.title = new AnimeTitle();
        }
        if (title == null) {
            title = new RESTAnimeTitle();
        }
        title.convertToDomainModel(anime.title);
        anime.description = description;
        anime.adult = adult;
        anime.airingStatus = airingStatus;
        anime.anilistEdited = anilistEdited;
        anime.classification = classification;
        anime.duration = duration;
        anime.endDate = DateUtils.iso8601ToDate(endDate);
        anime.startDate = DateUtils.iso8601ToDate(startDate);
        anime.genres = genres;
        anime.hashtag = hashtag;
        anime.image = image;
        if (anime.links == null) {
            anime.links = new ArrayList<>();
        }
        if (links == null) {
            links = new ArrayList<>();
        }
        int linksLength = links.size();
        for (int i = 0; i < linksLength; i++) {
            if (anime.links.get(i) == null) {
                AnimeLink link = new AnimeLink();
                links.get(i).convertToDomainModel(link);
                anime.links.add(link);
            } else {
                links.get(i).convertToDomainModel(anime.links.get(i));
            }
        }

        if (anime.relations == null) {
            anime.relations = new ArrayList<>();
        }
        if (relations == null) {
            relations = new ArrayList<>();
        }
        int relationsLength = relations.size();
        for (int i = 0; i < relationsLength; i++) {
            if (anime.relations.get(i) == null) {
                AnimeRelation relation = new AnimeRelation();
                relations.get(i).convertToDomainModel(relation);
                anime.relations.add(relation);
            } else {
                relations.get(i).convertToDomainModel(anime.relations.get(i));
            }
        }

        if (anime.studios == null) {
            anime.studios = new ArrayList<>();
        }
        if (studios == null) {
            studios = new ArrayList<>();
        }
        int studiosLength = studios.size();
        for (int i = 0; i < studiosLength; i++) {
            if (anime.studios.get(i) == null) {
                AnimeStudio studio = new AnimeStudio();
                studios.get(i).convertToDomainModel(studio);
                anime.studios.add(studio);
            } else {
                studios.get(i).convertToDomainModel(anime.studios.get(i));
            }
        }
        anime.source = source;
        anime.totalEpisodes = totalEpisodes;
        if (anime.tracks == null) {
            anime.tracks = new AnimeTracks();
        }
        if (tracks == null) {
            tracks = new RESTAnimeTracks();
        }
        tracks.convertToDomainModel(anime.tracks);

        anime.type = type;
        anime.watching = watching;
        anime.youtubeId = youtubeId;

    }

    @Override
    public void convertToNetworkModel(DomainModel domainModel) {
        Anime anime = (Anime) domainModel;
        id = anime.id;
        if (title == null) {
            title = new RESTAnimeTitle();
        }
        if (anime.title == null) {
            anime.title = new AnimeTitle();
        }
        title.convertToNetworkModel(anime.title);
        description = anime.description;
        adult = anime.adult;
        airingStatus = anime.airingStatus;
        anilistEdited = anime.anilistEdited;
        classification = anime.classification;
        duration = anime.duration;
        endDate = DateUtils.dateToIso8601(anime.endDate);
        startDate = DateUtils.dateToIso8601(anime.startDate);
        genres = anime.genres;
        hashtag = anime.hashtag;
        image = anime.image;
        if (links == null) {
            links = new ArrayList<>();
        }
        if (anime.links == null) {
            anime.links = new ArrayList<>();
        }
        int linksLength = anime.links.size();
        for (int i = 0; i < linksLength; i++) {
            if (links.get(i) == null) {
                RESTAnimeLink link = new RESTAnimeLink();
                link.convertToNetworkModel(anime.links.get(i));
                links.add(link);
            } else {
                links.get(i).convertToNetworkModel(anime.links.get(i));
            }
        }

        if (relations == null) {
            relations = new ArrayList<>();
        }
        if (anime.relations == null) {
            anime.relations = new ArrayList<>();
        }
        int relationsLength = anime.relations.size();
        for (int i = 0; i < relationsLength; i++) {
            if (relations.get(i) == null) {
                RESTAnimeRelation relation = new RESTAnimeRelation();
                relation.convertToNetworkModel(anime.relations.get(i));
                relations.add(relation);
            } else {
                relations.get(i).convertToNetworkModel(anime.relations.get(i));
            }
        }

        if (anime.studios == null) {
            anime.studios = new ArrayList<>();
        }
        if (studios == null) {
            studios = new ArrayList<>();
        }
        int studiosLength = anime.studios.size();
        for (int i = 0; i < studiosLength; i++) {
            if (studios.get(i) == null) {
                RESTAnimeStudio studio = new RESTAnimeStudio();
                studio.convertToNetworkModel(anime.studios.get(i));
                studios.add(studio);
            } else {
                studios.get(i).convertToNetworkModel(anime.studios.get(i));
            }
        }
        source = anime.source;
        totalEpisodes = anime.totalEpisodes;
        if (tracks == null) {
            tracks = new RESTAnimeTracks();
        }
        if (anime.tracks == null) {
            anime.tracks = new AnimeTracks();
        }
        tracks.convertToNetworkModel(anime.tracks);

        type = anime.type;
        watching = anime.watching;
        youtubeId = anime.youtubeId;
    }
}
