package moe.notify.animenotifier.domain.model.animelist;


public class AnimeProvider {

    public NextEpisode nextEpisode;
    public long available;
    public String url;
    public String type;
    public String rssUrl;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AnimeProvider)) {
            return false;
        }

        AnimeProvider that = (AnimeProvider) o;

        if ((nextEpisode != null) ? !nextEpisode.equals(that.nextEpisode) : (that.nextEpisode != null)) {
            return false;
        }
        if (!url.equals(that.url)) {
            return false;
        }
        return type.equals(that.type);

    }

    @Override
    public int hashCode() {
        int result = nextEpisode != null ? nextEpisode.hashCode() : 0;
        result = 31 * result + url.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "AnimeProvider{" +
                "nextEpisode=" + nextEpisode +
                ", available=" + available +
                ", url='" + url + '\'' +
                ", type='" + type + '\'' +
                ", rssUrl='" + rssUrl + '\'' +
                '}';
    }
}
