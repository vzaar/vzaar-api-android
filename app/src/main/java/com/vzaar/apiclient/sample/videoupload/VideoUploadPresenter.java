package com.vzaar.apiclient.sample.videoupload;

import android.net.Uri;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.vzaar.apiclient.model.Video;
import com.vzaar.apiclient.sample.R;

@SuppressWarnings("ConstantConditions")
public class VideoUploadPresenter extends MvpBasePresenter<VideoUploadView> {
    private final VideoUploadInteractor videoUploadInteractor;

    public VideoUploadPresenter(VideoUploadInteractor videoUploadInteractor) {
        this.videoUploadInteractor = videoUploadInteractor;
    }

    @Override
    public void attachView(VideoUploadView view) {
        super.attachView(view);
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        videoUploadInteractor.cancelAll();
    }

    void start() {
        if (isViewAttached()) {
            getView().hideSuccess();
            getView().hideProgress();
            getView().hideError();
            getView().showUploadForm();
        }
    }

    void selectVideoUriForUpload(Uri uri) {
        String title = isViewAttached() ? getView().getTitleInput() : "";
        String description = isViewAttached() ? getView().getDescriptionInput() : "";

        videoUploadInteractor.uploadVideo(uri, title, description,
                new VideoUploadInteractor.VideoUploadCompletedListener() {
                    @Override
                    public void onVideoUploadStarted() {
                        if (isViewAttached()) {
                            getView().hideSuccess();
                            getView().hideError();
                            getView().hideUploadForm();
                            getView().showProgress();
                        }
                    }

                    @Override
                    public void onVideoUploadCompleted(Video video) {
                        if (isViewAttached()) {
                            getView().setSuccess(R.string.message_upload_success);
                            getView().showSuccess();
                            getView().hideProgress();
                            getView().hideError();
                            getView().showUploadForm();
                        }
                    }

                    @Override
                    public void onVideoUploadFailed(Throwable e) {
                        if (isViewAttached()) {
                            getView().setError(R.string.message_upload_failure);
                            getView().showError();
                            getView().hideProgress();
                            getView().hideSuccess();
                            getView().showUploadForm();
                        }
                    }

                    @Override
                    public void onVideoUploadProgress(float percent) {
                        if (isViewAttached()) {
                            getView().setProgressBarProgress((int) percent);
                            getView().showProgress();
                        }
                    }
                });
    }

    void onUploadButtonPress() {
        if (isViewAttached()) {
            getView().launchFilePicker();
        }
    }
}
