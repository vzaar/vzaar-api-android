package com.vzaar.apiclient.sample.videodetail;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.vzaar.apiclient.model.Video;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class VideoDetailViewModel extends BaseObservable {

    private final Video video;

    public VideoDetailViewModel(Video video) {
        this.video = video;
    }

    @Bindable
    public String getDuration() {
        return Float.toString(video.getDuration());
    }

    @Bindable
    public String getCreatedAt() {
        SimpleDateFormat formatFrom = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ",
                Locale.getDefault());
        try {
            Date date = formatFrom
                    .parse(video.getCreatedAt().replaceAll("Z$", "+0000"));

            SimpleDateFormat formatTo = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss",
                    Locale.getDefault());
            return formatTo.format(date);
        } catch (ParseException e) {
            return "";
        }

    }

    @Bindable
    public String getTitle() {
        return video.getTitle();
    }

    @Bindable
    public String getDescription() {
        return video.getDescription();
    }

    public String getImageUrl() {
        return video.getThumbnailUrl();
    }
}
