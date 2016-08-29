package moe.notify.animenotifier.domain.model.anime;


import moe.notify.animenotifier.domain.model.DomainModel;

public class AnimeRelation implements DomainModel {

    public String type;
    public long id;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AnimeRelation)) {
            return false;
        }

        AnimeRelation that = (AnimeRelation) o;

        return (id == that.id) && ((type != null) ? type.equals(that.type) : (that.type == null));

    }

    @Override
    public int hashCode() {
        int result = (type != null) ? type.hashCode() : 0;
        result = (31 * result) + (int) id;
        return result;
    }

    @Override
    public String toString() {
        return "AnimeRelation{" +
                "type='" + type + '\'' +
                ", id=" + id +
                '}';
    }
}
