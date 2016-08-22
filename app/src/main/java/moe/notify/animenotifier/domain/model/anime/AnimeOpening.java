package moe.notify.animenotifier.domain.model.anime;


public class AnimeOpening {

    public float similarity;
    public String permalink;
    public String uri;
    public String title;
    public int likes;
    public int plays;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnimeOpening)) return false;

        AnimeOpening that = (AnimeOpening) o;

        if (!uri.equals(that.uri)) return false;
        return title.equals(that.title);

    }

    @Override
    public int hashCode() {
        int result = uri.hashCode();
        result = (31 * result) + title.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "AnimeOpening{" +
                "title='" + title + '\'' +
                ", uri='" + uri + '\'' +
                ", permalink='" + permalink + '\'' +
                ", likes=" + likes +
                ", plays=" + plays +
                ", similarity=" + similarity +
                '}';
    }
}
