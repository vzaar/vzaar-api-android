package com.vzaar.apiclient.request;

import com.vzaar.apiclient.VzaarCallback;

/**
 * A request to delete a playlist.
 * <p/>
 * {@inheritDoc}
 *
 * @see com.vzaar.apiclient.Vzaar#deletePlaylist(DeletePlaylistRequest, VzaarCallback)
 */
public class DeletePlaylistRequest extends VzaarRequest {

    private DeletePlaylistRequest() {
        super("api/v2/playlists/{id}", Method.DELETE);
    }

    public static class Builder {
        private final DeletePlaylistRequest obj;

        public Builder(int id) {
            obj = new DeletePlaylistRequest();
            obj.addPathParam("id", Integer.toString(id));
        }

        public DeletePlaylistRequest build() {
            return obj;
        }
    }
}
