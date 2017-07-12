package com.vzaar.apiclient.request;

import com.vzaar.apiclient.VzaarCallback;

/**
 * A request to create a multi-part upload signature.
 * <p/>
 * {@inheritDoc}
 *
 * @see com.vzaar.apiclient.Vzaar#createMultipartUploadSignature(CreateMultipartUploadSignatureRequest, VzaarCallback)
 */
public class CreateMultipartUploadSignatureRequest extends VzaarRequest {

    private CreateMultipartUploadSignatureRequest() {
        super("api/v2/signature/multipart", Method.POST);
    }

    public static class Builder {
        private final CreateMultipartUploadSignatureRequest obj;

        public Builder(String uploader, String fileName, long fileSize) {
            obj = new CreateMultipartUploadSignatureRequest();
            obj.addBodyParam("uploader", uploader);
            obj.addBodyParam("filesize", fileSize);
            obj.addBodyParam("filename", fileName);
        }

        public CreateMultipartUploadSignatureRequest.Builder setDesiredPartSize(String value) {
            obj.addBodyParam("desired_part_size", value);
            return this;
        }

        public CreateMultipartUploadSignatureRequest build() {
            return obj;
        }
    }
}
