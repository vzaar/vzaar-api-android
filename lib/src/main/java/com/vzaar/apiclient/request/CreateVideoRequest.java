package com.vzaar.apiclient.request;

import com.vzaar.apiclient.VzaarCallback;

/**
 * A request to create a video.
 * <p/>
 * {@inheritDoc}
 *
 * @see com.vzaar.apiclient.Vzaar#createVideo(CreateVideoRequest, VzaarCallback)
 */
public class CreateVideoRequest extends VzaarRequest {

    private CreateVideoRequest() {
        super("api/v2/videos", Method.POST);
    }

    public static class Builder {
        private final CreateVideoRequest obj;

        public Builder(String guid) {
            obj = new CreateVideoRequest();
            obj.addBodyParam("guid", guid);
        }

        public Builder setIngestRecipeId(int ingestRecipeId) {
            obj.addBodyParam("ingest_recipe_id", ingestRecipeId);
            return this;
        }

        public Builder setTitle(String title) {
            obj.addBodyParam("title", title);
            return this;
        }

        public Builder setDescription(String description) {
            obj.addBodyParam("description", description);
            return this;
        }

        public CreateVideoRequest build() {
            return obj;
        }
    }
}
