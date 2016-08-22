package moe.notify.animenotifier.presentation.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.freezingwind.animereleasenotifier.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import moe.notify.animenotifier.domain.model.anime.Anime;

public class AnimeActivity extends AppCompatActivity {


    // Automatically finds each field by the specified ID.
    @BindView(R.id.anime_summary_txt)
    TextView mDescription;
    @BindView(R.id.toolbar)
    Toolbar mAnimeToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime);
        ButterKnife.bind(this);
        setSupportActionBar(mAnimeToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Anime anime = getIntent().getParcelableExtra("clickedAnime");
        if (anime.description != null) {
            anime.description = anime.description.replace("<br>", "");
        } else {
            anime.description = "No description available ¯\\_(ツ)_/¯ ";
        }

        mDescription.setText(anime.description);
        getSupportActionBar().setTitle(anime.preferredTitle);
        getSupportActionBar().setSubtitle(anime.title.japanese);
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

}
