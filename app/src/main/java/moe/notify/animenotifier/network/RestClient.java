package moe.notify.animenotifier.network;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import io.realm.RealmList;
import moe.notify.animenotifier.utils.RealmString;
import moe.notify.animenotifier.utils.RealmStringListTypeAdapter;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <p/>
 * This is the main entry point for network communication. Use this class for instancing REST
 * services which do the
 * actual communication.
 */
public final class RestClient {

  /**
   * This is our main backend/server URL.
   */
  private static final String REST_API_URL = "https://notify.moe/api/";
  //    public static final String REST_API_URL = "http://192.168.0.12:3000";

  private static final Retrofit RETROFIT;

  static {

    // enable logging
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

    OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
        .addNetworkInterceptor(new StethoInterceptor())
        .build();
    Gson gson = new GsonBuilder().registerTypeAdapter(new TypeToken<RealmList<RealmString>>() {
    }.getType(), RealmStringListTypeAdapter.INSTANCE).create();

    RETROFIT = new Retrofit.Builder().baseUrl(REST_API_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build();
  }

  private RestClient() {
  }

  public static <T> T getService(Class<T> serviceClass) {
    return RETROFIT.create(serviceClass);
  }
}
