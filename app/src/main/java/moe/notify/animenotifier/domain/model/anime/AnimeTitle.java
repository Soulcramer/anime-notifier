package moe.notify.animenotifier.domain.model.anime;

import java.util.List;

import moe.notify.animenotifier.domain.model.DomainModel;


public class AnimeTitle implements DomainModel {


    public String romaji;
    public String japanese;
    public List<String> synonyms;
    public String english;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }

        AnimeTitle that = (AnimeTitle) o;

        if (!romaji.equals(that.romaji)) {
            return false;
        }
        if ((japanese != null) ? !japanese.equals(that.japanese) : (that.japanese != null)) {
            return false;
        }
        return (english != null) ? english.equals(that.english) : (that.english == null);

    }

    @Override
    public int hashCode() {
        int result = romaji.hashCode();
        result = (31 * result) + ((japanese != null) ? japanese.hashCode() : 0);
        result = (31 * result) + ((english != null) ? english.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AnimeTitle{" +
                "english='" + english + '\'' +
                ", japanese='" + japanese + '\'' +
                ", romaji='" + romaji + '\'' +
                '}';
    }
}
