package moe.notify.animenotifier.network.model.anime;


import moe.notify.animenotifier.domain.model.DomainModel;
import moe.notify.animenotifier.domain.model.anime.AnimeOpening;
import moe.notify.animenotifier.network.model.NetworkModel;

public class RESTAnimeOpening implements NetworkModel {


    public double similarity;
    public String permalink;
    public String uri;
    public String title;
    public int likes;
    public int plays;


    @Override
    public void convertToDomainModel(DomainModel domainModel) {
        AnimeOpening opening = (AnimeOpening) domainModel;
        opening.similarity = similarity;
        opening.permalink = permalink;
        opening.uri = uri;
        opening.title = title;
        opening.likes = likes;
        opening.plays = plays;
    }

    @Override
    public void convertToNetworkModel(DomainModel domainModel) {
        AnimeOpening opening = (AnimeOpening) domainModel;
        similarity = opening.similarity;
        permalink = opening.permalink;
        uri = opening.uri;
        title = opening.title;
        likes = opening.likes;
        plays = opening.plays;
    }
}
