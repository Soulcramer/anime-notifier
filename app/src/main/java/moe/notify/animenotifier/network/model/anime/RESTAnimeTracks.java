package moe.notify.animenotifier.network.model.anime;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import moe.notify.animenotifier.domain.model.DomainModel;
import moe.notify.animenotifier.domain.model.anime.AnimeOpening;
import moe.notify.animenotifier.domain.model.anime.AnimeTracks;
import moe.notify.animenotifier.network.model.NetworkModel;


public class RESTAnimeTracks implements NetworkModel {

    @SerializedName("opening")
    @Expose
    public RESTAnimeOpening opening;

    @Override
    public void convertToDomainModel(DomainModel domainModel) {
        AnimeTracks tracks = (AnimeTracks) domainModel;
        if (tracks.opening == null) {
            tracks.opening = new AnimeOpening();
        }
        if (opening == null) {
            opening = new RESTAnimeOpening();
        }
        opening.convertToDomainModel(tracks.opening);
    }

    @Override
    public void convertToNetworkModel(DomainModel domainModel) {
        AnimeTracks tracks = (AnimeTracks) domainModel;
        if (tracks.opening == null) {
            tracks.opening = new AnimeOpening();
        }
        if (opening == null) {
            opening = new RESTAnimeOpening();
        }
        opening.convertToNetworkModel(tracks.opening);
    }
}
