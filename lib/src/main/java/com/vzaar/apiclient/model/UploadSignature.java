package com.vzaar.apiclient.model;

@SuppressWarnings("unused")
public class UploadSignature {
    private String accessKeyId;
    private String key;
    private String acl;
    private String policy;
    private String signature;
    private String successActionStatus;
    private String contentType;
    private String guid;
    private String bucket;
    private String uploadHostname;
    private String partSize;
    private long partSizeInBytes;
    private int parts;

    public String getAcl() {
        return acl;
    }

    public void setAcl(String acl) {
        this.acl = acl;
    }

    public int getParts() {
        return parts;
    }

    public void setParts(int parts) {
        this.parts = parts;
    }

    public long getPartSizeInBytes() {
        return partSizeInBytes;
    }

    public void setPartSizeInBytes(long partSizeInBytes) {
        this.partSizeInBytes = partSizeInBytes;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPartSize() {
        return partSize;
    }

    public void setPartSize(String partSize) {
        this.partSize = partSize;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSuccessActionStatus() {
        return successActionStatus;
    }

    public void setSuccessActionStatus(String successActionStatus) {
        this.successActionStatus = successActionStatus;
    }

    public String getUploadHostname() {
        return uploadHostname;
    }

    public void setUploadHostname(String uploadHostname) {
        this.uploadHostname = uploadHostname;
    }

    public boolean isMultipart() {
        return parts > 0;
    }

    public String getKeyForUpload(String filename, int partIndex) {
        return getKey().replace("${filename}", filename)
                + (isMultipart() ? "." + partIndex : "");
    }
}
