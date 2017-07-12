package com.vzaar.apiclient;

import com.google.gson.reflect.TypeToken;
import com.vzaar.apiclient.model.Category;
import com.vzaar.apiclient.model.EncodingPreset;
import com.vzaar.apiclient.model.IngestRecipe;
import com.vzaar.apiclient.model.Playlist;
import com.vzaar.apiclient.model.UploadSignature;
import com.vzaar.apiclient.model.Video;
import com.vzaar.apiclient.request.CreateCategoryRequest;
import com.vzaar.apiclient.request.CreateIngestRecipeRequest;
import com.vzaar.apiclient.request.CreateLinkUploadRequest;
import com.vzaar.apiclient.request.CreateMultipartUploadSignatureRequest;
import com.vzaar.apiclient.request.CreatePlaylistRequest;
import com.vzaar.apiclient.request.CreateSingleUploadSignatureRequest;
import com.vzaar.apiclient.request.CreateVideoRequest;
import com.vzaar.apiclient.request.DeleteCategoryRequest;
import com.vzaar.apiclient.request.DeleteIngestRecipeRequest;
import com.vzaar.apiclient.request.DeletePlaylistRequest;
import com.vzaar.apiclient.request.DeleteVideoRequest;
import com.vzaar.apiclient.request.GetCategoriesRequest;
import com.vzaar.apiclient.request.GetCategoriesSubtreeRequest;
import com.vzaar.apiclient.request.GetCategoryRequest;
import com.vzaar.apiclient.request.GetEncodingPresetRequest;
import com.vzaar.apiclient.request.GetEncodingPresetsRequest;
import com.vzaar.apiclient.request.GetIngestRecipeRequest;
import com.vzaar.apiclient.request.GetIngestRecipesRequest;
import com.vzaar.apiclient.request.GetPlaylistRequest;
import com.vzaar.apiclient.request.GetPlaylistsRequest;
import com.vzaar.apiclient.request.GetVideoRequest;
import com.vzaar.apiclient.request.GetVideosRequest;
import com.vzaar.apiclient.request.UpdateCategoryRequest;
import com.vzaar.apiclient.request.UpdateIngestRecipeRequest;
import com.vzaar.apiclient.request.UpdatePlaylistRequest;
import com.vzaar.apiclient.request.UpdateVideoRequest;
import com.vzaar.apiclient.request.VzaarRequest;
import com.vzaar.apiclient.response.VzaarListResponse;
import com.vzaar.apiclient.response.VzaarResponse;

import java.lang.reflect.Type;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Provides access to the Vzaar API and higher level upload helpers.
 * <h2>Uploading</h2>
 * There are three steps to uploading videos to Vzaar.
 * <ol>
 * <li>Create a signature via the Vzaar API</li>
 * <li>Upload the source video into the storage</li>
 * <li>Create the video via the Vzaar API</li>
 * </ol>
 */
@SuppressWarnings({"WeakerAccess", "UnusedReturnValue"})
public class Vzaar {
    private static Vzaar instance;
    private final ApiRequester apiRequester;
    private final UploadRequester uploadRequester;
    private final VzaarConfig config;

    Vzaar(VzaarConfig config, ApiRequester apiRequester, UploadRequester uploadRequester) {
        this.config = config;
        this.apiRequester = apiRequester;
        this.uploadRequester = uploadRequester;
    }

    /**
     * Gets the singleton instance of the {@link Vzaar} class. An instance will not be available until
     * {@link Vzaar#newInstance(VzaarConfig)} is called.
     *
     * @return The existing singleton instance.
     */
    public static Vzaar getInstance() {
        if (instance == null) {
            throw new VzaarException("Must first call newInstance to set the configuration.");
        }

        return instance;
    }

    /**
     * Creates a new singleton instance for the {@link Vzaar} class. Once created, the instance can be
     * accessed using {@link Vzaar#getInstance()}.
     * <p>
     * References to previous instances will not be held.
     *
     * @return The new singleton instance.
     */
    public static Vzaar newInstance(VzaarConfig config) {
        if (config == null) {
            throw new VzaarException("Must pass a non-null config");
        }

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Platform platform = AndroidPlatform.get();

        ApiRequester apiRequester = new OkHttpApiRequester(config.getApiBaseUrl(),
                platform, okHttpClient);
        apiRequester.setAuthentication(config.getAuthClientId(), config.getAuthToken());

        UploadRequester uploadRequester = new S3UploadRequester(platform, okHttpClient);

        instance = new Vzaar(config, apiRequester, uploadRequester);
        return instance;
    }

