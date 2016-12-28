package moe.notify.animenotifier.presentation.ui.activities;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.freezingwind.animereleasenotifier.R;
import moe.notify.animenotifier.AndroidApplication;
import moe.notify.animenotifier.presentation.ui.fragments.AnimeListFragment;
import moe.notify.animenotifier.presentation.ui.fragments.SettingsFragment;
import moe.notify.animenotifier.receiver.BootReceiver;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

  private static final String TAG = "MainActivity";

  @BindView(R.id.toolbar) Toolbar mToolbar;
  @BindView(R.id.drawer_layout) DrawerLayout mDrawerLayout;
  @BindView(R.id.nav_view) NavigationView mNavigationView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main2);
    ButterKnife.bind(this);
    Timber.w("ONCREATE");

    init();

    enableBootReceiver();

    AndroidApplication.Companion.scheduleAlarm(this);
  }

  @Override protected void onResume() {
    super.onResume();

    Timber.w("ONRESUME");
  }

  private void init() {
    setSupportActionBar(mToolbar);

    ActionBarDrawerToggle toggle =
        new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_description_open,
            R.string.drawer_description_close);
    mDrawerLayout.addDrawerListener(toggle);
    toggle.syncState();

    mNavigationView.setNavigationItemSelectedListener(this);
    onNavigationItemSelected(mNavigationView.getMenu().findItem(R.id.nav_animeList));
    mNavigationView.setCheckedItem(R.id.nav_animeList);
  }

  // Enable boot receiver
  private void enableBootReceiver() {
    ComponentName receiver = new ComponentName(this, BootReceiver.class);
    PackageManager packageManager = this.getPackageManager();

    packageManager.setComponentEnabledSetting(receiver,
        PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
  }

  @Override public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();
    // Create a new fragment and specify the fragment to show based on nav item clicked
    Fragment fragment = null;
    Class fragmentClass;
    switch (id) {
      case R.id.nav_animeList:
        fragmentClass = AnimeListFragment.class;
        break;
      case R.id.nav_settings:
        fragmentClass = SettingsFragment.class;
        break;
      default:
        fragmentClass = AnimeListFragment.class;
    }

    try {
      fragment = (Fragment) fragmentClass.newInstance();
    } catch (Exception e) {
      Log.e(TAG, "selectDrawerItem: Couldn't instanciate fragment", e);
    }

    // Insert the fragment by replacing any existing fragment
    FragmentManager fragmentManager = getSupportFragmentManager();
    fragmentManager.beginTransaction().replace(R.id.content_main, fragment).commit();

    mDrawerLayout.closeDrawer(GravityCompat.START);
    return true;
  }
}
