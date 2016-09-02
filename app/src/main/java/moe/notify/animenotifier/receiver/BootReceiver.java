package moe.notify.animenotifier.receiver;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import moe.notify.animenotifier.AndroidApplication;

public class BootReceiver extends BroadcastReceiver {
    public static void enable(Context context) {
        setActivationState(context, PackageManager.COMPONENT_ENABLED_STATE_ENABLED);
    }

    public static void disable(Context context) {
        setActivationState(context, PackageManager.COMPONENT_ENABLED_STATE_DISABLED);
    }

    private static void setActivationState(Context context, int state) {
        ComponentName componentName = new ComponentName(context, BootReceiver.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(componentName, state, PackageManager.DONT_KILL_APP);
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            AndroidApplication.scheduleAlarm(context);
        }
    }

}
