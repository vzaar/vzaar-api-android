package com.vzaar.apiclient.sample.videolist;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import com.vzaar.apiclient.model.Video;
import com.vzaar.apiclient.sample.util.LoadableViewListener;

import java.util.List;

interface VideoListView extends MvpLceView<List<Video>>, LoadableViewListener {
    void navigateToVideoDetail(Video video);

    void setCategoryTitle(String title);

}
