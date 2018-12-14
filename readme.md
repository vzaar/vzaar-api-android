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

### Subtitles
To create a subtitle request:
```
CreateSubtitleRequest request = new CreateSubtitleRequest.Builder(123,"en").setContent("1\n00:00:00,498 --> 00:00:02,827\nMy Subtitles").build();

Vzaar.getInstance().createSubtitle(request, new VzaarCallback<VzaarResponse<Subtitle>>() {
    @Override
    public void onSuccessResponse(VzaarResponse<Subtitle> response) {
        //do something with the response
        }

    @Override
    public void onErrorResponse(VzaarResponse<Subtitle> response) {
        //get the errors from the response
    }

    @Override
    public void onNoResponse(VzaarException e) {
        // :(
    }
});
```

To get subtitles
```
GetSubtitlesRequest subtitlesRequest = new GetSubtitlesRequest.Builder(123).build();

Vzaar.getInstance().getSubtitles(subtitlesRequest, new VzaarCallback<VzaarListResponse<Subtitle>>() {
    @Override
    public void onSuccessResponse(VzaarListResponse<Subtitle> response) {
        //do somthing with the response
    }

    @Override
    public void onErrorResponse(VzaarListResponse<Subtitle> response) {
        //get the errors from the response
    }

    @Override
        public void onNoResponse(VzaarException e) {
    // :(
    }
});
```

### Update Image Frame
To update image frame with time
```
UpdateImageFrameRequest imageFrameRequest = new UpdateImageFrameRequest.Builder(123).setTime(1.5).build();

Vzaar.getInstance().updateImageFrame(imageFrameRequest, new VzaarCallback<VzaarResponse<Video>>() {
    @Override
    public void onSuccessResponse(VzaarResponse<Video> response) {
        //do somthing with the response
    }

    @Override
    public void onErrorResponse(VzaarResponse<Video> response) {
        //get the errors from the response
    }

    @Override
    public void onNoResponse(VzaarException e) {
        // :(
    }
});
```

To upload image frame with file
```
File file = new File("/storage/path/to/your/image/file");
UploadImageFrameRequest uploadframeRequest = new UploadImageFrameRequest.Builder(123, file).build();
Vzaar.getInstance().uploadImageFrame(uploadframeRequest, new VzaarCallback<VzaarResponse<Video>>() {
    @Override
    public void onSuccessResponse(VzaarResponse<Video> response) {
        //do somthing with the response
    }

    @Override
    public void onErrorResponse(VzaarResponse<Video> response) {
        //get the errors from the response
    }

    @Override
    public void onNoResponse(VzaarException e) {
        // :(
    }
});
```


For more on video uploading, see the [Vzaar API documentation](https://vzaar.readme.io/docs/video-upload).

### Full documentation
Full documentation can be found at [TBC](/)

##Sample
The sample app demonstrates some of the library's features.

Before running it, make sure you replace the Vzaar API credentials with your own in `App.java`.

