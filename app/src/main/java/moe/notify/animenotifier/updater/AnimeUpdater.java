package moe.notify.animenotifier.updater;

class AnimeUpdater {
//    private static final String TAG = "AnimeUpdater";
//
//    private final OkHttpClient mClient;
//    protected ArrayList<Anime> mAnimeList;
//
//    public AnimeUpdater() {
//        mAnimeList = new ArrayList<>();
//        mClient = new OkHttpClient();
//    }
//
//    // GetAnimeList
//    public ArrayList<Anime> getAnimeList() {
//        return mAnimeList;
//    }
//
////    public void update(String response, final Context context, final AnimeListUpdateCallBack callBack) {
////        try {
////            JSONObject responseObject = new JSONObject(response);
////            update(responseObject, context, callBack);
////        } catch (JSONException e) {
////            Log.d("AnimeUpdater", "update: " + e.toString());
////        }
////    }
//
//    public void update(String response, final Context context, final AnimeListUpdateCallBack callBack) {
////        try {
////            mAnimeList.clear();
////
////            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
////            SharedPreferences.Editor editor = sharedPrefs.edit();
////
////            // Create new gson object
////            final Gson gson = new Gson();
////            AnimeList animeList = gson.fromJson(response, AnimeList.class);
////
////            for (Anime anime :
////                    animeList.animelist) {
////                // Load cached episode count
////                String key = anime.mTitle + ":episodes-available";
////                int availableCached = sharedPrefs.getInt(key, -1);
////
////                anime.notify = (anime.mEpisodes.available - anime.mEpisodes.watched) == 1/*availableCached) && (availableCached != -1)*/;
////
////                // Save data in preferences
////                editor.putInt(anime.mTitle + ":episodes-available", anime.mEpisodes.available);
////
////                // Add to list
////                mAnimeList.add(anime);
////            }
////            Anime.sortType = Anime.BY_TITLE;
////            Collections.sort(mAnimeList);
////
////            // Write preferences
////            editor.apply();
////        } catch (Exception e) {
////            Log.w("AnimeUpdater", "update: ", e);
////            // Log.d("AnimeUpdater", "Error parsing JSON: " + e.toString());
////        } finally {
////            callBack.execute();
////        }
//    }
//
//    // Update
//    public void updateByUser(String userName, final Context context, final AnimeListUpdateCallBack callBack) {
//        String apiUrl = "https://notify.moe/api/animelist/" + userName;
//        Log.d(TAG, "updateByUser: apiURl = " + apiUrl);
//        Request request = new Request.Builder()
//                .url(apiUrl)
//                .build();
//
//        //if(completedOnly)
//        //apiUrl += "&completed=1";
//
//        final SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
////        apiUrl += "&animeProvider=" + sharedPrefs.getString("animeProvider", "KissAnime");
//
//        Toast.makeText(context, "Loading anime list of " + userName, Toast.LENGTH_SHORT).show();
//
//
//// Get a handler that can be used to post to the main thread
//        mClient.newCall(request).enqueue(new Callback() {
//            // Parse response using gson deserializer
//            @Override
//            public void onResponse(Call call, final Response response) throws IOException {
//                // Process the data on the worker thread
//                String jsonResponse = response.body().string();
//
//                // Access deserialized user object here
//                update(jsonResponse, context, callBack);
//
//                // Cache it
//                SharedPreferences.Editor editor = sharedPrefs.edit();
//                editor.putString("cachedAnimeListJSON", jsonResponse);
//                editor.apply();
//            }
//
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.d(TAG, "onFailure: ");
//            }
//        });
//    }
}
