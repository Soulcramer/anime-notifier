package moe.notify.animenotifier.presentation.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.freezingwind.animereleasenotifier.R;
import moe.notify.animenotifier.domain.model.animelist.AnimeList;
import moe.notify.animenotifier.domain.model.animelist.WatchingAnime;
import moe.notify.animenotifier.presentation.presenters.AnimeListPresenter;
import moe.notify.animenotifier.presentation.ui.listeners.IndividualAnimeViewClickListener;
import moe.notify.animenotifier.presentation.ui.listeners.RecyclerViewClickListener;

public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.ViewHolder>
    implements RecyclerViewClickListener {

  private static final String TAG = "AnimeAdapter";
  private final AnimeListPresenter.View view;
  // Store a member variable for the contacts
  private AnimeList animeList;
  private boolean grid;

  // Pass in the contact array into the constructor
  public AnimeAdapter(Context context, boolean grid, AnimeListPresenter.View view) {
    animeList = new AnimeList();
    this.view = view;
    this.grid = grid;
    Context context1 = context;
  }

  public boolean isGrid() {
    return grid;
  }

  public void setGrid(boolean grid) {
    this.grid = grid;
  }

  @Override public AnimeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    Context context = parent.getContext();
    LayoutInflater inflater = LayoutInflater.from(context);

    // Inflate the custom layout
    View animeView = inflater.inflate(grid ? R.layout.cell1 : R.layout.row1, parent, false);

    // Return a new holder instance
    return new ViewHolder(animeView, this);
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    WatchingAnime anime = animeList.getAnimes().get(position);
    holder.setup(anime);
  }

  @Override public int getItemCount() {
    return (animeList == null) ? 0 : animeList.getAnimes().size();
  }

  public void addNewAnimes(@NonNull AnimeList animeList) {
    // clean up old data
    if (this.animeList != null) {
      this.animeList.getAnimes().clear();
    }
    this.animeList = animeList;

    notifyDataSetChanged();
  }

  @Override public void onClickView(int position) {

    view.onClickAnime(animeList.getAnimes().get(position).getId());
  }

  @Override public void onClickAction(int position) {
    view.onClickDownload(animeList.getAnimes().get(position).getId(), position);
  }

  static class ViewHolder extends RecyclerView.ViewHolder
      implements View.OnClickListener, IndividualAnimeViewClickListener {

    private final RecyclerViewClickListener mListener;
    @BindView(R.id.title) TextView titleTextView;
    @BindView(R.id.airingDate) TextView airingTextView;
    @BindView(R.id.image) ImageView animeImageView;
    @BindView(R.id.actionImage) ImageView actionImageView;

    ViewHolder(View itemView, final RecyclerViewClickListener listener) {
      super(itemView);
      ButterKnife.bind(this, itemView);

      mListener = listener;

      actionImageView.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View view) {
          mListener.onClickAction(getAdapterPosition());
        }
      });

      itemView.setOnClickListener(this);
    }

    void setup(WatchingAnime anime) {
      Context context = titleTextView.getContext();

      titleTextView.setText(anime.getTitle().getRomaji());

      // TODO: 31/07/2016 this string cut the remaining time n a gridview on landscape
      //        String remainingText;
      ////        if(!animeList.mDate.remainingString.isEmpty()){
      ////            remainingText = ((animeList.episodes.watched < animeList.episodes.available) ? "Released " : "Released in ") +animeList.mDate.remainingString;
      ////        }else{
      ////            remainingText = "";
      ////        }
      airingTextView.setText(anime.getAiringDate().getRemainingString());

      // Mark as new
      //            if (animeList.episodes.watched < (animeList.episodes.available - animeList.episodes.offset)) {
      //                actionImageView.setImageResource(R.drawable.ic_cloud_download_black_24dp);
      ////            holder.itemView.setBackgroundColor(Color.rgb(248,165,130));
      //            } else if (animeList.airingStatus.equals("completed") || (animeList.episodes.available == animeList.episodes.watched)) {
      //                actionImageView.setImageResource(R.drawable.ic_check_black_24dp);
      //            } else {
      //                actionImageView.setImageResource(R.drawable.ic_error_outline_black_24dp);
      //            }

      // Image
      Glide.with(context).load(anime.getImage()).crossFade().centerCrop().into(animeImageView);
    }

    @Override public void onClick(View view) {
      mListener.onClickView(getAdapterPosition());
    }

    @Override public void onClickAction(long animeId) {
      mListener.onClickAction(getAdapterPosition());
    }
  }
}