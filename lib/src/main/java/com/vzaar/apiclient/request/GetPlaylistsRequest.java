package com.vzaar.apiclient.request;

import com.vzaar.apiclient.VzaarCallback;

/**
 * A request to get a list of playlists.
 * <p/>
 * {@inheritDoc}
 *
 * @see com.vzaar.apiclient.Vzaar#getPlaylists(GetPlaylistsRequest, VzaarCallback)
 */
public class GetPlaylistsRequest extends VzaarRequest {

    private GetPlaylistsRequest() {
        super("api/v2/playlists", Method.GET);
    }

    public static class Builder {

        private final GetPlaylistsRequest obj;

        public Builder() {
            obj = new GetPlaylistsRequest();
        }

        public Builder setPage(int page) {
            obj.addQueryParam("page", Integer.toString(page));
            return this;
        }

        public Builder setOrder(String order) {
            obj.addQueryParam("order", order);
            return this;
        }

        public Builder setSort(String sort) {
            obj.addQueryParam("sort", sort);
            return this;
        }

        public Builder setPerPage(int pageSize) {
            obj.addQueryParam("per_page", Integer.toString(pageSize));
            return this;
        }

        public GetPlaylistsRequest build() {
            return obj;
        }
    }
}