    /**
     * Lists {@link com.vzaar.apiclient.model.Video} via the Vzaar API.
     */
    public void getVideos(final GetVideosRequest request, final VzaarCallback<VzaarListResponse<Video>> callback) {
        sendRequest(request, new TypeToken<VzaarListResponse<Video>>() {
        }.getType(), callback);
    }

    /**
     * Looks up a {@link com.vzaar.apiclient.model.Video} via the Vzaar API.
     */
    public void getVideo(final GetVideoRequest request, final VzaarCallback<VzaarResponse<Video>> callback) {
        sendRequest(request, new TypeToken<VzaarResponse<Video>>() {
        }.getType(), callback);
    }

    /**
     * Creates a {@link com.vzaar.apiclient.model.Video} via the Vzaar API.
     * This is the last of three steps to uploading videos to Vzaar.
     * If you wish to use the uploader provided in this library, you should instead use one of the
     * following methods.
     * <ul>
     * <li>{@link Vzaar#signUploadAndCreateVideo(VzaarUploadRequest, VzaarUploadCallback)}</li>
     * <li>{@link Vzaar#uploadAndCreateVideo(VzaarUploadRequest, UploadSignature, VzaarUploadCallback)}</li>
     * </ul>
     */
    public void createVideo(final CreateVideoRequest request, final VzaarCallback<VzaarResponse<Video>> callback) {
        sendRequest(request, new TypeToken<VzaarResponse<Video>>() {
        }.getType(), callback);
    }

    /**
     * Updates a {@link com.vzaar.apiclient.model.Video} via the Vzaar API.
     */
    public void updateVideo(final UpdateVideoRequest request, final VzaarCallback<VzaarResponse<Video>> callback) {
        sendRequest(request, new TypeToken<VzaarResponse<Video>>() {
        }.getType(), callback);
    }

    /**
     * Deletes a {@link com.vzaar.apiclient.model.Video} via the Vzaar API.
     */
    public void deleteVideo(final DeleteVideoRequest request, final VzaarCallback<VzaarResponse<Void>> callback) {
        sendRequest(request, new TypeToken<VzaarResponse<Void>>() {
        }.getType(), callback);
    }

    /**
     * Lists {@link com.vzaar.apiclient.model.Category} via the Vzaar API.
     */
    public void getCategories(final GetCategoriesRequest request, final VzaarCallback<VzaarListResponse<Category>> callback) {
        sendRequest(request, new TypeToken<VzaarListResponse<Category>>() {
        }.getType(), callback);
    }

    /**
     * Gets a subtree of {@link com.vzaar.apiclient.model.Category} via the Vzaar API.
     * Vzaar categories are organized in a tree structure. The result of this request is a list
     * containing a given parent category and its direct children.
     */
    public void getCategoriesSubtree(final GetCategoriesSubtreeRequest request, final VzaarCallback<VzaarListResponse<Category>> callback) {
        sendRequest(request, new TypeToken<VzaarListResponse<Category>>() {
        }.getType(), callback);
    }

    /**
     * Looks up a {@link com.vzaar.apiclient.model.Category} via the Vzaar API.
     */
    public void getCategory(final GetCategoryRequest request, final VzaarCallback<VzaarResponse<Category>> callback) {
        sendRequest(request, new TypeToken<VzaarResponse<Category>>() {
        }.getType(), callback);
    }

    /**
     * Create a {@link com.vzaar.apiclient.model.Category} via the Vzaar API.
     */
    public void createCategory(final CreateCategoryRequest request, final VzaarCallback<VzaarResponse<Category>> callback) {
        sendRequest(request, new TypeToken<VzaarResponse<Category>>() {
        }.getType(), callback);
    }

    /**
     * Update a {@link com.vzaar.apiclient.model.Category} via the Vzaar API.
     */
    public void updateCategory(final UpdateCategoryRequest request, final VzaarCallback<VzaarResponse<Category>> callback) {
        sendRequest(request, new TypeToken<VzaarResponse<Category>>() {
        }.getType(), callback);
    }

