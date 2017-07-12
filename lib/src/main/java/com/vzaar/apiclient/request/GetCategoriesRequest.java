package com.vzaar.apiclient.request;

import com.vzaar.apiclient.VzaarCallback;

import java.util.Arrays;

/**
 * A request to get a list of categories.
 * <p/>
 * {@inheritDoc}
 *
 * @see com.vzaar.apiclient.Vzaar#getCategories(GetCategoriesRequest, VzaarCallback)
 */
public class GetCategoriesRequest extends VzaarRequest {

    private GetCategoriesRequest() {
        super("api/v2/categories", Method.GET);
    }

    public static class Builder {
        private final GetCategoriesRequest obj;

        public Builder() {
            obj = new GetCategoriesRequest();
        }

        public Builder setSort(String value) {
            obj.addQueryParam("sort", value);
            return this;
        }

        public Builder setOrder(String value) {
            obj.addQueryParam("order", value);
            return this;
        }

        public Builder setPage(int value) {
            obj.addQueryParam("page", Integer.toString(value));
            return this;
        }

        public Builder setPerPage(int value) {
            obj.addQueryParam("per_page", Integer.toString(value));
            return this;
        }

        public Builder setLevels(int value) {
            obj.addQueryParam("levels", Integer.toString(value));
            return this;
        }

        public Builder setIds(int[] ids) {
            obj.addQueryParam("ids", Arrays.toString(ids));
            return this;
        }

        public GetCategoriesRequest build() {
            return obj;
        }
    }
}
