package com.vzaar.apiclient.request;

import com.vzaar.apiclient.VzaarCallback;
import com.vzaar.apiclient.model.IngestRecipe;

/**
 * A request to create a video using from a URL.
 * <p/>
 * {@inheritDoc}
 *
 * @see com.vzaar.apiclient.Vzaar#createLinkUpload(CreateLinkUploadRequest, VzaarCallback)
 */
public class CreateLinkUploadRequest extends VzaarRequest {

    private CreateLinkUploadRequest() {
        super("api/v2/link_uploads", Method.POST);
    }

    public static class Builder {
        private final CreateLinkUploadRequest obj;

        public Builder(String uploader, String url) {
            obj = new CreateLinkUploadRequest();
            obj.addBodyParam("uploader", uploader);
            obj.addBodyParam("url", url);
        }

        public Builder setIngestRecipe(IngestRecipe value) {
            obj.addBodyParam("ingest_recipe", value.getId());
            return this;
        }

        public Builder setTitle(String value) {
            obj.addBodyParam("title", value);
            return this;
        }

        public Builder setDescription(String value) {
            obj.addBodyParam("description", value);
            return this;
        }

        public CreateLinkUploadRequest build() {
            return obj;
        }
    }
}
