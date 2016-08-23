package moe.notify.animenotifier.presentation.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.freezingwind.animereleasenotifier.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import moe.notify.animenotifier.domain.executor.impl.ThreadExecutor;
import moe.notify.animenotifier.domain.model.anime.Anime;
import moe.notify.animenotifier.presentation.presenters.AnimePresenter;
import moe.notify.animenotifier.presentation.presenters.impl.AnimePresenterImpl;
import moe.notify.animenotifier.presentation.ui.fragments.AnimeListFragment;
import moe.notify.animenotifier.storage.AnimeRepositoryImpl;
import moe.notify.animenotifier.threading.MainThreadImpl;
import timber.log.Timber;

public class AnimeActivity extends AppCompatActivity implements AnimePresenter.View {


    // Automatically finds each field by the specified ID.
    @BindView(R.id.anime_summary_txt)
    TextView mDescription;
    @BindView(R.id.toolbar)
    Toolbar mAnimeToolbar;
    private AnimePresenter animePresenter;
    private long animeId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime);
        ButterKnife.bind(this);
        Timber.w("ONCREATEVIEW");
        animeId = getIntent().getLongExtra(AnimeListFragment.EXTRA_ANIME_ID, 0);
        init();
        setSupportActionBar(mAnimeToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    protected void onResume() {
        super.onResume();
        animePresenter.getAnime(animeId);
        Timber.w("ONRESUME");
    }


    @Override
    protected void onStop() {
        Timber.w("ONSTOP");
        animePresenter.stop();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Timber.w("ONDESTROY");
        super.onDestroy();
    }

    private void init() {
        animePresenter = new AnimePresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new AnimeRepositoryImpl(this)
        );



    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                //handle the home button onClick event here.
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showAnime(Anime anime) {
        mDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mDescription.setText(anime.description);
        getSupportActionBar().setTitle(anime.title.romaji);
        getSupportActionBar().setSubtitle(anime.title.japanese);

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String message) {

    }
}
