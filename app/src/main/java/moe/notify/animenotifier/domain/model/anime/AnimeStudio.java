package moe.notify.animenotifier.domain.model.anime;


public class AnimeStudio {

    public String name;
    public String wiki;
    public int mainStudio;
    public long id;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AnimeStudio)) {
            return false;
        }

        AnimeStudio that = (AnimeStudio) o;

        return (id == that.id) && ((name != null) ? name.equals(that.name) : ((that.name == null) && ((wiki != null) ? wiki.equals(that.wiki) : (that.wiki == null))));

    }

    @Override
    public int hashCode() {
        int result = (name != null) ? name.hashCode() : 0;
        result = (31 * result) + ((wiki != null) ? wiki.hashCode() : 0);
        result = (31 * result) + (int) id;
        return result;
    }
}
