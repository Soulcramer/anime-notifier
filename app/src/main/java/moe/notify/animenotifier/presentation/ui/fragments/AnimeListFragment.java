package moe.notify.animenotifier.presentation.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.freezingwind.animereleasenotifier.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import moe.notify.animenotifier.domain.executor.impl.ThreadExecutor;
import moe.notify.animenotifier.domain.model.animelist.AnimeList;
import moe.notify.animenotifier.helpers.DividerItemDecoration;
import moe.notify.animenotifier.presentation.presenters.AnimeListPresenter;
import moe.notify.animenotifier.presentation.presenters.impl.AnimeListPresenterImpl;
import moe.notify.animenotifier.presentation.ui.activities.AnimeActivity;
import moe.notify.animenotifier.presentation.ui.adapters.AnimeAdapter;
import moe.notify.animenotifier.storage.AnimeRepositoryImpl;
import moe.notify.animenotifier.threading.MainThreadImpl;
import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 */
public class AnimeListFragment extends Fragment implements AnimeListPresenter.View {
    public static final String EXTRA_ANIME_ID = "extra_anime_id_key";
    protected Context mContext;
    protected AnimeAdapter adapter;
    protected boolean useGridView;
    @BindView(R.id.animeList)
    RecyclerView mAnimeListRecyclerView;
    @BindView(R.id.loadingTextView)
    TextView mLoadingTextView;
    @BindView(R.id.loadingSpinner)
    ProgressBar mLoadingSpinner;

    private SharedPreferences sharedPrefs;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.ItemDecoration itemDecoration;
    private Unbinder unbinder;
    private AnimeListPresenter mAnimeListPresenter;

    public AnimeListFragment() {
        // ...
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getContext();

        View view = inflater.inflate(R.layout.fragment_animelist, container, false);
        unbinder = ButterKnife.bind(this, view);
        Timber.w("ONCREATEVIEW");

        init();

        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(mContext);
//        sharedPrefs.registerOnSharedPreferenceChangeListener(mContext);


//        useGridView = sharedPrefs.getString("viewType", "list").equals("grid");


        // Update title
//        updateTitle();

//        setupAnimeListView();
//        update();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mAnimeListPresenter.resume();

        // reset the layout
//        mRevealLayout.setVisibility(View.INVISIBLE);

        Timber.w("ONRESUME");
    }


    @Override
    public void onStop() {
        super.onStop();
        mAnimeListPresenter.stop();
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        Timber.w("ONDESTROYVIEW");
        super.onDestroyView();
    }

    private void init() {
        // setup recycler view adapter
        adapter = new AnimeAdapter(mContext, useGridView, this);

        // init divider for list
        itemDecoration = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST);

        // Add the animation on items when sliding
        if (useGridView) {
            mLayoutManager = new GridLayoutManager(mContext, (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) ? 2 : 4);
        } else {
            mLayoutManager = new LinearLayoutManager(mContext);
            mAnimeListRecyclerView.addItemDecoration(itemDecoration);
        }

        // setup recycler view

        // Attach the layout manager to the recycler view
        mAnimeListRecyclerView.setLayoutManager(mLayoutManager);

        mAnimeListRecyclerView.setHasFixedSize(true);
        mAnimeListRecyclerView.setAdapter(adapter);


