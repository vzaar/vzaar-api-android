package com.vzaar.apiclient.request;

import com.vzaar.apiclient.VzaarCallback;

/**
 * A request to create a playlist.
 * <p/>
 * {@inheritDoc}
 *
 * @see com.vzaar.apiclient.Vzaar#createPlaylist(CreatePlaylistRequest, VzaarCallback)
 */
public class CreatePlaylistRequest extends VzaarRequest {

    private CreatePlaylistRequest() {
        super("api/v2/playlists", Method.POST);
    }

    public static class Builder {
        private final CreatePlaylistRequest obj;

        public Builder(String title, int categoryId) {
            obj = new CreatePlaylistRequest();
            obj.addBodyParam("title", title);
            obj.addBodyParam("category_id", categoryId);
        }

        public Builder setSortBy(String value) {
            obj.addBodyParam("sort_by", value);
            return this;
        }

        public Builder setSortOrder(String value) {
            obj.addBodyParam("sort_order", value);
            return this;
        }

        public Builder setPrivate(boolean value) {
            obj.addBodyParam("private", value);
            return this;
        }

        public Builder setDimensions(int width, int height) {
            obj.addBodyParam("dimensions", width + "x" + height);
            return this;
        }

        public Builder setMaxVids(int value) {
            obj.addBodyParam("max_vids", value);
            return this;
        }

        public Builder setPosition(String value) {
            obj.addBodyParam("position", value);
            return this;
        }

        public Builder setAutoplay(boolean value) {
            obj.addBodyParam("autoplay", value);
            return this;
        }

        public Builder setContinuousPlay(boolean value) {
            obj.addBodyParam("continuous_play", value);
            return this;
        }

        public CreatePlaylistRequest build() {
            return obj;
        }
    }
}
