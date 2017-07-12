package com.vzaar.apiclient;

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

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.lang.reflect.Type;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SuppressWarnings("unchecked")
public class VzaarTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Vzaar vzaar;

    @Before
    public void setup() {
        ApiRequester mockApiRequester = mock(ApiRequester.class);
        doAnswer(
                new Answer<Object>() {
                    @Override
                    public Object answer(InvocationOnMock invocation) throws Throwable {
                        ((VzaarCallback) invocation.getArgument(2))
                                .onSuccessResponse(mock(VzaarResponse.class));
                        return null;
                    }
                }).when(mockApiRequester)
                .sendRequest(any(VzaarRequest.class), any(Type.class), any(VzaarCallback.class));
        vzaar = new Vzaar(mock(VzaarConfig.class), mockApiRequester, mock(UploadRequester.class));
    }

    @Test
    public void testGetVideo() {
        // Given
        GetVideoRequest request = new GetVideoRequest.Builder(0).build();
        VzaarCallback<VzaarResponse<Video>> callback = mock(VzaarCallback.class);

        // When
        vzaar.getVideo(request, callback);

        // Then
        verifySuccessResponse(callback);
    }

    @Test
    public void testGetVideo_nullRequest_throwsException() {
        // Given
        VzaarCallback<VzaarResponse<Video>> callback = mock(VzaarCallback.class);

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.getVideo(null, callback);
    }

    @Test
    public void testGetVideo_nullCallback_throwsException() {
        // Given
        GetVideoRequest request = new GetVideoRequest.Builder(0).build();

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.getVideo(request, null);
    }

    @Test
    public void testGetVideos() {
        // Given
        GetVideosRequest request = new GetVideosRequest.Builder().build();
        VzaarCallback<VzaarListResponse<Video>> callback = mock(VzaarCallback.class);

        // When
        vzaar.getVideos(request, callback);

        // Then
        verifySuccessResponse(callback);
    }

    @Test
    public void testGetVideos_nullRequest_throwsException() {
        // Given
        VzaarCallback<VzaarListResponse<Video>> callback = mock(VzaarCallback.class);

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.getVideos(null, callback);
    }

    @Test
    public void testGetVideos_nullCallback_throwsException() {
        // Given
        GetVideosRequest request = new GetVideosRequest.Builder().build();

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.getVideos(request, null);
    }


    @Test
    public void testCreateVideo() {
        // Given
        CreateVideoRequest request = new CreateVideoRequest.Builder("fakeguid").build();
        VzaarCallback<VzaarResponse<Video>> callback = mock(VzaarCallback.class);

        // When
        vzaar.createVideo(request, callback);

        // Then
        verifySuccessResponse(callback);
    }

    @Test
    public void testCreateVideo_nullRequest_throwsException() {
        // Given
        VzaarCallback<VzaarResponse<Video>> callback = mock(VzaarCallback.class);

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.createVideo(null, callback);
    }

    @Test
    public void testCreateVideo_nullCallback_throwsException() {
        // Given
        CreateVideoRequest request = new CreateVideoRequest.Builder("fakeguid").build();

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.createVideo(request, null);
    }

    @Test
    public void testUpdateVideo() {
        // Given
        UpdateVideoRequest request = mock(UpdateVideoRequest.class);
        VzaarCallback<VzaarResponse<Video>> callback = mock(VzaarCallback.class);

        // When
        vzaar.updateVideo(request, callback);

        // Then
        verifySuccessResponse(callback);
    }

    @Test
    public void testUpdateVideo_nullRequest_throwsException() {
        // Given
        VzaarCallback<VzaarResponse<Video>> callback = mock(VzaarCallback.class);

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.updateVideo(null, callback);
    }

    @Test
    public void testUpdateVideo_nullCallback_throwsException() {
        // Given
        UpdateVideoRequest request = mock(UpdateVideoRequest.class);

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.updateVideo(request, null);
    }

    @Test
    public void testDeleteVideo() {
        // Given
        DeleteVideoRequest request = mock(DeleteVideoRequest.class);
        VzaarCallback<VzaarResponse<Void>> callback = mock(VzaarCallback.class);

        // When
        vzaar.deleteVideo(request, callback);

        // Then
        verifySuccessResponse(callback);
    }

    @Test
    public void testDeleteVideo_nullRequest_throwsException() {
        // Given
        VzaarCallback<VzaarResponse<Void>> callback = mock(VzaarCallback.class);

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.deleteVideo(null, callback);
    }

    @Test
    public void testDeleteVideo_nullCallback_throwsException() {
        // Given
        DeleteVideoRequest request = mock(DeleteVideoRequest.class);

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.deleteVideo(request, null);
    }

    @Test
    public void testGetCategory() {
        // Given
        GetCategoryRequest request = new GetCategoryRequest.Builder(0).build();
        VzaarCallback<VzaarResponse<Category>> callback = mock(VzaarCallback.class);

        // When
        vzaar.getCategory(request, callback);

        // Then
        verifySuccessResponse(callback);
    }

    @Test
    public void testGetCategory_nullRequest_throwsException() {
        // Given
        VzaarCallback<VzaarResponse<Category>> callback = mock(VzaarCallback.class);

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.getCategory(null, callback);
    }

    @Test
    public void testGetCategory_nullCallback_throwsException() {
        // Given
        GetCategoryRequest request = new GetCategoryRequest.Builder(0).build();

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.getCategory(request, null);
    }

    @Test
    public void testGetCategories() {
        // Given
        GetCategoriesRequest request = new GetCategoriesRequest.Builder().build();
        VzaarCallback<VzaarListResponse<Category>> callback = mock(VzaarCallback.class);

        // When
        vzaar.getCategories(request, callback);

        // Then
        verifySuccessResponse(callback);
    }

    @Test
    public void testGetCategories_nullRequest_throwsException() {
        // Given
        VzaarCallback<VzaarListResponse<Category>> callback = mock(VzaarCallback.class);

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.getCategories(null, callback);
    }

    @Test
    public void testGetCategories_nullCallback_throwsException() {
        // Given
        GetCategoriesRequest request = new GetCategoriesRequest.Builder().build();

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.getCategories(request, null);
    }

    @Test
    public void testGetCategoriesSubtree() {
        // Given
        GetCategoriesSubtreeRequest request = new GetCategoriesSubtreeRequest.Builder(1).build();
        VzaarCallback<VzaarListResponse<Category>> callback = mock(VzaarCallback.class);

        // When
        vzaar.getCategoriesSubtree(request, callback);

        // Then
        verifySuccessResponse(callback);
    }

    @Test
    public void testGetCategoriesSubtree_nullRequest_throwsException() {
        // Given
        VzaarCallback<VzaarListResponse<Category>> callback = mock(VzaarCallback.class);

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.getCategoriesSubtree(null, callback);
    }

    @Test
    public void testGetCategoriesSubtree_nullCallback_throwsException() {
        // Given
        GetCategoriesSubtreeRequest request = new GetCategoriesSubtreeRequest.Builder(1).build();

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.getCategoriesSubtree(request, null);
    }

    @Test
    public void testCreateCategory() {
        // Given
        CreateCategoryRequest request = new CreateCategoryRequest.Builder("fakeguid").build();
        VzaarCallback<VzaarResponse<Category>> callback = mock(VzaarCallback.class);

        // When
        vzaar.createCategory(request, callback);

        // Then
        verifySuccessResponse(callback);
    }

    @Test
    public void testCreateCategory_nullRequest_throwsException() {
        // Given
        VzaarCallback<VzaarResponse<Category>> callback = mock(VzaarCallback.class);

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.createCategory(null, callback);
    }

    @Test
    public void testCreateCategory_nullCallback_throwsException() {
        // Given
        CreateCategoryRequest request = new CreateCategoryRequest.Builder("fakeguid").build();

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.createCategory(request, null);
    }

    @Test
    public void testUpdateCategory() {
        // Given
        UpdateCategoryRequest request = mock(UpdateCategoryRequest.class);
        VzaarCallback<VzaarResponse<Category>> callback = mock(VzaarCallback.class);

        // When
        vzaar.updateCategory(request, callback);

        // Then
        verifySuccessResponse(callback);
    }

    @Test
    public void testUpdateCategory_nullRequest_throwsException() {
        // Given
        VzaarCallback<VzaarResponse<Category>> callback = mock(VzaarCallback.class);

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.updateCategory(null, callback);
    }

    @Test
    public void testUpdateCategory_nullCallback_throwsException() {
        // Given
        UpdateCategoryRequest request = mock(UpdateCategoryRequest.class);

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.updateCategory(request, null);
    }

    @Test
    public void testDeleteCategory() {
        // Given
        DeleteCategoryRequest request = mock(DeleteCategoryRequest.class);
        VzaarCallback<VzaarResponse<Void>> callback = mock(VzaarCallback.class);

        // When
        vzaar.deleteCategory(request, callback);

        // Then
        verifySuccessResponse(callback);
    }

    @Test
    public void testDeleteCategory_nullRequest_throwsException() {
        // Given
        VzaarCallback<VzaarResponse<Void>> callback = mock(VzaarCallback.class);

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.deleteCategory(null, callback);
    }

    @Test
    public void testDeleteCategory_nullCallback_throwsException() {
        // Given
        DeleteCategoryRequest request = mock(DeleteCategoryRequest.class);

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.deleteCategory(request, null);
    }

    @Test
    public void testGetIngestRecipe() {
        // Given
        GetIngestRecipeRequest request = new GetIngestRecipeRequest.Builder(1).build();
        VzaarCallback<VzaarResponse<IngestRecipe>> callback = mock(VzaarCallback.class);

        // When
        vzaar.getIngestRecipe(request, callback);

        // Then
        verifySuccessResponse(callback);
    }

    @Test
    public void testGetIngestRecipe_nullRequest_throwsException() {
        // Given

        VzaarCallback<VzaarResponse<IngestRecipe>> callback = mock(VzaarCallback.class);

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.getIngestRecipe(null, callback);
    }

    @Test
    public void testGetIngestRecipe_nullCallback_throwsException() {
        // Given
        GetIngestRecipeRequest request = new GetIngestRecipeRequest.Builder(1).build();

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.getIngestRecipe(request, null);
    }


    @Test
    public void testGetIngestRecipes() {
        // Given
        GetIngestRecipesRequest request = new GetIngestRecipesRequest.Builder()
                .build();
        VzaarCallback<VzaarListResponse<IngestRecipe>> callback = mock(VzaarCallback.class);

        // When
        vzaar.getIngestRecipes(request, callback);

        // Then
        verifySuccessResponse(callback);
    }

    @Test
    public void testGetIngestRecipes_nullRequest_throwsException() {
        // Given
        VzaarCallback<VzaarListResponse<IngestRecipe>> callback = mock(VzaarCallback.class);

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.getIngestRecipes(null, callback);
    }

    @Test
    public void testGetIngestRecipes_nullCallback_throwsException() {
        // Given
        GetIngestRecipesRequest request = new GetIngestRecipesRequest.Builder().build();

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.getIngestRecipes(request, null);
    }

    @Test
    public void testCreateIngestRecipe() {
        // Given
        CreateIngestRecipeRequest request = mock(CreateIngestRecipeRequest.class);
        VzaarCallback<VzaarResponse<IngestRecipe>> callback = mock(VzaarCallback.class);

        // When
        vzaar.createIngestRecipe(request, callback);

        // Then
        verifySuccessResponse(callback);
    }

    @Test
    public void testCreateIngestRecipe_nullRequest_throwsException() {
        // Given
        VzaarCallback<VzaarResponse<IngestRecipe>> callback = mock(VzaarCallback.class);

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.createIngestRecipe(null, callback);
    }

    @Test
    public void testCreateIngestRecipe_nullCallback_throwsException() {
        // Given
        CreateIngestRecipeRequest request = mock(CreateIngestRecipeRequest.class);

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.createIngestRecipe(request, null);
    }


    @Test
    public void testUpdateIngestRecipe() {
        // Given
        UpdateIngestRecipeRequest request = mock(UpdateIngestRecipeRequest.class);
        VzaarCallback<VzaarResponse<IngestRecipe>> callback = mock(VzaarCallback.class);

        // When
        vzaar.updateIngestRecipe(request, callback);

        // Then
        verifySuccessResponse(callback);
    }

    @Test
    public void testUpdateIngestRecipe_nullRequest_throwsException() {
        // Given
        VzaarCallback<VzaarResponse<IngestRecipe>> callback = mock(VzaarCallback.class);

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.updateIngestRecipe(null, callback);
    }

    @Test
    public void testUpdateIngestRecipe_nullCallback_throwsException() {
        // Given
        UpdateIngestRecipeRequest request = mock(UpdateIngestRecipeRequest.class);

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.updateIngestRecipe(request, null);
    }

    @Test
    public void testDeleteIngestRecipe() {
        // Given
        DeleteIngestRecipeRequest request = mock(DeleteIngestRecipeRequest.class);
        VzaarCallback<VzaarResponse<Void>> callback = mock(VzaarCallback.class);

        // When
        vzaar.deleteIngestRecipe(request, callback);

        // Then
        verifySuccessResponse(callback);
    }

    @Test
    public void testDeleteIngestRecipe_nullRequest_throwsException() {
        // Given
        VzaarCallback<VzaarResponse<Void>> callback = mock(VzaarCallback.class);

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.deleteIngestRecipe(null, callback);
    }

    @Test
    public void testDeleteIngestRecipe_nullCallback_throwsException() {
        // Given
        DeleteIngestRecipeRequest request = mock(DeleteIngestRecipeRequest.class);

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.deleteIngestRecipe(request, null);
    }

    @Test
    public void testGetEncodingPreset() {
        // Given
        GetEncodingPresetRequest request = new GetEncodingPresetRequest.Builder(1).build();
        VzaarCallback<VzaarResponse<EncodingPreset>> callback = mock(VzaarCallback.class);

        // When
        vzaar.getEncodingPreset(request, callback);

        // Then
        verifySuccessResponse(callback);
    }

    @Test
    public void testGetEncodingPreset_nullRequest_throwsException() {
        // Given
        VzaarCallback<VzaarResponse<EncodingPreset>> callback = mock(VzaarCallback.class);

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.getEncodingPreset(null, callback);
    }

    @Test
    public void testGetEncodingPreset_nullCallback_throwsException() {
        // Given
        GetEncodingPresetRequest request = new GetEncodingPresetRequest.Builder(1).build();

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.getEncodingPreset(request, null);
    }


    @Test
    public void testGetEncodingPresets() {
        // Given
        GetEncodingPresetsRequest request = new GetEncodingPresetsRequest.Builder().build();
        VzaarCallback<VzaarListResponse<EncodingPreset>> callback = mock(VzaarCallback.class);

        // When
        vzaar.getEncodingPresets(request, callback);

        // Then
        verifySuccessResponse(callback);
    }

    @Test
    public void testGetEncodingPresets_nullRequest_throwsException() {
        // Given
        VzaarCallback<VzaarListResponse<EncodingPreset>> callback = mock(VzaarCallback.class);

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.getEncodingPresets(null, callback);
    }

    @Test
    public void testGetEncodingPresets_nullCallback_throwsException() {
        // Given
        GetEncodingPresetsRequest request = new GetEncodingPresetsRequest.Builder().build();

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.getEncodingPresets(request, null);
    }

    @Test
    public void testCreateSingleUploadSignature() {
        // Given
        CreateSingleUploadSignatureRequest request = mock(CreateSingleUploadSignatureRequest.class);
        VzaarCallback<VzaarResponse<UploadSignature>> callback = mock(VzaarCallback.class);

        // When
        vzaar.createSingleUploadSignature(request, callback);

        // Then
        verifySuccessResponse(callback);
    }

    @Test
    public void testCreateSingleUploadSignature_nullRequest_throwsException() {
        // Given
        VzaarCallback<VzaarResponse<UploadSignature>> callback = mock(VzaarCallback.class);

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.createSingleUploadSignature(null, callback);
    }

    @Test
    public void testCreateSingleUploadSignature_nullCallback_throwsException() {
        // Given
        CreateSingleUploadSignatureRequest request = mock(CreateSingleUploadSignatureRequest.class);

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.createSingleUploadSignature(request, null);
    }

    @Test
    public void testCreateMultipartUploadSignature() {
        // Given
        CreateMultipartUploadSignatureRequest request = mock(
                CreateMultipartUploadSignatureRequest.class);
        VzaarCallback<VzaarResponse<UploadSignature>> callback = mock(VzaarCallback.class);

        // When
        vzaar.createMultipartUploadSignature(request, callback);

        // Then
        verifySuccessResponse(callback);
    }

    @Test
    public void testCreateMultipartUploadSignature_nullRequest_throwsException() {
        // Given
        VzaarCallback<VzaarResponse<UploadSignature>> callback = mock(VzaarCallback.class);

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.createMultipartUploadSignature(null, callback);
    }

    @Test
    public void testCreateMultipartUploadSignature_nullCallback_throwsException() {
        // Given
        CreateMultipartUploadSignatureRequest request = mock(
                CreateMultipartUploadSignatureRequest.class);

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.createMultipartUploadSignature(request, null);
    }

    @Test
    public void testCreateLinkUpload() {
        // Given
        CreateLinkUploadRequest request = mock(CreateLinkUploadRequest.class);
        VzaarCallback<VzaarResponse<Video>> callback = mock(VzaarCallback.class);

        // When
        vzaar.createLinkUpload(request, callback);

        // Then
        verifySuccessResponse(callback);
    }

    @Test
    public void testCreateLinkUpload_nullRequest_throwsException() {
        // Given
        VzaarCallback<VzaarResponse<Video>> callback = mock(VzaarCallback.class);

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.createLinkUpload(null, callback);
    }

    @Test
    public void testCreateLinkUpload_nullCallback_throwsException() {
        // Given
        CreateLinkUploadRequest request = mock(CreateLinkUploadRequest.class);

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.createLinkUpload(request, null);
    }

    @Test
    public void testGetPlaylist() {
        // Given
        GetPlaylistRequest request = new GetPlaylistRequest.Builder(0).build();
        VzaarCallback<VzaarResponse<Playlist>> callback = mock(VzaarCallback.class);

        // When
        vzaar.getPlaylist(request, callback);

        // Then
        verifySuccessResponse(callback);
    }

    @Test
    public void testGetPlaylist_nullRequest_throwsException() {
        // Given
        VzaarCallback<VzaarResponse<Playlist>> callback = mock(VzaarCallback.class);

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.getPlaylist(null, callback);
    }

    @Test
    public void testGetPlaylist_nullCallback_throwsException() {
        // Given
        GetPlaylistRequest request = new GetPlaylistRequest.Builder(0).build();

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.getPlaylist(request, null);
    }

    @Test
    public void testGetPlaylists() {
        // Given
        GetPlaylistsRequest request = new GetPlaylistsRequest.Builder().build();
        VzaarCallback<VzaarListResponse<Playlist>> callback = mock(VzaarCallback.class);

        // When
        vzaar.getPlaylists(request, callback);

        // Then
        verifySuccessResponse(callback);
    }

    @Test
    public void testGetPlaylists_nullRequest_throwsException() {
        // Given
        VzaarCallback<VzaarListResponse<Playlist>> callback = mock(VzaarCallback.class);

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.getPlaylists(null, callback);
    }

    @Test
    public void testGetPlaylists_nullCallback_throwsException() {
        // Given
        GetPlaylistsRequest request = new GetPlaylistsRequest.Builder().build();

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.getPlaylists(request, null);
    }


    @Test
    public void testCreatePlaylist() {
        // Given
        CreatePlaylistRequest request = new CreatePlaylistRequest.Builder("test_var1", 2).build();
        VzaarCallback<VzaarResponse<Playlist>> callback = mock(VzaarCallback.class);

        // When
        vzaar.createPlaylist(request, callback);

        // Then
        verifySuccessResponse(callback);
    }

    @Test
    public void testCreatePlaylist_nullRequest_throwsException() {
        // Given
        VzaarCallback<VzaarResponse<Playlist>> callback = mock(VzaarCallback.class);

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.createPlaylist(null, callback);
    }

    @Test
    public void testCreatePlaylist_nullCallback_throwsException() {
        // Given
        CreatePlaylistRequest request = new CreatePlaylistRequest.Builder("test_var1", 1).build();

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.createPlaylist(request, null);
    }

    @Test
    public void testUpdatePlaylist() {
        // Given
        UpdatePlaylistRequest request = mock(UpdatePlaylistRequest.class);
        VzaarCallback<VzaarResponse<Playlist>> callback = mock(VzaarCallback.class);

        // When
        vzaar.updatePlaylist(request, callback);

        // Then
        verifySuccessResponse(callback);
    }

    @Test
    public void testUpdatePlaylist_nullRequest_throwsException() {
        // Given
        VzaarCallback<VzaarResponse<Playlist>> callback = mock(VzaarCallback.class);

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.updatePlaylist(null, callback);
    }

    @Test
    public void testUpdatePlaylist_nullCallback_throwsException() {
        // Given
        UpdatePlaylistRequest request = mock(UpdatePlaylistRequest.class);

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.updatePlaylist(request, null);
    }

    @Test
    public void testDeletePlaylist() {
        // Given
        DeletePlaylistRequest request = mock(DeletePlaylistRequest.class);
        VzaarCallback<VzaarResponse<Void>> callback = mock(VzaarCallback.class);

        // When
        vzaar.deletePlaylist(request, callback);

        // Then
        verifySuccessResponse(callback);
    }

    @Test
    public void testDeletePlaylist_nullRequest_throwsException() {
        // Given
        VzaarCallback<VzaarResponse<Void>> callback = mock(VzaarCallback.class);

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.deletePlaylist(null, callback);
    }

    @Test
    public void testDeletePlaylist_nullCallback_throwsException() {
        // Given
        DeletePlaylistRequest request = mock(DeletePlaylistRequest.class);

        // Expect
        thrown.expect(IllegalArgumentException.class);

        // When
        vzaar.deletePlaylist(request, null);
    }

    private void verifySuccessResponse(VzaarCallback callback) {
        verify(callback, times(1)).onSuccessResponse(any(VzaarResponse.class));
    }

    @After
    public void teardown() {

    }

}
