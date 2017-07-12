package com.vzaar.apiclient.request;

import com.vzaar.apiclient.VzaarCallback;
import com.vzaar.apiclient.model.Category;

import java.util.List;

/**
 * A request to update a video.
 * <p/>
 * {@inheritDoc}
 *
 * @see com.vzaar.apiclient.Vzaar#updateVideo(UpdateVideoRequest, VzaarCallback)
 */
public class UpdateVideoRequest extends VzaarRequest {

    private UpdateVideoRequest() {
        super("api/v2/videos/{id}", Method.PATCH);
    }

    public static class Builder {
        private final UpdateVideoRequest obj;

        public Builder(int id) {
            obj = new UpdateVideoRequest();
            obj.addPathParam("id", Integer.toString(id));
        }

        public Builder setTitle(String value) {
            obj.addBodyParam("title", value);
            return this;
        }

        public Builder setDescription(String value) {
            obj.addBodyParam("description", value);
            return this;
        }

        public Builder setPrivate(boolean value) {
            obj.addBodyParam("private", value);
            return this;
        }

        public Builder setSeoUrl(String value) {
            obj.addBodyParam("seo_url", value);
            return this;
        }

        public Builder setCategories(List<Category> value) {
            obj.addBodyParam("category_ids", JsonUtil.createIdsArray(value));
            return this;
        }

        public UpdateVideoRequest build() {
            return obj;
        }
    }
}
