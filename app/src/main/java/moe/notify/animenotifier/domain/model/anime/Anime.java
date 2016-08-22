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
    public String preferredTitle;
    public Episodes episodes;
    public String hashtag;
    public long anilistEdited;
    public AnimeProvider animeProvider;
    public AiringDate airingDate;
    public List<AnimeLink> links;
    public List<AnimeStudio> studios;
    public List<AnimeRelation> relations;
    public AnimeTracks tracks;


}
