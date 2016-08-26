package moe.notify.animenotifier.domain.model.anime;


import moe.notify.animenotifier.domain.model.DomainModel;

public class AnimeLink implements DomainModel {

    public String url;
    public String title;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AnimeLink)) {
            return false;
        }

        AnimeLink animeLink = (AnimeLink) o;

        return (url != null) ? url.equals(animeLink.url) : ((animeLink.url == null) && ((title != null) ? title.equals(animeLink.title) : (animeLink.title == null)));

    }

    @Override
    public int hashCode() {
        int result = (url != null) ? url.hashCode() : 0;
        result = (31 * result) + ((title != null) ? title.hashCode() : 0);
        return result;
    }
}
