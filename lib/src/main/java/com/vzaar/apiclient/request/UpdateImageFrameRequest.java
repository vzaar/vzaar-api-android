package com.vzaar.apiclient.request;

import com.vzaar.apiclient.VzaarCallback;

/**
 * A request to update the image frame.
 * <p/>
 * {@inheritDoc}
 *
 * @see com.vzaar.apiclient.Vzaar#updateImageFrame(UpdateImageFrameRequest, VzaarCallback)
 */
public class UpdateImageFrameRequest extends VzaarRequest {

    private UpdateImageFrameRequest() {
        super("api/v2/videos/{id}/image", Method.PATCH);
    }

    public static class Builder {
        private final UpdateImageFrameRequest obj;

        public Builder(int id) {
            obj = new UpdateImageFrameRequest();
            obj.addPathParam("id", Integer.toString(id));
        }

        public Builder setTime(Float time) {
            obj.addBodyParam("time", time);
            return this;
        }

        public UpdateImageFrameRequest build() {
            return obj;
        }
    }

}
