package com.vzaar.apiclient.sample.home;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

@SuppressWarnings("ConstantConditions")
public class HomePresenter extends MvpBasePresenter<HomeView> {
    void onUploadButtonClick() {
        if (isViewAttached()) {
            getView().launchUploadVideoView();
        }
    }

    void onBrowseButtonClick() {
        if (isViewAttached()) {
            getView().launchCategoryListView();
        }
    }
}
