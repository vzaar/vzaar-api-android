package com.vzaar.apiclient.request;

import java.io.File;

public class UploadImageFrameRequest extends VzaarRequest{

    private UploadImageFrameRequest() { super("api/v2/videos/{id}/image", Method.POST); }

    public static class Builder {
        private final UploadImageFrameRequest obj;

        public Builder(int id, File image) {
            obj = new UploadImageFrameRequest();
            obj.addPathParam("id", Integer.toString(id));

            obj.addFileParam(image);

        }

        public UploadImageFrameRequest build() {
            return obj;
        }
    }

}
