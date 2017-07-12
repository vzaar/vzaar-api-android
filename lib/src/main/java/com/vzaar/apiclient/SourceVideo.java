package com.vzaar.apiclient;

import java.io.FileNotFoundException;
import java.io.InputStream;

public interface SourceVideo {
    InputStream openInputStream() throws FileNotFoundException;

    long getFileSizeBytes();

    String getFilename();

    String getMimeType();
}
