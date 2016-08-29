package moe.notify.animenotifier.domain.model.anime;


import moe.notify.animenotifier.domain.model.DomainModel;

public class AnimeTracks implements DomainModel {

    public AnimeOpening opening;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AnimeTracks)) {
            return false;
        }

        AnimeTracks that = (AnimeTracks) o;

        return opening.equals(that.opening);

    }

    @Override
    public int hashCode() {
        return opening.hashCode();
    }


    @Override
    public String toString() {
        return "AnimeTracks{" +
                "opening=" + opening +
                '}';
    }
}
