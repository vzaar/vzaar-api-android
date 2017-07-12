package com.vzaar.apiclient.sample.videolist;

import com.vzaar.apiclient.Vzaar;
import com.vzaar.apiclient.VzaarCallback;
import com.vzaar.apiclient.VzaarException;
import com.vzaar.apiclient.model.Category;
import com.vzaar.apiclient.model.Video;
import com.vzaar.apiclient.request.GetCategoryRequest;
import com.vzaar.apiclient.request.GetVideosRequest;
import com.vzaar.apiclient.response.VzaarListResponse;
import com.vzaar.apiclient.response.VzaarResponse;

import java.util.List;

class VideoListInteractor {

    private final Vzaar vzaar;

    VideoListInteractor(Vzaar vzaar) {
        this.vzaar = vzaar;
    }

    void getVideos(Integer categoryId, int page, final GetVideosListener listener) {
        GetVideosRequest.Builder requestBuilder = new GetVideosRequest.Builder()
                .setPerPage(5)
                .setPage(page + 1);

        if (categoryId != null) {
            requestBuilder.setCategoryId(categoryId);
        }

        GetVideosRequest request = requestBuilder.build();

        vzaar.getVideos(request, new VzaarCallback<VzaarListResponse<Video>>() {
            @Override
            public void onSuccessResponse(VzaarListResponse<Video> response) {
                listener.onLoadVideosSuccess(response.getData());

                if (response.isFinalPage()) {
                    listener.onLoadVideosEnd();
                }
            }

            @Override
            public void onErrorResponse(VzaarListResponse<Video> response) {
                listener.onLoadVideosFailure();
            }

            @Override
            public void onNoResponse(VzaarException e) {
                e.printStackTrace();
                listener.onLoadVideosFailure();
            }
        });
    }

    void getCategory(int categoryId, final GetCategoryListener listener) {
        final GetCategoryRequest request = new GetCategoryRequest.Builder(categoryId).build();

        vzaar.getCategory(request, new VzaarCallback<VzaarResponse<Category>>() {
            @Override
            public void onSuccessResponse(VzaarResponse<Category> response) {
                listener.onLoadCategorySuccess(response.getData());
            }

            @Override
            public void onErrorResponse(VzaarResponse<Category> response) {
                listener.onLoadCategoryFailure();
            }

            @Override
            public void onNoResponse(VzaarException e) {
                listener.onLoadCategoryFailure();
            }
        });
    }


    interface GetCategoryListener {
        void onLoadCategorySuccess(Category category);

        void onLoadCategoryFailure();
    }

    interface GetVideosListener {
        void onLoadVideosSuccess(List<Video> videos);

        void onLoadVideosEnd();

        void onLoadVideosFailure();
    }
}
