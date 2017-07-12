package com.vzaar.apiclient.request;

import com.vzaar.apiclient.VzaarCallback;

/**
 * A request to look up a category.
 * <p/>
 * {@inheritDoc}
 *
 * @see com.vzaar.apiclient.Vzaar#getCategory(GetCategoryRequest, VzaarCallback)
 */
public class GetCategoryRequest extends VzaarRequest {

    private GetCategoryRequest() {
        super("api/v2/categories/{id}", Method.GET);
    }

    public static class Builder {
        private final GetCategoryRequest obj;

        public Builder(int id) {
            obj = new GetCategoryRequest();
            obj.addPathParam("id", Integer.toString(id));
        }

        public GetCategoryRequest build() {
            return obj;
        }
    }
}
