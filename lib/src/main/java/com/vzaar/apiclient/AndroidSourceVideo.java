package com.vzaar.apiclient;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.ParcelFileDescriptor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class AndroidSourceVideo implements SourceVideo {
    private final ContentResolver contentResolver;
    private final Uri uri;
    private final String filename;
    private final long filesize;
    private final String mimeType;

    public AndroidSourceVideo(ContentResolver contentResolver, Uri uri, String filename)
            throws IOException {
        this.contentResolver = contentResolver;
        this.uri = uri;
        this.filename = filename;
        ParcelFileDescriptor fileDescriptor = contentResolver.openFileDescriptor(uri, "r");
        if (fileDescriptor == null) {
            throw new IOException("File descriptor was null so file size could not be " +
                    "determined.");
        }
        filesize = fileDescriptor.getStatSize();
        mimeType = contentResolver.getType(uri);
    }

    @Override
    public InputStream openInputStream() throws FileNotFoundException {
        return contentResolver.openInputStream(uri);
    }

    @Override
    public long getFileSizeBytes() {
        return filesize;
    }

    @Override
    public String getFilename() {
        return filename;
    }

    @Override
    public String getMimeType() {
        return mimeType;
    }
}