    /**
     * Delete a {@link com.vzaar.apiclient.model.Category} via the Vzaar API.
     */
    public void deleteCategory(final DeleteCategoryRequest request, final VzaarCallback<VzaarResponse<Void>> callback) {
        sendRequest(request, new TypeToken<VzaarResponse<Void>>() {
        }.getType(), callback);
    }

    /**
     * Looks up an {@link com.vzaar.apiclient.model.IngestRecipe} recipe via the Vzaar API.
     */
    public void getIngestRecipe(final GetIngestRecipeRequest request, final VzaarCallback<VzaarResponse<IngestRecipe>> callback) {
        sendRequest(request, new TypeToken<VzaarResponse<IngestRecipe>>() {
        }.getType(), callback);
    }

    /**
     * Lists {@link com.vzaar.apiclient.model.IngestRecipe} via the Vzaar API.
     */
    public void getIngestRecipes(final GetIngestRecipesRequest request, final VzaarCallback<VzaarListResponse<IngestRecipe>> callback) {
        sendRequest(request, new TypeToken<VzaarCallback<VzaarListResponse<IngestRecipe>>>() {
        }.getType(), callback);
    }

    /**
     * Creates an {@link com.vzaar.apiclient.model.IngestRecipe} via the Vzaar API.
     */
    public void createIngestRecipe(final CreateIngestRecipeRequest request, final VzaarCallback<VzaarResponse<IngestRecipe>> callback) {
        sendRequest(request, new TypeToken<VzaarResponse<IngestRecipe>>() {
        }.getType(), callback);
    }

    /**
     * Updates an {@link com.vzaar.apiclient.model.IngestRecipe} via the Vzaar API.
     */
    public void updateIngestRecipe(final UpdateIngestRecipeRequest request, final VzaarCallback<VzaarResponse<IngestRecipe>> callback) {
        sendRequest(request, new TypeToken<VzaarResponse<IngestRecipe>>() {
        }.getType(), callback);
    }

    /**
     * Deletes an {@link com.vzaar.apiclient.model.IngestRecipe} via the Vzaar API.
     */
    public void deleteIngestRecipe(final DeleteIngestRecipeRequest request, final VzaarCallback<VzaarResponse<Void>> callback) {
        sendRequest(request, new TypeToken<VzaarResponse<Void>>() {
        }.getType(), callback);
    }

    /**
     * Looks up an {@link com.vzaar.apiclient.model.EncodingPreset} via the Vzaar API.
     */
    public void getEncodingPreset(final GetEncodingPresetRequest request, final VzaarCallback<VzaarResponse<EncodingPreset>> callback) {
        sendRequest(request, new TypeToken<VzaarResponse<EncodingPreset>>() {
        }.getType(), callback);
    }

    /**
     * Lists {@link com.vzaar.apiclient.model.EncodingPreset} via the Vzaar API.
     */
    public void getEncodingPresets(final GetEncodingPresetsRequest request, final VzaarCallback<VzaarListResponse<EncodingPreset>> callback) {
        sendRequest(request, new TypeToken<VzaarCallback<VzaarListResponse<EncodingPreset>>>() {
        }.getType(), callback);
    }

    /**
     * Creates an upload signature via the Vzaar API. Use this method if you wish to perform a
     * multi-part video upload.
     * <p/>
     * This is the first of three steps to uploading videos to Vzaar.
     * <p/>
     * If instead you want to combine the signature creation and video upload into a single
     * operation you can use {@link Vzaar#signUploadAndCreateVideo(VzaarUploadRequest, VzaarUploadCallback)}
     *
     * @see Vzaar#createSingleUploadSignature(CreateSingleUploadSignatureRequest, VzaarCallback)
     * @see Vzaar#signUploadAndCreateVideo(VzaarUploadRequest, VzaarUploadCallback)
     */
    public void createMultipartUploadSignature(final CreateMultipartUploadSignatureRequest request, final VzaarCallback<VzaarResponse<UploadSignature>> callback) {
        sendRequest(request, new TypeToken<VzaarResponse<UploadSignature>>() {
        }.getType(), callback);
    }