        // instantiate the presenter
        mAnimeListPresenter = new AnimeListPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new AnimeRepositoryImpl(mContext)
        );

    }

    // Create anime list view
    protected void setupAnimeListView() {
        mAnimeListRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        // Add the animation on items when sliding
        if (useGridView) {
            mLayoutManager = new GridLayoutManager(mContext, (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) ? 2 : 4);
        } else {
            mLayoutManager = new LinearLayoutManager(mContext);
            mAnimeListRecyclerView.addItemDecoration(itemDecoration);
        }
        // Attach the layout manager to the recycler view
        mAnimeListRecyclerView.setLayoutManager(mLayoutManager);

        mAnimeListRecyclerView.setHasFixedSize(true);
//        mAnimeListRecyclerView.addOnItemTouchListener(
//                new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(View view, int position) {
//                        Anime anime = getAnimeUpdater().getAnimeList().get(position);
//
//                        Intent intent = new Intent();
//                        intent.setClass(mContext, AnimeActivity.class);
//                        intent.putExtra("clickedAnime", anime);
//
//                        Toast.makeText(view.getContext(), anime.title + " was clicked", Toast.LENGTH_SHORT).show();
//                        startActivity(intent);

        // Video URL
        //				if((anime.mProvider.url != null) && !anime.mProvider.url.isEmpty()) {
        //					Uri videoURI = Uri.parse(anime.mProvider.url);
        //					intent = new Intent(Intent.ACTION_VIEW, videoURI);
        //					intent.setDataAndType(videoURI, "video/*");
        //
        //					try {
        //						startActivity(intent);
        //						return;
        //					} catch (ActivityNotFoundException e) {
        //						Log.d("AnimeListFragment", e.toString());
        //					}
        //				}

        // Next episode URL
//						if(anime.episodes.watched < (anime.episodes.available - anime.episodes.offset)) {
//							if ((anime.mProvider.nextEpisode.url != null) && !anime.mProvider.nextEpisode.url.isEmpty()) {
//								Uri uri = Uri.parse(anime.mProvider.nextEpisode.url);
//								intent = new Intent(Intent.ACTION_VIEW, uri);
//
//								try {
//									startActivity(intent);
//									return;
//								} catch (ActivityNotFoundException e) {
//									Log.d("AnimeListFragment", e.toString());
//								}
//							}
//						}


        // Anime provider URL
//						if (anime.mProvider.url != null && !anime.mProvider.url.isEmpty()) {
//							Uri uri = Uri.parse(anime.mProvider.url);
//							intent = new Intent(Intent.ACTION_VIEW, uri);
//
//							try {
//								startActivity(intent);
//								return;
//							} catch (ActivityNotFoundException e) {
//								Log.d("AnimeListFragment", e.toString());
//							}
//						}

        //				// List provider URL
        //				if(anime.url != null && !anime.url.isEmpty()) {
        //					Uri uri = Uri.parse(anime.url);
        //					intent = new Intent(Intent.ACTION_VIEW, uri);
        //
        //					try {
        //						startActivity(intent);
        //						return;
        //					} catch (ActivityNotFoundException e) {
        //						Log.d("AnimeListFragment", e.toString());
        //					}
        //				}
//                    }
//                })
//        );

    }

//    @Override
//    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
//        switch (key) {
//            case "animeProvider":
//            case "userName":
//                update();
//                break;
//        }
//    }


//    // GetCacheKey
//    protected String getCacheKey() {
//        return "cachedAnimeListJSON";
//    }
//
//    // DisplayUserNameMissingNotification
//    protected void displayUserNameMissingNotification() {
//        // This notification should never be sent more than once in 5 seconds
//        if ((System.currentTimeMillis() - lastUserNameMissingNotification) < 5000)
//            return;
//
//        // Save the last time this notification happened
//        lastUserNameMissingNotification = System.currentTimeMillis();
//
//        // Text message
//        Toast.makeText(mContext, "Please enter your ARN username", Toast.LENGTH_SHORT).show();
//
//        try {
//            startActivity(showSettingsIntent);
//        } catch (IllegalStateException e) {
//            Log.d("AnimeListFragment", "Not attached to activity");
//        }
//    }
//
//    // Update
//    protected void update() {
//        String userName = sharedPrefs.getString("userName", "");
//        String cachedJSON = sharedPrefs.getString(getCacheKey(), "");
//
//        final AnimeUpdater updater = getAnimeUpdater();
//
//        if (userName.isEmpty()) {
//            displayUserNameMissingNotification();
//            return;
//        }
//
//        if (!cachedJSON.isEmpty()) {
//            updater.update(cachedJSON, mContext, new AnimeListUpdateCallBack() {
//                @Override
//                public void execute() {
//                    onReceiveAnimeList();
//                }
//            });
//        } else {
//            mLoadingSpinner.setVisibility(View.VISIBLE);
//            mLoadingTextView.setVisibility(View.VISIBLE);
//        }
//
//        // Update in the background
//        updater.updateByUser(userName, mContext, new AnimeListUpdateCallBack() {
//            @Override
//            public void execute() {
//                onReceiveAnimeList();
//            }
//        });
//
//    }
//
//    protected void onReceiveAnimeList() {
//        getActivity().runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                mLoadingSpinner.setVisibility(View.GONE);
//                mLoadingTextView.setVisibility(View.GONE);
//                mAnimeListRecyclerView.setVisibility(View.VISIBLE);
//                adapter.notifyDataSetChanged();
//            }
//        });
//
//    }

    @Override
    public void showAnimeList(AnimeList animeList) {
        // signal the adapter that it has data to show
        adapter.addNewAnimes(animeList);
    }


    @Override
    public void onClickDownload(long animeId, int position) {

        // TODO: 22/08/2016 redirect the user to the next episode or the anime provider or the anime website in the last case.
        Timber.d("Click on Anime action");
    }

    @Override
    public void onClickAnime(long animeId) {
        // intent to start another activity
        final Intent intent = new Intent(mContext, AnimeActivity.class);
        intent.putExtra(EXTRA_ANIME_ID, animeId);

        startActivity(intent);
    }

    @Override
    public void showProgress() {
        mLoadingSpinner.setVisibility(View.VISIBLE);
        mLoadingTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mLoadingSpinner.setVisibility(View.GONE);
        mLoadingTextView.setVisibility(View.GONE);

    }

    @Override
    public void showError(String message) {

    }
}
