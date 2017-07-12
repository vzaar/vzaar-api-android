package com.vzaar.apiclient.request;

import com.vzaar.apiclient.VzaarCallback;

/**
 * A request to create a category.
 * <p/>
 * {@inheritDoc}
 *
 * @see com.vzaar.apiclient.Vzaar#createCategory(CreateCategoryRequest, VzaarCallback)
 */
public class CreateCategoryRequest extends VzaarRequest {

    private CreateCategoryRequest() {
        super("api/v2/categories", Method.POST);
    }

    public static class Builder {
        private final CreateCategoryRequest obj;

        public Builder(String name) {
            obj = new CreateCategoryRequest();
            obj.addBodyParam("name", name);
        }

        public Builder setParentId(int parentId) {
            obj.addBodyParam("parent_id", parentId);
            return this;
        }

        public CreateCategoryRequest build() {
            return obj;
        }
    }
}
