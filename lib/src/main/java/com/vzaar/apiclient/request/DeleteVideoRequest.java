package com.vzaar.apiclient.request;

import com.vzaar.apiclient.VzaarCallback;

/**
 * A request to delete a video.
 * <p/>
 * {@inheritDoc}
 *
 * @see com.vzaar.apiclient.Vzaar#deleteVideo(DeleteVideoRequest, VzaarCallback)
 */
public class DeleteVideoRequest extends VzaarRequest {

    private DeleteVideoRequest() {
        super("api/v2/videos/{id}", Method.DELETE);
    }

    public static class Builder {
        private final DeleteVideoRequest obj;

        public Builder(int id) {
            obj = new DeleteVideoRequest();
            obj.addPathParam("id", Integer.toString(id));
        }

        public DeleteVideoRequest build() {
            return obj;
        }
    }
}
