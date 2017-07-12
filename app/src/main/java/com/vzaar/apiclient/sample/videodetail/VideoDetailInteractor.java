package com.vzaar.apiclient.sample.videodetail;

import com.vzaar.apiclient.Vzaar;
import com.vzaar.apiclient.VzaarCallback;
import com.vzaar.apiclient.VzaarException;
import com.vzaar.apiclient.model.Video;
import com.vzaar.apiclient.request.GetVideoRequest;
import com.vzaar.apiclient.response.VzaarResponse;

class VideoDetailInteractor {

    private final Vzaar vzaar;

    VideoDetailInteractor(Vzaar vzaar) {
        this.vzaar = vzaar;
    }

    void getVideoDetail(int videoId, final VideoDetailLoadedListener listener) {

        GetVideoRequest request = new GetVideoRequest.Builder(videoId)
                .build();

        vzaar.getVideo(request,
                new VzaarCallback<VzaarResponse<Video>>() {
                    @Override
                    public void onSuccessResponse(VzaarResponse<Video> response) {
                        listener.onVideoDetailLoaded(response.getData());
                    }

                    @Override
                    public void onErrorResponse(VzaarResponse<Video> response) {
                        listener.onFailure(
                                new RuntimeException(response.getErrors().get(0).getMessage()));
                    }

                    @Override
                    public void onNoResponse(VzaarException e) {
                        listener.onFailure(e);
                    }
                });
    }

    interface VideoDetailLoadedListener {
        void onVideoDetailLoaded(Video video);

        void onFailure(Throwable e);

    }
}
