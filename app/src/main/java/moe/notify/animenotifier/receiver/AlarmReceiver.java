package moe.notify.animenotifier.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.freezingwind.animereleasenotifier.R;

import moe.notify.animenotifier.domain.executor.impl.ThreadExecutor;
import moe.notify.animenotifier.domain.interactors.GetAllAnimesInteractor;
import moe.notify.animenotifier.domain.interactors.impl.GetAllAnimesInteractorImpl;
import moe.notify.animenotifier.domain.model.animelist.AnimeList;
import moe.notify.animenotifier.domain.model.animelist.WatchingAnime;
import moe.notify.animenotifier.storage.AnimeListRepositoryImpl;
import moe.notify.animenotifier.threading.MainThreadImpl;

// AlarmReceiver
public class AlarmReceiver extends BroadcastReceiver implements GetAllAnimesInteractor.Callback {
    private NotificationCompat.Builder notificationBuilder;
    private GetAllAnimesInteractorImpl getAllAnimesInteractor;
    private Notification notification;
    private int largeIconWidth;
    private int largeIconHeight;
    private Context context;


    public AlarmReceiver() {
        getAllAnimesInteractor =
                new GetAllAnimesInteractorImpl(
                        ThreadExecutor.getInstance(),
                        MainThreadImpl.getInstance(),
                        new AnimeListRepositoryImpl(),
                        this,
                        "Scott"
                );
        context = null;
    }

    void buildNotification(WatchingAnime anime, @Nullable Bitmap largeIcon) {

        notificationBuilder =
                new NotificationCompat.Builder(context)
                        .setColor(ContextCompat.getColor(context, R.color.gray_100))
                        .setSmallIcon(R.drawable.ic_arn_waifu)
                        .setContentTitle(anime.preferredTitle)
                        .setContentText("Episode " + anime.episodes.available + " is now available!");
        if (largeIcon != null) {
            notificationBuilder.setLargeIcon(largeIcon);
        }

//        notificationBuilder = new Notification.Builder(context)
//                .setSmallIcon(R.drawable.ic_arn_waifu)
//                .setAutoCancel(true)
//                .setContentIntent(PendingIntent.getActivity(context, 0, new Intent(context, AnimeListActivity.class), 0))
//                .setDefaults(Notification.DEFAULT_SOUND)
//                .setLargeIcon(largeIcon)
//                .setContentTitle(anime.title.romaji)
//                .setColor()
//                .setCategory(CATEGORY_ALARM)
//                .setContentText("Episode " + anime.episodes.available + " is now available!");


        notification = notificationBuilder.build();
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        final SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        this.context = context;
//        final String userName = sharedPrefs.getString("userName", "");
        largeIconWidth = context.getResources().getDimensionPixelSize(
                (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) ? android.R.dimen.notification_large_icon_width : R.dimen.notification_large_icon_default)
        ;
        largeIconHeight = context.getResources().getDimensionPixelSize(
                (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) ? android.R.dimen.notification_large_icon_height : R.dimen.notification_large_icon_default)
        ;

        Toast.makeText(context, "Checking anime notifications for: " + "Scott", Toast.LENGTH_SHORT).show();
        getAllAnimesInteractor.execute();
    }

    @Override
    public void onAnimeListRetrieved(AnimeList animeList) {
        for (int i = 0; i < animeList.animes.size(); i++) {
            final WatchingAnime anime = animeList.animes.get(i);

//            // Notify
            if ((anime.episodes.available != anime.episodes.next) || (anime.episodes.watched == anime.episodes.available)) {
                continue;
            }

            Bitmap largeIcon;

            Glide
                    .with(context.getApplicationContext())
                    .load(anime.image)
                    .asBitmap()
                    .into(new SimpleTarget<Bitmap>(100, 100) {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
                            buildNotification(anime, resource);
                            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                            int mNotificationId = anime.preferredTitle.hashCode();
                            mNotificationManager.notify(mNotificationId, notification);
                        }
                    });

//            buildNotification(anime, largeIcon);
//            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//            int mNotificationId = anime.preferredTitle.hashCode();
//            mNotificationManager.notify(mNotificationId, notification);
        }

    }
}
