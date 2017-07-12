package com.vzaar.apiclient.sample.videolist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vzaar.apiclient.model.Video;
import com.vzaar.apiclient.sample.R;

class VideoListItemViewHolder extends RecyclerView.ViewHolder {
    private final TextView nameText;
    private final ImageView image;

    VideoListItemViewHolder(View view) {
        super(view);
        nameText = (TextView) view.findViewById(R.id.video_item_text_name);
        image = (ImageView) view.findViewById(R.id.video_item_image);
    }

    void bind(Video video) {
        nameText.setText(video.getTitle());
        Picasso.with(itemView.getContext())
                .load(video.getThumbnailUrl())
                .into(image);
    }
}