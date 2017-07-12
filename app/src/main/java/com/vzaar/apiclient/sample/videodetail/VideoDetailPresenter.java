package com.vzaar.apiclient.sample.videodetail;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.vzaar.apiclient.model.Video;

import timber.log.Timber;

@SuppressWarnings("ConstantConditions")
class VideoDetailPresenter extends MvpBasePresenter<VideoDetailView> implements VideoDetailInteractor.VideoDetailLoadedListener {

    private final VideoDetailInteractor interactor;

    public VideoDetailPresenter(VideoDetailInteractor interactor) {
        this.interactor = interactor;
    }

    void loadVideo(int videoId) {
        Timber.d("Load video %d", videoId);

        if (isViewAttached()) {
            getView().showLoading(false);
        }

        interactor.getVideoDetail(videoId, this);

    }

    @Override
    public void onVideoDetailLoaded(Video video) {
        if (isViewAttached()) {
            getView().setData(new VideoDetailViewModel(video));
            getView().showContent();
        }
    }

    @Override
    public void onFailure(Throwable e) {
        e.printStackTrace();
        if (isViewAttached()) {
            getView().showError(e, false);
        }
    }
}
