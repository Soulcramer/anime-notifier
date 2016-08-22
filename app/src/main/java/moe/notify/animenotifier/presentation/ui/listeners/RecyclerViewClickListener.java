package moe.notify.animenotifier.presentation.ui.listeners;


public interface RecyclerViewClickListener {

    void onClickView(int position);

    void onClickDownload(int position, long animeId);

}