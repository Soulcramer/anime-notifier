package moe.notify.animenotifier

import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v7.preference.PreferenceManager
import com.facebook.stetho.Stetho
import com.freezingwind.animereleasenotifier.BuildConfig
import com.squareup.leakcanary.LeakCanary
import com.uphyca.stetho_realm.RealmInspectorModulesProvider
import io.realm.Realm
import moe.notify.animenotifier.helpers.AlarmHelper
import moe.notify.animenotifier.receiver.AlarmReceiver
import timber.log.Timber
import timber.log.Timber.DebugTree


class AndroidApplication : Application() {

  override fun onCreate() {
    super.onCreate()
    if (LeakCanary.isInAnalyzerProcess(this)) {
      // This process is dedicated to LeakCanary for heap analysis.
      // You should not init your app in this process.
      return
    }
    LeakCanary.install(this)

    // initiate Timber
    if (BuildConfig.DEBUG) {
      Timber.plant(DebugTree())
    } else {
      // TODO Crashlytics.start(this);
      // TODO Timber.plant(new CrashlyticsTree());
    }

    Realm.init(this)

    // enable stetho
    Stetho.initialize(
        Stetho.newInitializerBuilder(this)
            .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
            .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
            .build())

  }

  companion object {
    // Schedule alarm
    fun scheduleAlarm(context: Context) {
      val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
      val updateInterval = java.lang.Long.parseLong(sharedPrefs.getString("updateInterval", "30"))

      val alarmHelper = object : AlarmHelper(context, Constants.MINUTE * updateInterval,
          Constants.HOUR) {
        override fun pendingIntent(context: Context, flags: Int): PendingIntent {
          val intent = Intent(context, AlarmReceiver::class.java)
          return PendingIntent.getBroadcast(context, 0, intent, flags)
        }
      }

      alarmHelper.scheduleUnconditionally()
    }

    operator fun get(context: Context): AndroidApplication {
      return context.applicationContext as AndroidApplication
    }
  }

}
