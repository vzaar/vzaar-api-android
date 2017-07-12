package com.vzaar.apiclient.sample.videolist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vzaar.apiclient.model.Video;
import com.vzaar.apiclient.sample.R;
import com.vzaar.apiclient.sample.util.ItemSelectedListener;

import java.util.ArrayList;
import java.util.List;

class VideoListRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final ItemSelectedListener<Video> interactionListener;
    private List<Video> videos;

    VideoListRecyclerAdapter(ItemSelectedListener<Video> interactionListener) {
        this.videos = new ArrayList<>();
        this.interactionListener = interactionListener;
    }

    void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    @Override
    public VideoListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_video_item, parent, false);
        return new VideoListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Video item = videos.get(position);
        ((VideoListItemViewHolder) holder).bind(item);
        ((VideoListItemViewHolder) holder).itemView
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        interactionListener.onItemSelected(item);
                    }
                });
    }
}
