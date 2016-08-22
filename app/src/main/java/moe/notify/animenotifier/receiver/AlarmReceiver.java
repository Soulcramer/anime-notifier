package moe.notify.animenotifier.receiver;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import com.freezingwind.animereleasenotifier.R;

import moe.notify.animenotifier.domain.model.anime.Anime;
import moe.notify.animenotifier.updater.AnimeUpdater;

// AlarmReceiver
public class AlarmReceiver extends BroadcastReceiver {
    protected AnimeUpdater updater;
    private NotificationCompat.Builder notificationBuilder;
    private Notification notification;
    private int largeIconWidth;
    private int largeIconHeight;


    public AlarmReceiver() {
        updater = new AnimeUpdater();
    }

    void buildNotification(Context context, Anime anime, Bitmap largeIcon) {

        notificationBuilder =
                new NotificationCompat.Builder(context)
                        .setColor(ContextCompat.getColor(context, R.color.gray_100))
                        .setSmallIcon(R.drawable.ic_arn_waifu)
                        .setLargeIcon(largeIcon)
                        .setContentTitle(anime.preferredTitle)
                        .setContentText("Episode " + anime.episodes.available + " is now available!");

//        notificationBuilder = new Notification.Builder(context)
//                .setSmallIcon(R.drawable.ic_arn_waifu)
//                .setAutoCancel(true)
//                .setContentIntent(PendingIntent.getActivity(context, 0, new Intent(context, AnimeListActivity.class), 0))
//                .setDefaults(Notification.DEFAULT_SOUND)
//                .setLargeIcon(largeIcon)
//                .setContentTitle(anime.title.romaji)
//                .setColor()
////                .setCategory(CATEGORY_ALARM)
//                .setContentText("Episode " + anime.episodes.available + " is now available!");


        notification = notificationBuilder.build();
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        final SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        final String userName = sharedPrefs.getString("userName", "");
        largeIconWidth = context.getResources().getDimensionPixelSize(
                (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) ? android.R.dimen.notification_large_icon_width : R.dimen.notification_large_icon_default)
        ;
        largeIconHeight = context.getResources().getDimensionPixelSize(
                (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) ? android.R.dimen.notification_large_icon_height : R.dimen.notification_large_icon_default)
        ;

//        Toast.makeText(context, "Checking anime notifications for: " + userName, Toast.LENGTH_SHORT).show();

        /*updater.updateByUser(userName, context, new AnimeListUpdateCallBack() {
            @Override
            public void execute() {
                ArrayList<Anime> animeList = updater.getAnimeList();

                for (int i = 0; i < animeList.size(); i++) {
                    final Anime anime = animeList.get(i);

                    // Notify
                    if (!anime.notify) {
                        continue;
                    }

                    Bitmap largeIcon = null;
                    try {
                        largeIcon = Glide.with(context)
                                .load(anime.mImageUrl)
                                .asBitmap()
                                .centerCrop()
                                .into(largeIconWidth, largeIconHeight)
                                .get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }

                    buildNotification(context, anime, largeIcon);
                    NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                    int mNotificationId = anime.mTitle.hashCode();
                    mNotificationManager.notify(mNotificationId, notification);
                }
            }
        });*/
    }
}
