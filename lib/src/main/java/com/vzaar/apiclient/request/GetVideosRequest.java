package com.vzaar.apiclient.request;

import com.vzaar.apiclient.VzaarCallback;

/**
 * A request to get a list of videos.
 * <p/>
 * {@inheritDoc}
 *
 * @see com.vzaar.apiclient.Vzaar#getVideos(GetVideosRequest, VzaarCallback)
 */
public class GetVideosRequest extends VzaarRequest {

    private GetVideosRequest() {
        super("api/v2/videos", Method.GET);
    }

    public static class Builder {

        private final GetVideosRequest obj;

        public Builder() {
            obj = new GetVideosRequest();
        }

        public Builder setPage(int page) {
            obj.addQueryParam("page", Integer.toString(page));
            return this;
        }

        public Builder setQuery(String query) {
            obj.addQueryParam("q", query);
            return this;
        }

        public Builder setOrder(String order) {
            obj.addQueryParam("order", order);
            return this;
        }

        public Builder setPerPage(int pageSize) {
            obj.addQueryParam("per_page", Integer.toString(pageSize));
            return this;
        }

        public Builder setCategoryId(int categoryId) {
            obj.addQueryParam("category_id", Integer.toString(categoryId));
            return this;
        }

        public Builder setState(String state) {
            obj.addQueryParam("state", state);
            return this;
        }

        public GetVideosRequest build() {
            return obj;
        }
    }
}
