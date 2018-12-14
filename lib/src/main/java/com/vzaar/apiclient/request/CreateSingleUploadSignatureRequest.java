package com.vzaar.apiclient.request;

import com.vzaar.apiclient.VzaarCallback;

/**
 * A request to create a single-part upload signature.
 * <p/>
 * {@inheritDoc}
 *
 * @see com.vzaar.apiclient.Vzaar#createSingleUploadSignature(CreateSingleUploadSignatureRequest, VzaarCallback)
 */
public class CreateSingleUploadSignatureRequest extends VzaarRequest {

    private CreateSingleUploadSignatureRequest() {
        super("api/v2/signature/single/2", Method.POST);
    }

    public static class Builder {
        private final CreateSingleUploadSignatureRequest obj;

        public Builder(String uploader) {
            obj = new CreateSingleUploadSignatureRequest();
            obj.addBodyParam("uploader", uploader);
        }

        public CreateSingleUploadSignatureRequest.Builder setFileSize(String value) {
            obj.addBodyParam("filesize", value);
            return this;
        }

        public CreateSingleUploadSignatureRequest.Builder setFileName(String value) {
            obj.addBodyParam("filename", value);
            return this;
        }


        public CreateSingleUploadSignatureRequest.Builder setDesiredPartSize(String value) {
            obj.addBodyParam("desired_part_size", value);
            return this;
        }

        public CreateSingleUploadSignatureRequest build() {
            return obj;
        }
    }
}
