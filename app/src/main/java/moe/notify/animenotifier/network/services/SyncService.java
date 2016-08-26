package moe.notify.animenotifier.network.services;

import moe.notify.animenotifier.network.model.anime.RESTAnime;
import moe.notify.animenotifier.network.model.animelist.RESTAnimeList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * A REST interface describing how data will be synced with the backend.
 * <p/>
 */
public interface SyncService {

    /**
     * Get the animelist of an notify.moe user.
     *
     * @param username Username of the current or wanted user.
     * @return the wanted animelist.
     */
    @GET("animelist/{username}")
    Call<RESTAnimeList> getAnimeListFromUser(@Path("username") String username);

    /**
     * Get the anime with the required id.
     *
     * @param id Id of the current or wanted anime.
     * @return The RESTAnime of the corresponding anime.
     */
    @GET("anime/{id}")
    Call<RESTAnime> getAnimeById(@Path("id") long id);

//    @GET("/users/{username}")


}