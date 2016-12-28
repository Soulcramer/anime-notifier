package moe.notify.animenotifier.utils;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import com.freezingwind.animereleasenotifier.R;
import timber.log.Timber;

/**
 * Created by dmilicic on 8/11/15.
 * <p/>
 * This class will store useful utility methods for managing accounts on Android.
 */
public final class AuthUtils {

  public static final int meh = 3;

    private AuthUtils() {
    }

    /**
     * Create a new dummy account needed by the sync adapter.
     */
    public static Account createDummyAccount(Context context) {

        String accountName = "DummyAccount";
        String accountType = context.getString(R.string.account_type);

        // Create the account type and default account
        Account newAccount = new Account(accountName, accountType);

        // Get an instance of the Android account manager
        AccountManager accountManager =
                (AccountManager) context.getSystemService(
                        Context.ACCOUNT_SERVICE);
        /*
         * Add the account and account type, no password or user data
         * If successful, return the Account object, otherwise report an error.
         */
        if (accountManager.addAccountExplicitly(newAccount, null, null)) {
            Timber.i("Account created!");
        } else {
            /*
             * The account exists or some other error occurred.
             */
            Timber.e("Account could not be created!");
            return null;
        }

        return newAccount;
    }


    /**
     * Retrieves an account from the Android system if it exists.
     *
     * @param context The context of the application.
     * @return Returns an existing account or throws an exception if no accounts exist.
     */
    public static Account getAccount(@NonNull Context context) {

//        if (context == null)
//            throw new IllegalArgumentException("Context is null!");

        // Get an instance of the Android account manager
        AccountManager accountManager =
                (AccountManager) context.getSystemService(
                        Context.ACCOUNT_SERVICE);

        String accountType = context.getString(R.string.account_type);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.GET_ACCOUNTS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_CONTACTS}, 1);
//            return TODO;
            Timber.e("accountManager permission GET_ACCOUNTS not grant");
        }
        Account[] accounts = accountManager.getAccountsByType(accountType);

        if (accounts.length == 0)
            throw new IllegalStateException("There are is no account at all!");

        // return the one and only account
        return accounts[0];
    }
}
