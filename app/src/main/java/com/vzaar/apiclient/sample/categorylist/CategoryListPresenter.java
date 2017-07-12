package com.vzaar.apiclient.sample.categorylist;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.vzaar.apiclient.model.Category;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ConstantConditions")
class CategoryListPresenter extends MvpBasePresenter<CategoryListView> implements CategoryListInteractor.GetCategoriesListener {

    private final List<Category> curData;
    private final CategoryListInteractor categoryListInteractor;
    private int curPage;

    CategoryListPresenter(CategoryListInteractor categoryListInteractor) {
        this.curPage = 0;
        this.curData = new ArrayList<>();
        this.categoryListInteractor = categoryListInteractor;
    }

    void selectCategory(Category category) {
        if (isViewAttached()) {
            if (category.getNodeChildrenCount() > 0) {
                getView().navigateToCategoryList(category);
            } else {
                getView().navigateToVideoList(category);
            }
        }
    }

    void loadMoreCategories(Integer categoryId) {
        if (isViewAttached()) {
            getView().showLoading(false);
        }

        categoryListInteractor.getCategories(categoryId, curPage, this);
    }

    @Override
    public void onLoadCategoriesFailure() {
        if (isViewAttached()) {
            getView().showError(new RuntimeException("There was a problem loading categories."),
                    false);
        }
    }

    @Override
    public void onLoadCategoriesSuccess(List<Category> categories) {
        curPage++;
        curData.addAll(categories);
        if (isViewAttached()) {
            getView().setData(curData);
            getView().showContent();
        }
    }

    @Override
    public void onLoadCategoriesEnd() {
        if (isViewAttached()) {
            getView().onLoadAllCompleted();
            getView().showContent();
        }
    }

    @Override
    public void onParentCategoryAvailable(Category category) {
        if (isViewAttached()) {
            getView().setScreenTitle(category.getName());
        }
    }




}
