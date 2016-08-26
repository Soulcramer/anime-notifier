package moe.notify.animenotifier.domain.model.anime;

import java.util.Date;
import java.util.List;


public class Anime {
    public long id;
    public String type;
    public AnimeTitle title;
    public String image;
    public String airingStatus;
    public int adult;
    public int watching;
    public String description;
    public Date startDate;
    public Date endDate;
    public String youtubeId;
    public List<String> genres;
    public String source;
    public String classification;
    public int totalEpisodes;
    public int duration;
    public String hashtag;
    public long anilistEdited;
    public List<AnimeLink> links;
    public List<AnimeStudio> studios;
    public List<AnimeRelation> relations;
    public AnimeTracks tracks;


    @Override
    public String toString() {
        return "Anime{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", title=" + title.toString() +
                ", image='" + image + '\'' +
                ", airingStatus='" + airingStatus + '\'' +
                ", adult=" + adult +
                ", watching=" + watching +
                ", description='" + description + '\'' +
                ", startDate=" + startDate.toString() +
                ", endDate=" + endDate.toString() +
                ", youtubeId='" + youtubeId + '\'' +
                ", genres=" + genres.size() +
                ", source='" + source + '\'' +
                ", classification='" + classification + '\'' +
                ", totalEpisodes=" + totalEpisodes +
                ", duration=" + duration +
                ", hashtag='" + hashtag + '\'' +
                ", anilistEdited=" + anilistEdited +
                ", links=" + links.size() +
                ", studios=" + studios.size() +
                ", relations=" + relations.size() +
                ", tracks=" + tracks.toString() +
                '}';
    }
}
