package com.vzaar.apiclient.request;

import com.vzaar.apiclient.VzaarCallback;

/**
 * A request to update the image frame.
 * <p/>
 * {@inheritDoc}
 *
 * @see com.vzaar.apiclient.Vzaar#deleteSubtitle(DeleteSubtitleRequest, VzaarCallback)
 */
public class DeleteSubtitleRequest extends VzaarRequest {

    private DeleteSubtitleRequest() {
        super("api/v2/videos/{id}/subtitles/{subtitleId}", Method.DELETE);
    }

    public static class Builder {
        private final DeleteSubtitleRequest obj;

        public Builder(int id, int subtitleId) {
            obj = new DeleteSubtitleRequest();
            obj.addPathParam("id", Integer.toString(id));
            obj.addPathParam("subtitleId", Integer.toString(subtitleId));
        }

        public DeleteSubtitleRequest build() {
            return obj;
        }
    }

}
