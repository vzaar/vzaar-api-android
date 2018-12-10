package com.vzaar.apiclient.request;

import com.vzaar.apiclient.VzaarCallback;

/**
 * A request to update the image frame.
 * <p/>
 * {@inheritDoc}
 *
 * @see com.vzaar.apiclient.Vzaar#getSubtitles(GetSubtitlesRequest, VzaarCallback)
 */
public class GetSubtitlesRequest extends VzaarRequest {

    private GetSubtitlesRequest() {
        super("api/v2/videos/{id}/subtitles", Method.GET);
    }

    public static class Builder {
        private final GetSubtitlesRequest obj;

        public Builder(int id) {
            obj = new GetSubtitlesRequest();
            obj.addPathParam("id", Integer.toString(id));
        }

        public GetSubtitlesRequest build() {
            return obj;
        }
    }

}
