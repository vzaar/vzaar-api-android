package com.vzaar.apiclient.sample.videodetail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceActivity;
import com.squareup.picasso.Picasso;
import com.vzaar.apiclient.Vzaar;
import com.vzaar.apiclient.sample.R;
import com.vzaar.apiclient.sample.databinding.ActivityVideoDetailBinding;

import timber.log.Timber;

public class VideoDetailActivity extends MvpLceActivity<ViewGroup, VideoDetailViewModel, VideoDetailView, VideoDetailPresenter> implements VideoDetailView {
    public static final String ARG_VIDEO_ID = "arg_video_id";

    private ActivityVideoDetailBinding binding;
    private int videoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_video_detail);
        videoId = getIntent().getIntExtra(ARG_VIDEO_ID, -1);

        setSupportActionBar(binding.videoDetailToolbar);
        setBackEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData(false);
    }

    @Override
    public void setData(VideoDetailViewModel data) {
        binding.setViewModel(data);
        binding.videoDetailCollapsingToolbar.setTitle(data.getTitle());
        Picasso.with(this).load(data.getImageUrl()).into(binding.videoDetailImage);
    }

    @NonNull
    @Override
    public VideoDetailPresenter createPresenter() {
        return new VideoDetailPresenter(new VideoDetailInteractor(Vzaar.getInstance()));
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getMessage();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        Timber.d("Load video %d", videoId);
        presenter.loadVideo(videoId);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setBackEnabled(boolean enabled) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(enabled);
        }
    }
}
