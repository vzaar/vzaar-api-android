package com.vzaar.apiclient.request;

import com.vzaar.apiclient.VzaarCallback;

/**
 * A request to look up a playlist.
 * <p/>
 * {@inheritDoc}
 *
 * @see com.vzaar.apiclient.Vzaar#getPlaylist(GetPlaylistRequest, VzaarCallback)
 */
public class GetPlaylistRequest extends VzaarRequest {

    private GetPlaylistRequest() {
        super("api/v2/playlists/{id}", Method.GET);
    }

    public static class Builder {

        private final GetPlaylistRequest obj;

        public Builder(int id) {
            obj = new GetPlaylistRequest();
            obj.addPathParam("id", Integer.toString(id));
        }

        public GetPlaylistRequest build() {
            return obj;
        }

    }

}
