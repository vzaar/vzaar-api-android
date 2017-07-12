package com.vzaar.apiclient.sample.videoupload;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.vzaar.apiclient.Vzaar;
import com.vzaar.apiclient.sample.R;
import com.vzaar.apiclient.sample.databinding.ActivityVideoUploadBinding;

import timber.log.Timber;

public class VideoUploadActivity extends MvpActivity<VideoUploadView, VideoUploadPresenter> implements VideoUploadView {

    private static final int REQUEST_CODE_FILE = 1;
    private ActivityVideoUploadBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_video_upload);
        binding.setHandler(this);
        presenter.start();
    }

    @NonNull
    @Override
    public VideoUploadPresenter createPresenter() {
        return new VideoUploadPresenter(
                new VideoUploadInteractor(
                        Vzaar.getInstance(),
                        getContentResolver()
                ));
    }

    @Override
    public void launchFilePicker() {
        Intent intent = new Intent()
                .setType("video/*")
                .setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(intent, "Select a file"), REQUEST_CODE_FILE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_FILE && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            Timber.d("selected file: %s", uri);
            presenter.selectVideoUriForUpload(uri);
        }
    }

    @Override
    public void onUploadButtonClick() {
        presenter.onUploadButtonPress();
    }

    @Override
    public void hideError() {
        binding.videoUploadError.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        binding.videoUploadProgressBar.setVisibility(View.INVISIBLE);
        binding.videoUploadProgressTitle.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideSuccess() {
        binding.videoUploadSuccess.setVisibility(View.GONE);
    }

    @Override
    public void hideUploadForm() {
        binding.videoUploadTitle.setVisibility(View.INVISIBLE);
        binding.videoUploadTitleLabel.setVisibility(View.INVISIBLE);
        binding.videoUploadDescription.setVisibility(View.INVISIBLE);
        binding.videoUploadDescriptionLabel.setVisibility(View.INVISIBLE);
        binding.videoUploadButton.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setError(int messageId) {
        binding.videoUploadError.setText(getResources().getText(messageId));
    }

    @Override
    public void setProgressBarProgress(int progress) {
        binding.videoUploadProgressBar.setProgress(progress);
    }

    @Override
    public void setSuccess(int messageId) {
        binding.videoUploadSuccess.setText(getResources().getText(messageId));
    }

    @Override
    public void showError() {
        binding.videoUploadError.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress() {
        binding.videoUploadProgressBar.setVisibility(View.VISIBLE);
        binding.videoUploadProgressTitle.setVisibility(View.VISIBLE);
    }

    @Override
    public void showSuccess() {
        binding.videoUploadSuccess.setVisibility(View.VISIBLE);
    }

    @Override
    public void showUploadForm() {
        binding.videoUploadTitle.setVisibility(View.VISIBLE);
        binding.videoUploadTitleLabel.setVisibility(View.VISIBLE);
        binding.videoUploadDescription.setVisibility(View.VISIBLE);
        binding.videoUploadDescriptionLabel.setVisibility(View.VISIBLE);
        binding.videoUploadButton.setVisibility(View.VISIBLE);
    }

    @Override
    public String getDescriptionInput() {
        return binding.videoUploadDescription.getText().toString();
    }

    @Override
    public String getTitleInput() {
        return binding.videoUploadTitle.getText().toString();
    }
}
