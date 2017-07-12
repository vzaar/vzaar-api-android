package com.vzaar.apiclient.sample.categorylist;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceActivity;
import com.vzaar.apiclient.Vzaar;
import com.vzaar.apiclient.model.Category;
import com.vzaar.apiclient.sample.R;
import com.vzaar.apiclient.sample.databinding.ActivityCategoryListBinding;
import com.vzaar.apiclient.sample.util.ItemSelectedListener;
import com.vzaar.apiclient.sample.videolist.VideoListActivity;

import java.util.List;

import static android.view.View.GONE;

public class CategoryListActivity extends MvpLceActivity<RecyclerView, List<Category>, CategoryListView, CategoryListPresenter>
        implements CategoryListView, ItemSelectedListener<Category> {

    private static final String ARG_CATEGORY_NAME = "arg_category_name";
    private static final String ARG_CATEGORY_ID = "arg_category_id";
    private ActivityCategoryListBinding binding;
    private CategoryListRecyclerAdapter recyclerAdapter;

    private Integer argCategoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_category_list);
        binding.setLoadableViewHandler(this);
        binding.setHandler(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerAdapter = new CategoryListRecyclerAdapter(this);
        binding.categoryListRecyclerView.setAdapter(recyclerAdapter);
        binding.categoryListRecyclerView.setLayoutManager(layoutManager);

        Bundle args = getIntent().getExtras();

        if (args != null) {
            if (args.containsKey(ARG_CATEGORY_ID)) {
                argCategoryId = args.getInt(ARG_CATEGORY_ID);
            }
            if (args.containsKey(ARG_CATEGORY_NAME)) {
                setScreenTitle(args.getString(ARG_CATEGORY_NAME));
            }
        }

        setBackEnabled(argCategoryId != null);
        loadData(false);
    }

    @Override
    public void onLoadNextRequested() {
        loadData(false);
    }

    @Override
    public void onItemSelected(Category category) {
        presenter.selectCategory(category);
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getMessage();
    }

    @NonNull
    @Override
    public CategoryListPresenter createPresenter() {
        return new CategoryListPresenter(
                new CategoryListInteractor(Vzaar.getInstance()));
    }

    @Override
    public void setData(List<Category> data) {
        recyclerAdapter.setCategories(data);
        recyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadMoreCategories(argCategoryId);
    }

    @Override
    public void navigateToVideoList(Category category) {
        Intent intent = new Intent(this, VideoListActivity.class);
        intent.putExtra(VideoListActivity.ARG_CATEGORY_ID, category.getId());
        startActivity(intent);
    }

    @Override
    public void navigateToCategoryList(Category category) {
        Intent intent = new Intent(this, CategoryListActivity.class);
        intent.putExtra(CategoryListActivity.ARG_CATEGORY_ID, category.getId());
        startActivity(intent);
    }

    @Override
    public void setScreenTitle(String title) {
        setTitle(title);
    }

    private void setBackEnabled(boolean enabled) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(enabled);
        }
    }

    @Override
    public void onLoadAllCompleted() {
        binding.categoryListButtonLoad.setVisibility(GONE);
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
}
