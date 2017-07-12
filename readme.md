# Vzaar API Client for Android
A simple client for the [Vzaar API](https://vzaar.readme.io/).

## Download
### Gradle
```
compile 'com.vzaar.apiclient:apiclient:0.1.0'
```

## Usage
First, initialize the client with your configuration.
```
VzaarConfig config =
    new VzaarConfig.Builder("your-client-id", "yourtoken")
        .build();
Vzaar vzaar = Vzaar.newInstance(config);
```

Once the config has been set, you can get the singleton with `Vzaar.getInstance()`.

### Requests
To make a request, first build it:
```
GetVideosRequest request = new GetVideosRequest.Builder()
                .setPerPage(5)
                .setPage(page + 1)
                .build();
```

Then pass the request to the Vzaar object:
```
vzaar.getVideos(request, new VzaarCallback<VzaarListResponse<Video>>() {
    @Override
    public void onSuccessResponse(VzaarListResponse<Video> response) {
        // do something with the response
    }

    @Override
    public void onErrorResponse(VzaarListResponse<Video> response) {
        // get the errors from the response
    }

    @Override
    public void onNoResponse(VzaarException e) {
        // :(
    }
});
```

### Uploads
There is some basic upload functionality.

To create an upload request:
```
SourceVideo source = new AndroidSourceVideo(contentResolver, contentUri, filename);
VzaarUploadRequest request = new VzaarUploadRequest.Builder(source)
        .setVideoTitle(title)
        .setVideoDescription(description)
        .build();
```

To sign, upload and process the video on Vzaar, use:
```
Vzaar.signUploadAndCreateVideo(request, ...);
```
If you would like to create the signature separately, instead use:
```
Vzaar.uploadAndCreateVideo(request, signature, ...);
```

For more on video uploading, see the [Vzaar API documentation](https://vzaar.readme.io/docs/video-upload).

### Full documentation
Full documentation can be found at [TBC](/)

##Sample
The sample app demonstrates some of the library's features.

Before running it, make sure you replace the Vzaar API credentials with your own in `App.java`.

