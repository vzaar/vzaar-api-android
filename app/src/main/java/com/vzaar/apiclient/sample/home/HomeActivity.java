package com.vzaar.apiclient.sample.home;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.vzaar.apiclient.sample.R;
import com.vzaar.apiclient.sample.categorylist.CategoryListActivity;
import com.vzaar.apiclient.sample.databinding.ActivityHomeBinding;
import com.vzaar.apiclient.sample.videoupload.VideoUploadActivity;

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
    public void onBrowseButtonClick() {
        presenter.onBrowseButtonClick();
    }

    @Override
    public void onUploadButtonClick() {
        presenter.onUploadButtonClick();
    }
}
