package com.vzaar.apiclient.sample.videolist;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceActivity;
import com.vzaar.apiclient.Vzaar;
import com.vzaar.apiclient.model.Video;
import com.vzaar.apiclient.sample.R;
import com.vzaar.apiclient.sample.databinding.ActivityVideoListBinding;
import com.vzaar.apiclient.sample.util.ItemSelectedListener;
import com.vzaar.apiclient.sample.videodetail.VideoDetailActivity;

import java.util.List;

import static android.view.View.GONE;

public class VideoListActivity extends MvpLceActivity<RecyclerView, List<Video>, VideoListView, VideoListPresenter>
        implements VideoListView, ItemSelectedListener<Video> {

    public static final String ARG_CATEGORY_ID = "arg_category_id";
    private ActivityVideoListBinding binding;
    private VideoListRecyclerAdapter recyclerAdapter;

    private Integer argCategoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_video_list);
        binding.setHandler(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerAdapter = new VideoListRecyclerAdapter(this);
        binding.videoListRecyclerView.setAdapter(recyclerAdapter);
        binding.videoListRecyclerView.setLayoutManager(layoutManager);

        Bundle args = getIntent().getExtras();

        if (args != null) {
            if (args.containsKey(ARG_CATEGORY_ID)) {
                argCategoryId = args.getInt(ARG_CATEGORY_ID);
            }
        }

        setBackEnabled(argCategoryId != null);
        setTitle("");
        loadData(false);
        presenter.loadTitle(argCategoryId);
    }

    @Override
    public void onLoadNextRequested() {
        loadData(false);
    }

    @Override
    public void onItemSelected(Video video) {
        presenter.selectVideo(video);
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return "There was a videos error....";
    }

    @NonNull
    @Override
    public VideoListPresenter createPresenter() {
        return new VideoListPresenter(new VideoListInteractor(Vzaar.getInstance()));
    }

    @Override
    public void setData(List<Video> data) {
        recyclerAdapter.setVideos(data);
        recyclerAdapter.notifyDataSetChanged();
    }


    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadMoreVideos(argCategoryId);
    }

    @Override
    public void navigateToVideoDetail(Video video) {
        Intent intent = new Intent(this, VideoDetailActivity.class);
        intent.putExtra(VideoDetailActivity.ARG_VIDEO_ID, video.getId());
        startActivity(intent);
    }

    @Override
    public void onLoadAllCompleted() {
        binding.videoListButtonLoad.setVisibility(GONE);
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

    @Override
    public void setCategoryTitle(String title) {
        setTitle(title);
    }

    private void setBackEnabled(boolean enabled) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(enabled);
        }
    }
}
