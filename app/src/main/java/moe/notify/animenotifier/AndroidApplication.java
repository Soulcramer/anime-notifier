package moe.notify.animenotifier;

import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.preference.PreferenceManager;

import com.facebook.stetho.Stetho;
import com.freezingwind.animereleasenotifier.BuildConfig;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import moe.notify.animenotifier.helpers.AlarmHelper;
import moe.notify.animenotifier.receiver.AlarmReceiver;
import timber.log.Timber;
import timber.log.Timber.DebugTree;


public class AndroidApplication extends Application {
    // Schedule alarm
    public static void scheduleAlarm(Context context) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        int updateInterval = Integer.parseInt(sharedPrefs.getString("updateInterval", "60"));

        AlarmHelper alarmHelper = new AlarmHelper(context, 1000L * 60L * updateInterval, 1000L) {
            @Override
            protected PendingIntent pendingIntent(Context context, int flags) {
                Intent intent = new Intent(context, AlarmReceiver.class);
                return PendingIntent.getBroadcast(context, 0, intent, flags);
            }
        };

        alarmHelper.scheduleUnconditionally();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // initiate Timber
        if (BuildConfig.DEBUG) {
            Timber.plant(new DebugTree());
        } else {
            // TODO Crashlytics.start(this);
            // TODO Timber.plant(new CrashlyticsTree());
        }

        FlowManager.init(new FlowConfig.Builder(this)
                .openDatabasesOnInit(true).build());

        // enable stetho
        Stetho.initializeWithDefaults(this);
    }


}
