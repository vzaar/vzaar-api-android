package com.vzaar.apiclient.request;

import com.vzaar.apiclient.VzaarCallback;

import java.io.File;

/**
 * A request to update the image frame.
 * <p/>
 * {@inheritDoc}
 *
 * @see com.vzaar.apiclient.Vzaar#createSubtitle(CreateSubtitleRequest, VzaarCallback)
 */
public class CreateSubtitleRequest extends VzaarRequest {

    private CreateSubtitleRequest() {
        super("api/v2/videos/{id}/subtitles", Method.POST);
    }

    public static class Builder {
        private final CreateSubtitleRequest obj;

        public Builder(int id, String code) {
            obj = new CreateSubtitleRequest();
            obj.addPathParam("id", Integer.toString(id));

            obj.addBodyParam("code",code);
            obj.addFileParam("code", code);
        }

        public Builder setTitle(String title) {
            obj.addFileParam("title", title);
            return this;
        }

        public Builder setContent(String content) {
            obj.addFileParam("content", content);
            return this;
        }

        public Builder setFile(File file) {
            obj.addFileParam(file);
            return this;
        }

        public CreateSubtitleRequest build() {
            return obj;
        }
    }

}
