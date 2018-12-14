package com.vzaar.apiclient.sample.home;

import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.vzaar.apiclient.Vzaar;
import com.vzaar.apiclient.VzaarCallback;
import com.vzaar.apiclient.VzaarException;
import com.vzaar.apiclient.model.Subtitle;
import com.vzaar.apiclient.model.Video;
import com.vzaar.apiclient.request.CreateSubtitleRequest;
import com.vzaar.apiclient.request.GetSubtitlesRequest;
import com.vzaar.apiclient.request.UpdateImageFrameRequest;
import com.vzaar.apiclient.request.UploadImageFrameRequest;
import com.vzaar.apiclient.response.VzaarListResponse;
import com.vzaar.apiclient.response.VzaarResponse;
import com.vzaar.apiclient.sample.R;
import com.vzaar.apiclient.sample.categorylist.CategoryListActivity;
import com.vzaar.apiclient.sample.databinding.ActivityHomeBinding;
import com.vzaar.apiclient.sample.videoupload.VideoUploadActivity;

import java.io.File;

public class HomeActivity extends MvpActivity<HomeView, HomePresenter> implements HomeView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityHomeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        binding.setHandler(this);

    }

    @NonNull
    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    public void launchCategoryListView() {
        Intent intent = new Intent(this, CategoryListActivity.class);
        startActivity(intent);
    }

    @Override
    public void launchUploadVideoView() {
        Intent intent = new Intent(this, VideoUploadActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBrowseButtonClick() { presenter.onBrowseButtonClick(); }

    @Override
    public void onUploadButtonClick() {
        presenter.onUploadButtonClick();
    }
}
