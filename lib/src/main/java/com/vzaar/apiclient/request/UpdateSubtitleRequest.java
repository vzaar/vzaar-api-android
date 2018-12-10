package com.vzaar.apiclient.request;

import com.vzaar.apiclient.VzaarCallback;

import java.io.File;

/**
 * A request to update the image frame.
 * <p/>
 * {@inheritDoc}
 *
 * @see com.vzaar.apiclient.Vzaar#updateSubtitle(UpdateSubtitleRequest, VzaarCallback)
 */
public class UpdateSubtitleRequest extends VzaarRequest {

    private UpdateSubtitleRequest() {
        super("api/v2/videos/{id}/subtitles/{subtitleId}", Method.PATCH);
    }

    public static class Builder {
        private final UpdateSubtitleRequest obj;

        public Builder(int id, int subtitleId, String code) {
            obj = new UpdateSubtitleRequest();
            obj.addPathParam("id", Integer.toString(id));
            obj.addPathParam("subtitleId", Integer.toString(subtitleId));
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

        public UpdateSubtitleRequest build() {
            return obj;
        }
    }

}
