package com.vzaar.apiclient.request;

import com.vzaar.apiclient.VzaarCallback;

/**
 * A request to create a category.
 * <p/>
 * {@inheritDoc}
 *
 * @see com.vzaar.apiclient.Vzaar#deleteCategory(DeleteCategoryRequest, VzaarCallback)
 */
public class DeleteCategoryRequest extends VzaarRequest {

    private DeleteCategoryRequest() {
        super("api/v2/categories/{id}", Method.DELETE);
    }

    public static class Builder {
        private final DeleteCategoryRequest obj;

        public Builder(int id) {
            obj = new DeleteCategoryRequest();
            obj.addPathParam("id", Integer.toString(id));
        }

        public DeleteCategoryRequest build() {
            return obj;
        }
    }
}
