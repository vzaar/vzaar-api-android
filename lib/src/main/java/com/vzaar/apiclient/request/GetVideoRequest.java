package com.vzaar.apiclient.request;

import com.vzaar.apiclient.VzaarCallback;

/**
 * A request to look up a video.
 * <p/>
 * {@inheritDoc}
 *
 * @see com.vzaar.apiclient.Vzaar#getVideo(GetVideoRequest, VzaarCallback)
 */
public class GetVideoRequest extends VzaarRequest {

    private GetVideoRequest() {
        super("api/v2/videos/{id}", Method.GET);
    }

    public static class Builder {

        private final GetVideoRequest obj;

        public Builder(int id) {
            obj = new GetVideoRequest();
            obj.addPathParam("id", Integer.toString(id));
        }

        public GetVideoRequest build() {
            return obj;
        }

    }

}
