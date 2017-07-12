package com.vzaar.apiclient.sample.categorylist;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import com.vzaar.apiclient.model.Category;
import com.vzaar.apiclient.sample.util.LoadableViewListener;

import java.util.List;

public interface CategoryListView extends MvpLceView<List<Category>>, LoadableViewListener {
    void navigateToVideoList(Category category);

    void navigateToCategoryList(Category category);

    void setScreenTitle(String title);


}

