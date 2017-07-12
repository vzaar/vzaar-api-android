package com.vzaar.apiclient.request;

import com.vzaar.apiclient.VzaarCallback;

/**
 * A request to get a list of encoding presets.
 * <p/>
 * {@inheritDoc}
 *
 * @see com.vzaar.apiclient.Vzaar#getEncodingPresets(GetEncodingPresetsRequest, VzaarCallback)
 */
public class GetEncodingPresetsRequest extends VzaarRequest {

    private GetEncodingPresetsRequest() {
        super("api/v2/encoding_presets", Method.GET);
    }

    public static class Builder {

        private final GetEncodingPresetsRequest obj;

        public Builder() {
            obj = new GetEncodingPresetsRequest();
        }

        public Builder setSort(String value) {
            obj.addQueryParam("sort", value);
            return this;
        }

        public Builder setOrder(String order) {
            obj.addQueryParam("order", order);
            return this;
        }

        public Builder setPage(int value) {
            obj.addQueryParam("page", Integer.toString(value));
            return this;
        }

        public Builder setPageSize(int pageSize) {
            obj.addQueryParam("per_page", Integer.toString(pageSize));
            return this;
        }

        public GetEncodingPresetsRequest build() {
            return obj;
        }
    }
}
