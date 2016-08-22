package moe.notify.animenotifier.storage.database;

import com.raizlabs.android.dbflow.annotation.Database;


@Database(name = AnimeDatabase.NAME, version = AnimeDatabase.VERSION, foreignKeysSupported = true)
public final class AnimeDatabase {
    public static final String NAME = "Anime_db";

    public static final int VERSION = 1;
}
