package moe.notify.animenotifier.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import moe.notify.animenotifier.AndroidApplication;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            AndroidApplication.scheduleAlarm(context);
        }
    }
}