    /**
     * Creates an upload signature via the Vzaar API. Use this method if you wish to perform a
     * single-part video upload.
     * <p/>
     * This is the first of three steps to uploading videos to Vzaar.
     * <p/>
     * If instead you want to combine the signature creation and video upload into a single
     * operation you can use {@link Vzaar#signUploadAndCreateVideo(VzaarUploadRequest, VzaarUploadCallback)}
     *
     * @see Vzaar#createMultipartUploadSignature(CreateMultipartUploadSignatureRequest, VzaarCallback)
     * @see Vzaar#signUploadAndCreateVideo(VzaarUploadRequest, VzaarUploadCallback)
     */
    public void createSingleUploadSignature(final CreateSingleUploadSignatureRequest request, final VzaarCallback<VzaarResponse<UploadSignature>> callback) {
        sendRequest(request, new TypeToken<VzaarResponse<UploadSignature>>() {
        }.getType(), callback);
    }

    /**
     * Creates a {@link com.vzaar.apiclient.model.Video} via the Vzaar API using a URL as the source.
     */
    public void createLinkUpload(final CreateLinkUploadRequest request, final VzaarCallback<VzaarResponse<Video>> callback) {
        sendRequest(request, new TypeToken<VzaarResponse<Video>>() {
        }.getType(), callback);
    }

    /**
     * Lists {@link com.vzaar.apiclient.model.Playlist} via the Vzaar API.
     */
    public void getPlaylists(final GetPlaylistsRequest request, final VzaarCallback<VzaarListResponse<Playlist>> callback) {
        sendRequest(request, new TypeToken<VzaarListResponse<Playlist>>() {
        }.getType(), callback);
    }

    /**
     * Looks up a {@link com.vzaar.apiclient.model.Playlist} via the Vzaar API.
     */
    public void getPlaylist(final GetPlaylistRequest request, final VzaarCallback<VzaarResponse<Playlist>> callback) {
        sendRequest(request, new TypeToken<VzaarResponse<Playlist>>() {
        }.getType(), callback);
    }

    /**
     * Creates a {@link com.vzaar.apiclient.model.Playlist} via the Vzaar API.
     */
    public void createPlaylist(final CreatePlaylistRequest request, final VzaarCallback<VzaarResponse<Playlist>> callback) {
        sendRequest(request, new TypeToken<VzaarResponse<Playlist>>() {
        }.getType(), callback);
    }

    /**
     * Updates a {@link com.vzaar.apiclient.model.Playlist} via the Vzaar API.
     */
    public void updatePlaylist(final UpdatePlaylistRequest request, final VzaarCallback<VzaarResponse<Playlist>> callback) {
        sendRequest(request, new TypeToken<VzaarResponse<Playlist>>() {
        }.getType(), callback);
    }

    /**
     * Deletes a {@link com.vzaar.apiclient.model.Playlist} via the Vzaar API.
     */
    public void deletePlaylist(final DeletePlaylistRequest request, final VzaarCallback<VzaarResponse<Void>> callback) {
        sendRequest(request, new TypeToken<VzaarResponse<Void>>() {
        }.getType(), callback);
    }

    private void sendRequest(VzaarRequest request, Type responseType, VzaarCallback callback) {
        validateRequest(request, callback);
        apiRequester.sendRequest(request, responseType, callback);
    }

    /**
     * Signs, uploads and creates a video via the Vzaar API and storage API. Performs all steps
     * required to upload videos to Vzaar. This method will automatically select whether to create
     * a single-part or multi-part signature based on the configuration.
     *
     * @return A reference to the ongoing request.
     * @see Vzaar#createVideo(CreateVideoRequest, VzaarCallback)
     * @see Vzaar#uploadAndCreateVideo(VzaarUploadRequest, UploadSignature, VzaarUploadCallback)
     */
    public VzaarCall signUploadAndCreateVideo(final VzaarUploadRequest request, final VzaarUploadCallback callback) {
        final SignatureThenUploadCallback signatureCallback = new SignatureThenUploadCallback(
                this, request, callback);

        long filesize = request.getSourceVideo().getFileSizeBytes();
        String filename = request.getSourceVideo().getFilename();
        if (filesize <= config.getMaxSinglePartFileBytes()) {
            createSingleUploadSignature(
                    new CreateSingleUploadSignatureRequest.Builder("java-android")
                            .setFileSize(Long.toString(filesize))
                            .setFileName(filename).build(),
                    signatureCallback);
        } else {
            createMultipartUploadSignature(
                    new CreateMultipartUploadSignatureRequest.Builder(
                            "java-android", filename, filesize)
                            .build(),
                    signatureCallback);
        }
        return new VzaarCall() {
            @Override
            public void cancel() {
                signatureCallback.cancelUpload();
            }
        };
    }

