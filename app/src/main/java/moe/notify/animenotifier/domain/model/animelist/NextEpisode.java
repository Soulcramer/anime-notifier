package moe.notify.animenotifier.domain.model.animelist;


public class NextEpisode {

    public String url;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NextEpisode)) {
            return false;
        }

        NextEpisode that = (NextEpisode) o;

        return (url != null) ? url.equals(that.url) : (that.url == null);

    }

    @Override
    public int hashCode() {
        return (url != null) ? url.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "NextEpisode{" +
                "url='" + url + '\'' +
                '}';
    }
}
