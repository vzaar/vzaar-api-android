package com.vzaar.apiclient.request;

import com.vzaar.apiclient.VzaarCallback;

/**
 * A request to get a subtree of categories.
 * <p/>
 * {@inheritDoc}
 *
 * @see com.vzaar.apiclient.Vzaar#getCategoriesSubtree(GetCategoriesSubtreeRequest, VzaarCallback)
 */
public class GetCategoriesSubtreeRequest extends VzaarRequest {

    private GetCategoriesSubtreeRequest() {
        super("api/v2/categories/{id}/subtree", Method.GET);
    }

    public static class Builder {
        private final GetCategoriesSubtreeRequest obj;

        public Builder(int id) {
            obj = new GetCategoriesSubtreeRequest();
            obj.addPathParam("id", Integer.toString(id));
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

        public GetCategoriesSubtreeRequest build() {
            return obj;
        }
    }
}
