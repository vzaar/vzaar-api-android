package com.vzaar.apiclient.request;

import com.vzaar.apiclient.VzaarCallback;

/**
 * A request to update a category.
 * <p/>
 * {@inheritDoc}
 *
 * @see com.vzaar.apiclient.Vzaar#updateCategory(UpdateCategoryRequest, VzaarCallback)
 */
public class UpdateCategoryRequest extends VzaarRequest {

    private UpdateCategoryRequest() {
        super("api/v2/categories/{id}", Method.PATCH);
    }

    public static class Builder {
        private final UpdateCategoryRequest obj;

        public Builder(int id) {
            obj = new UpdateCategoryRequest();
            obj.addPathParam("id", Integer.toString(id));
        }

        public Builder setName(String name) {
            obj.addBodyParam("name", name);
            return this;
        }

        public Builder setParentId(int parentId) {
            obj.addBodyParam("parent_id", parentId);
            return this;
        }

        public Builder setMoveToRoot(boolean moveToRoot) {
            obj.addBodyParam("move_to_root", moveToRoot);
            return this;
        }

        public UpdateCategoryRequest build() {
            return obj;
        }
    }
}
