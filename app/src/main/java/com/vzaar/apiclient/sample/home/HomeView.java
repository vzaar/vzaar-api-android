package com.vzaar.apiclient.sample.home;

import com.hannesdorfmann.mosby.mvp.MvpView;

public interface HomeView extends MvpView {
    void launchUploadVideoView();

    void launchCategoryListView();

    void onUploadButtonClick();

    void onBrowseButtonClick();
}
