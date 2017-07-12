package com.vzaar.apiclient.sample.videoupload;

import com.hannesdorfmann.mosby.mvp.MvpView;

public interface VideoUploadView extends MvpView {

    void onUploadButtonClick();

    void launchFilePicker();

    void showProgress();

    void hideProgress();

    void setProgressBarProgress(int progress);

    void setError(int messageId);

    void showError();

    void hideError();

    void setSuccess(int messageId);

    void showSuccess();

    void hideSuccess();

    void showUploadForm();

    void hideUploadForm();

    String getTitleInput();

    String getDescriptionInput();
}