    /**
     * Uploads a source video to the storage API and creates the video via the Vzaar API.
     * The last two of three steps to uploading videos to Vzaar:
     * <p/>
     * If you do not wish to create the signature in a separate request, you can instead use the
     * following method:
     * <ul>
     * <li>{@link Vzaar#signUploadAndCreateVideo(VzaarUploadRequest, VzaarUploadCallback)}</li>
     * </ul>
     *
     * @return A reference to the ongoing request.
     * @see Vzaar#signUploadAndCreateVideo(VzaarUploadRequest, VzaarUploadCallback)
     */
    public VzaarCall uploadAndCreateVideo(final VzaarUploadRequest request,
                                          final UploadSignature signature,
                                          final VzaarUploadCallback callback) {
        return uploadRequester
                .uploadVideo(request, signature, new UploadRequester.Callback() {
                    @Override
                    public void onSuccessResponse() {
                        CreateVideoRequest createVideoRequest =
                                new CreateVideoRequest.Builder(signature.getGuid())
                                        .setTitle(request.getVideoTitle())
                                        .setDescription(request.getVideoDescription())
                                        .build();
                        createVideo(createVideoRequest,
                                new VzaarCallback<VzaarResponse<Video>>() {
                                    @Override
                                    public void onSuccessResponse(VzaarResponse<Video> response) {
                                        callback.onComplete(response);
                                    }

                                    @Override
                                    public void onErrorResponse(VzaarResponse<Video> response) {
                                        callback.onError(new VzaarException(
                                                "Upload completed but there was an error creating the video:\n" +
                                                        response.getErrorsAsString()));
                                    }

                                    @Override
                                    public void onNoResponse(VzaarException e) {
                                        callback.onError(e);
                                    }
                                });
                    }

                    @Override
                    public void onProgress(long successfulParts, long failedParts, long totalBytes) {
                        callback.onProgress(successfulParts, failedParts, totalBytes);
                    }

                    @Override
                    public void onErrorResponse(Exception e) {
                        callback.onError(new VzaarException("Failed to upload the file.", e));
                    }
                });
    }

    private void validateRequest(VzaarRequest request, VzaarCallback<?> callback) {
        if (request == null) {
            throw new IllegalArgumentException("VzaarRequest cannot be null");
        }

        if (callback == null) {
            throw new IllegalArgumentException("Callback cannot be null");
        }
    }

    private static class SignatureThenUploadCallback implements VzaarCallback<VzaarResponse<UploadSignature>> {
        private final Vzaar vzaar;
        private final VzaarUploadCallback callback;
        private final VzaarUploadRequest vzaarUploadRequest;
        private boolean cancelRequested;
        private VzaarCall call = null;

        SignatureThenUploadCallback(Vzaar vzaar, VzaarUploadRequest vzaarUploadRequest, final VzaarUploadCallback callback) {
            this.vzaar = vzaar;
            this.vzaarUploadRequest = vzaarUploadRequest;
            this.callback = callback;
        }

        @Override
        public void onSuccessResponse(final VzaarResponse<UploadSignature> response) {
            if (!cancelRequested) {
                call = vzaar.uploadAndCreateVideo(vzaarUploadRequest,
                        response.getData(), callback);
            }
        }

        @Override
        public void onErrorResponse(VzaarResponse<UploadSignature> response) {
            callback.onError(new VzaarException(
                    "The upload signature could not be created:\n" + response
                            .getErrorsAsString()));
        }

        @Override
        public void onNoResponse(VzaarException e) {
            callback.onError(e);
        }

        public void cancelUpload() {
            if (call != null) {
                call.cancel();
            } else {
                cancelRequested = true;
            }
        }
    }
}
