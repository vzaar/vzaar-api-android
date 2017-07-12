package com.vzaar.apiclient.sample.videolist;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.vzaar.apiclient.model.Category;
import com.vzaar.apiclient.model.Video;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ConstantConditions")
class VideoListPresenter extends MvpBasePresenter<VideoListView> implements VideoListInteractor.GetVideosListener, VideoListInteractor.GetCategoryListener {

    private final List<Video> curData;
    private final VideoListInteractor interactor;
    private int curPage;

    VideoListPresenter(VideoListInteractor interactor) {
        this.curPage = 0;
        this.curData = new ArrayList<>();
        this.interactor = interactor;
    }

    void selectVideo(Video video) {
        if (isViewAttached()) {
            getView().navigateToVideoDetail(video);
        }
    }

    void loadTitle(Integer categoryId) {
        boolean hasParentCategory = categoryId != null;
        if (hasParentCategory) {
            interactor.getCategory(categoryId, this);
        }
    }

    void loadMoreVideos(Integer categoryId) {
        if (isViewAttached()) {
            getView().showLoading(false);
        }

        interactor.getVideos(categoryId, curPage, this);
    }

    @Override
    public void onLoadVideosFailure() {
        if (isViewAttached()) {
            getView().showError(null, false);
        }
    }

    @Override
    public void onLoadVideosSuccess(List<Video> videos) {
        curPage++;
        curData.addAll(videos);
        if (isViewAttached()) {
            getView().setData(curData);
            getView().showContent();
        }
    }

    @Override
    public void onLoadCategorySuccess(Category category) {
        if (isViewAttached()) {
            getView().setCategoryTitle(category.getName());
        }
    }

    @Override
    public void onLoadVideosEnd() {
        if (isViewAttached()) {
            getView().onLoadAllCompleted();
        }
    }

    @Override
    public void onLoadCategoryFailure() {
        if (isViewAttached()) {
            getView().setCategoryTitle("");
        }
    }
}
