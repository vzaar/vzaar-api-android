package com.vzaar.apiclient.request;

import com.vzaar.apiclient.model.Category;
import com.vzaar.apiclient.model.EncodingPreset;
import com.vzaar.apiclient.model.IngestRecipe;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RequestBuilderTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private static EncodingPreset createMockEncodingPresetWithId(int id) {
        EncodingPreset encodingPreset = mock(EncodingPreset.class);
        when(encodingPreset.getId()).thenReturn(id);
        return encodingPreset;
    }

    private static Category createMockCategoryWithId(int id) {
        Category category = mock(Category.class);
        when(category.getId()).thenReturn(id);
        return category;
    }

    @Test
    public void testCreateVideoRequestBuilder_endpoint() {
        // Given
        CreateVideoRequest.Builder builder = new CreateVideoRequest.Builder("testguid");

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getEndpoint(), is("api/v2/videos"));
        assertThat(request.getMethod(), is(VzaarRequest.Method.POST));
    }

    @Test
    public void testCreateVideoRequestBuilder_build_minParams() {
        // Given
        CreateVideoRequest.Builder builder = new CreateVideoRequest.Builder("testguid");

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getBody().entrySet().size(), is(1));
        assertThat(request.getBody().get("guid").getAsString(), is("testguid"));
        assertThat(request.getQueryParams().size(), is(0));
        assertThat(request.getPathParams().size(), is(0));
    }

    @Test
    public void testCreateVideoRequestBuilder_build_allParams() {
        // Given
        CreateVideoRequest.Builder builder = new CreateVideoRequest.Builder("test_val1")
                .setDescription("test_val2")
                .setIngestRecipeId(3)
                .setTitle("test_val4");

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getBody().entrySet().size(), is(4));
        assertThat(request.getBody().get("guid").getAsString(), is("test_val1"));
        assertThat(request.getBody().get("description").getAsString(), is("test_val2"));
        assertThat(request.getBody().get("ingest_recipe_id").getAsString(), is("3"));
        assertThat(request.getBody().get("title").getAsString(), is("test_val4"));
        assertThat(request.getQueryParams().size(), is(0));
        assertThat(request.getPathParams().size(), is(0));
    }

    @Test
    public void testGetVideosRequestBuilder_endpoint() {
        // Given
        GetVideosRequest.Builder builder = new GetVideosRequest.Builder();

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getEndpoint(), is("api/v2/videos"));
        assertThat(request.getMethod(), is(VzaarRequest.Method.GET));
    }

    @Test
    public void testGetVideosRequestBuilder_build_minParams() {
        // Given
        GetVideosRequest.Builder builder = new GetVideosRequest.Builder();

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getBody().entrySet().size(), is(0));
        assertThat(request.getQueryParams().size(), is(0));
        assertThat(request.getPathParams().size(), is(0));
    }

    @Test
    public void testGetVideosRequestBuilder_build_allParams() {
        // Given
        GetVideosRequest.Builder builder = new GetVideosRequest.Builder()
                .setCategoryId(1)
                .setOrder("test_val2")
                .setPage(3)
                .setPerPage(4)
                .setQuery("test_val5")
                .setState("test_val6");

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getBody().entrySet().size(), is(0));
        assertThat(request.getQueryParams().size(), is(6));
        assertThat(request.getQueryParams().get("category_id"), is("1"));
        assertThat(request.getQueryParams().get("order"), is("test_val2"));
        assertThat(request.getQueryParams().get("page"), is("3"));
        assertThat(request.getQueryParams().get("per_page"), is("4"));
        assertThat(request.getQueryParams().get("q"), is("test_val5"));
        assertThat(request.getQueryParams().get("state"), is("test_val6"));
        assertThat(request.getPathParams().size(), is(0));
    }

    @Test
    public void testGetVideoRequestBuilder_endpoint() {
        // Given
        GetVideoRequest.Builder builder = new GetVideoRequest.Builder(1);

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getEndpoint(), startsWith("api/v2/videos/"));
        assertThat(request.getMethod(), is(VzaarRequest.Method.GET));
    }

    @Test
    public void testGetVideoRequestBuilder_build() {
        // Given
        GetVideoRequest.Builder builder = new GetVideoRequest.Builder(1);

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getBody().entrySet().size(), is(0));
        assertThat(request.getQueryParams().size(), is(0));
        assertThat(request.getPathParams().size(), is(1));
        assertThat(request.getPathParams().get("id"), is("1"));
    }

    @Test
    public void testDeleteVideoRequestBuilder_endpoint() {
        // Given
        DeleteVideoRequest.Builder builder = new DeleteVideoRequest.Builder(1);

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getEndpoint(), is("api/v2/videos/1"));
        assertThat(request.getMethod(), is(VzaarRequest.Method.DELETE));
    }

    @Test
    public void testDeleteVideoRequestBuilder_build_allParams() {
        // Given
        DeleteVideoRequest.Builder builder = new DeleteVideoRequest.Builder(1);

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getBody().entrySet().size(), is(0));
        assertThat(request.getQueryParams().size(), is(0));
        assertThat(request.getPathParams().size(), is(1));
    }

    @Test
    public void testUpdateVideoRequestBuilder_endpoint() {
        // Given
        UpdateVideoRequest.Builder builder = new UpdateVideoRequest.Builder(1);

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getEndpoint(), is("api/v2/videos/1"));
        assertThat(request.getMethod(), is(VzaarRequest.Method.PATCH));
    }

    @Test
    public void testUpdateVideoRequestBuilder_build_minParams() {
        // Given
        UpdateVideoRequest.Builder builder = new UpdateVideoRequest.Builder(1);

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getBody().entrySet().size(), is(0));
        assertThat(request.getQueryParams().size(), is(0));
        assertThat(request.getPathParams().size(), is(1));
    }

    @Test
    public void testUpdateVideoRequestBuilder_build_allParams() {
        // Given
        List<Category> categories = new ArrayList<Category>() {{
            add(createMockCategoryWithId(5));
            add(createMockCategoryWithId(6));
        }};

        UpdateVideoRequest.Builder builder = new UpdateVideoRequest.Builder(1)
                .setTitle("test_val2")
                .setDescription("test_val3")
                .setPrivate(true)
                .setSeoUrl("test_val4")
                .setCategories(categories);

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getBody().entrySet().size(), is(5));
        assertThat(request.getBody().get("title").getAsString(), is("test_val2"));
        assertThat(request.getBody().get("description").getAsString(), is("test_val3"));
        assertThat(request.getBody().get("private").getAsString(), is("true"));
        assertThat(request.getBody().get("seo_url").getAsString(), is("test_val4"));
        assertThat(request.getBody().get("category_ids").getAsJsonArray().toString(), is("[5,6]"));

        assertThat(request.getQueryParams().size(), is(0));
        assertThat(request.getPathParams().size(), is(1));
    }

    @Test
    public void testGetCategoriesRequestBuilder_endpoint() {
        // Given
        GetCategoriesRequest.Builder builder = new GetCategoriesRequest.Builder();

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getEndpoint(), is("api/v2/categories"));
        assertThat(request.getMethod(), is(VzaarRequest.Method.GET));
    }

    @Test
    public void testGetCategoriesRequestBuilder_build_minParams() {
        // Given
        GetCategoriesRequest.Builder builder = new GetCategoriesRequest.Builder();

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getBody().entrySet().size(), is(0));
        assertThat(request.getQueryParams().size(), is(0));
        assertThat(request.getPathParams().size(), is(0));
    }

    @Test
    public void testGetCategoriesRequestBuilder_buildAll() {
        // Given
        GetCategoriesRequest.Builder builder = new GetCategoriesRequest.Builder()
                .setIds(new int[]{1, 2, 3})
                .setLevels(2)
                .setSort("test_val3")
                .setOrder("test_val4")
                .setPage(5)
                .setPerPage(6);

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getBody().entrySet().size(), is(0));
        assertThat(request.getQueryParams().size(), is(6));
        assertThat(request.getQueryParams().get("ids"), is("[1, 2, 3]"));
        assertThat(request.getQueryParams().get("levels"), is("2"));
        assertThat(request.getQueryParams().get("sort"), is("test_val3"));
        assertThat(request.getQueryParams().get("order"), is("test_val4"));
        assertThat(request.getQueryParams().get("page"), is("5"));
        assertThat(request.getQueryParams().get("per_page"), is("6"));
        assertThat(request.getPathParams().size(), is(0));
    }

    @Test
    public void testGetCategoriesSubtreeRequestBuilder_endpoint() {
        // Given
        GetCategoriesSubtreeRequest.Builder builder = new GetCategoriesSubtreeRequest.Builder(1);

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getEndpoint(), is("api/v2/categories/1/subtree"));
        assertThat(request.getMethod(), is(VzaarRequest.Method.GET));
    }

    @Test
    public void testGetCategoriesSubtreeRequestBuilder_build_minParams() {
        // Given
        GetCategoriesSubtreeRequest.Builder builder = new GetCategoriesSubtreeRequest.Builder(1);

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getBody().entrySet().size(), is(0));
        assertThat(request.getQueryParams().size(), is(0));
        assertThat(request.getPathParams().size(), is(1));
        assertThat(request.getPathParams().get("id"), is("1"));
    }

    @Test
    public void testGetCategoriesSubtreeRequestBuilder_build_AllParams() {
        // Given
        GetCategoriesSubtreeRequest.Builder builder = new GetCategoriesSubtreeRequest.Builder(1)
                .setLevels(2)
                .setPage(3)
                .setPerPage(4);

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getBody().entrySet().size(), is(0));
        assertThat(request.getQueryParams().size(), is(3));
        assertThat(request.getQueryParams().get("levels"), is("2"));
        assertThat(request.getQueryParams().get("page"), is("3"));
        assertThat(request.getQueryParams().get("per_page"), is("4"));
        assertThat(request.getPathParams().size(), is(1));
        assertThat(request.getPathParams().get("id"), is("1"));
    }

    @Test
    public void testGetCategoryRequestBuilder_endpoint() {
        // Given
        GetCategoryRequest.Builder builder = new GetCategoryRequest.Builder(1);

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getEndpoint(), startsWith("api/v2/categories/"));
        assertThat(request.getMethod(), is(VzaarRequest.Method.GET));
    }

    @Test
    public void testGetCategoryRequestBuilder_build() {
        // Given
        GetCategoryRequest.Builder builder = new GetCategoryRequest.Builder(1);

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getBody().entrySet().size(), is(0));
        assertThat(request.getQueryParams().size(), is(0));
        assertThat(request.getPathParams().size(), is(1));
        assertThat(request.getPathParams().get("id"), is("1"));
    }

    @Test
    public void testCreateCategoryRequestBuilder_endpoint() {
        // Given
        CreateCategoryRequest.Builder builder = new CreateCategoryRequest.Builder("testname");

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getEndpoint(), is("api/v2/categories"));
        assertThat(request.getMethod(), is(VzaarRequest.Method.POST));
    }

    @Test
    public void testCreateCategoryRequestBuilder_build_minParams() {
        // Given
        CreateCategoryRequest.Builder builder = new CreateCategoryRequest.Builder("testname");

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getBody().entrySet().size(), is(1));
        assertThat(request.getBody().get("name").getAsString(), is("testname"));
        assertThat(request.getQueryParams().size(), is(0));
        assertThat(request.getPathParams().size(), is(0));
    }

    @Test
    public void testCreateCategoryRequestBuilder_build_allParams() {
        // Given
        CreateCategoryRequest.Builder builder = new CreateCategoryRequest.Builder("test_val1")
                .setParentId(2);

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getBody().entrySet().size(), is(2));
        assertThat(request.getBody().get("name").getAsString(), is("test_val1"));
        assertThat(request.getBody().get("parent_id").getAsString(), is("2"));
        assertThat(request.getQueryParams().size(), is(0));
        assertThat(request.getPathParams().size(), is(0));
    }

    @Test
    public void testDeleteCategoryRequestBuilder_endpoint() {
        // Given
        DeleteCategoryRequest.Builder builder = new DeleteCategoryRequest.Builder(1);

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getEndpoint(), is("api/v2/categories/1"));
        assertThat(request.getMethod(), is(VzaarRequest.Method.DELETE));
    }

    @Test
    public void testDeleteCategoryRequestBuilder_build_allParams() {
        // Given
        DeleteCategoryRequest.Builder builder = new DeleteCategoryRequest.Builder(1);

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getBody().entrySet().size(), is(0));
        assertThat(request.getQueryParams().size(), is(0));
        assertThat(request.getPathParams().size(), is(1));
    }

    @Test
    public void testUpdateCategoryRequestBuilder_endpoint() {
        // Given
        UpdateCategoryRequest.Builder builder = new UpdateCategoryRequest.Builder(1);

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getEndpoint(), is("api/v2/categories/1"));
        assertThat(request.getMethod(), is(VzaarRequest.Method.PATCH));
    }

    @Test
    public void testUpdateCategoryRequestBuilder_build_minParams() {
        // Given
        UpdateCategoryRequest.Builder builder = new UpdateCategoryRequest.Builder(1);

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getBody().entrySet().size(), is(0));
        assertThat(request.getQueryParams().size(), is(0));
        assertThat(request.getPathParams().size(), is(1));
    }

    @Test
    public void testUpdateCategoryRequestBuilder_build_allParams() {
        // Given
        UpdateCategoryRequest.Builder builder = new UpdateCategoryRequest.Builder(1)
                .setName("test_val2")
                .setParentId(3)
                .setMoveToRoot(true);

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getBody().entrySet().size(), is(3));
        assertThat(request.getBody().get("name").getAsString(), is("test_val2"));
        assertThat(request.getBody().get("parent_id").getAsString(), is("3"));
        assertThat(request.getBody().get("move_to_root").getAsString(), is("true"));
        assertThat(request.getQueryParams().size(), is(0));
        assertThat(request.getPathParams().size(), is(1));
    }

    @Test
    public void testGetIngestRecipeBuilder_endpoint() {
        // Given
        GetIngestRecipeRequest.Builder builder = new GetIngestRecipeRequest.Builder(1);

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getEndpoint(), startsWith("api/v2/ingest_recipes/"));
        assertThat(request.getMethod(), is(VzaarRequest.Method.GET));
    }

    @Test
    public void testGetIngestRecipeRequestBuilder_build() {
        // Given
        GetIngestRecipeRequest.Builder builder = new GetIngestRecipeRequest.Builder(1);

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getBody().entrySet().size(), is(0));
        assertThat(request.getQueryParams().size(), is(0));
        assertThat(request.getPathParams().size(), is(1));
        assertThat(request.getPathParams().get("id"), is("1"));
    }

    @Test
    public void testGetIngestRecipesRequestBuilder_build_minParams() {
        // Given
        GetIngestRecipesRequest.Builder builder = new GetIngestRecipesRequest.Builder();

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getBody().entrySet().size(), is(0));
        assertThat(request.getQueryParams().size(), is(0));
        assertThat(request.getPathParams().size(), is(0));
    }

    @Test
    public void testCreateIngestRecipeBuilder_endpoint() {
        // Given
        List<EncodingPreset> encodingPresets = new ArrayList<EncodingPreset>() {{
            add(createMockEncodingPresetWithId(1));
        }};

        CreateIngestRecipeRequest.Builder builder = new CreateIngestRecipeRequest.Builder(
                "test_name", encodingPresets);

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getEndpoint(), is("api/v2/ingest_recipes"));
        assertThat(request.getMethod(), is(VzaarRequest.Method.POST));
    }

    @Test
    public void testCreateIngestRecipeRequestBuilder_build_minParams() {
        // Given
        List<EncodingPreset> encodingPresets = new ArrayList<EncodingPreset>() {{
            add(createMockEncodingPresetWithId(1));
        }};

        String name = "test_name";

        CreateIngestRecipeRequest.Builder builder = new CreateIngestRecipeRequest.Builder(name,
                encodingPresets);

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getBody().entrySet().size(), is(2));
        assertThat(request.getBody().get("name").getAsString(), is(name));
        assertThat(request.getBody().get("encoding_preset_ids").getAsJsonArray().get(0).getAsInt(),
                is(1));
        assertThat(request.getQueryParams().size(), is(0));
        assertThat(request.getPathParams().size(), is(0));
    }

    @Test
    public void testCreateIngestRecipeRequestBuilder_build_allParams() {
        // Given
        List<EncodingPreset> encodingPresets = new ArrayList<EncodingPreset>() {{
            add(createMockEncodingPresetWithId(1));
        }};

        String name = "test_name";

        CreateIngestRecipeRequest.Builder builder = new CreateIngestRecipeRequest.Builder(name,
                encodingPresets)
                .setDefault(true)
                .setGenerateAnimatedThumb(true)
                .setGenerateSprite(true)
                .setMultipass(true)
                .setSendToYoutube(true)
                .setUseWatermark(true);

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getBody().entrySet().size(), is(8));
        assertThat(request.getBody().get("name").getAsString(), is(name));
        assertThat(request.getBody().get("encoding_preset_ids").getAsJsonArray().get(0).getAsInt(),
                is(1));
        assertThat(request.getBody().get("default").getAsBoolean(), is(true));
        assertThat(request.getBody().get("generate_animated_thumb").getAsBoolean(), is(true));
        assertThat(request.getBody().get("generate_sprite").getAsBoolean(), is(true));
        assertThat(request.getBody().get("multipass").getAsBoolean(), is(true));
        assertThat(request.getBody().get("send_to_youtube").getAsBoolean(), is(true));
        assertThat(request.getBody().get("use_watermark").getAsBoolean(), is(true));
        assertThat(request.getQueryParams().size(), is(0));
        assertThat(request.getPathParams().size(), is(0));
    }

    @Test
    public void testUpdateIngestRecipeRequestBuilder_endpoint() {
        // Given

        UpdateIngestRecipeRequest.Builder builder = new UpdateIngestRecipeRequest.Builder(1);

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getEndpoint(), is("api/v2/ingest_recipes/1"));
        assertThat(request.getMethod(), is(VzaarRequest.Method.PATCH));
    }

    @Test
    public void testUpdateIngestRecipeRequestBuilder_build_minParams() {
        // Given

        UpdateIngestRecipeRequest.Builder builder = new UpdateIngestRecipeRequest.Builder(1);

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getBody().entrySet().size(), is(0));
        assertThat(request.getQueryParams().size(), is(0));
        assertThat(request.getPathParams().size(), is(1));
        assertThat(request.getPathParams().get("id"), is("1"));
    }

    @Test
    public void testUpdateIngestRecipeRequestBuilder_build_allParams() {
        // Given
        List<EncodingPreset> encodingPresets = new ArrayList<EncodingPreset>() {{
            add(createMockEncodingPresetWithId(1));
        }};

        UpdateIngestRecipeRequest.Builder builder = new UpdateIngestRecipeRequest.Builder(1)
                .setName("test_name")
                .setDefault(true)
                .setEncodingPresets(encodingPresets)
                .setGenerateAnimatedThumb(true)
                .setGenerateSprite(true)
                .setMultipass(true)
                .setSendToYoutube(true)
                .setUseWatermark(true);

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getPathParams().size(), is(1));
        assertThat(request.getPathParams().get("id"), is("1"));

        assertThat(request.getBody().entrySet().size(), is(8));
        assertThat(request.getBody().get("name").getAsString(), is("test_name"));
        assertThat(request.getBody().get("default").getAsBoolean(), is(true));
        assertThat(request.getBody().get("encoding_preset_ids").getAsJsonArray().get(0).getAsInt(),
                is(1));
        assertThat(request.getBody().get("generate_animated_thumb").getAsBoolean(), is(true));
        assertThat(request.getBody().get("generate_sprite").getAsBoolean(), is(true));
        assertThat(request.getBody().get("multipass").getAsBoolean(), is(true));
        assertThat(request.getBody().get("send_to_youtube").getAsBoolean(), is(true));
        assertThat(request.getBody().get("use_watermark").getAsBoolean(), is(true));

        assertThat(request.getQueryParams().size(), is(0));
    }

    @Test
    public void testDeleteIngestRecipeRequestBuilder_endpoint() {
        // Given
        DeleteIngestRecipeRequest.Builder builder = new DeleteIngestRecipeRequest.Builder(1);

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getEndpoint(), is("api/v2/ingest_recipes/1"));
        assertThat(request.getMethod(), is(VzaarRequest.Method.DELETE));
    }

    @Test
    public void testDeleteIngestRecipeRequestBuilder_build() {
        // Given
        DeleteIngestRecipeRequest.Builder builder = new DeleteIngestRecipeRequest.Builder(1);

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getBody().entrySet().size(), is(0));
        assertThat(request.getQueryParams().size(), is(0));
        assertThat(request.getPathParams().size(), is(1));
        assertThat(request.getPathParams().get("id"), is("1"));
    }

    @Test
    public void testGetEncodingPresetRequestBuilder_endpoint() {
        // Given
        GetEncodingPresetRequest.Builder builder = new GetEncodingPresetRequest.Builder(1);

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getEndpoint(), is("api/v2/encoding_presets/1"));
        assertThat(request.getMethod(), is(VzaarRequest.Method.GET));
    }

    @Test
    public void testGetEncodingPresetRequestBuilder_build() {
        // Given
        GetEncodingPresetRequest.Builder builder = new GetEncodingPresetRequest.Builder(1);

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getBody().entrySet().size(), is(0));
        assertThat(request.getQueryParams().size(), is(0));
        assertThat(request.getPathParams().size(), is(1));
        assertThat(request.getPathParams().get("id"), is("1"));
    }


    @Test
    public void testGetEncodingPresetsRequestBuilder_endpoint() {
        // Given
        GetEncodingPresetsRequest.Builder builder = new GetEncodingPresetsRequest.Builder();

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getEndpoint(), is("api/v2/encoding_presets"));
        assertThat(request.getMethod(), is(VzaarRequest.Method.GET));
    }

    @Test
    public void testGetEncodingPresetsRequestBuilder_build_minParams() {
        // Given
        GetEncodingPresetsRequest.Builder builder = new GetEncodingPresetsRequest.Builder();

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getBody().entrySet().size(), is(0));
        assertThat(request.getQueryParams().size(), is(0));
        assertThat(request.getPathParams().size(), is(0));
    }

    @Test
    public void testGetEncodingPresetRequestBuilder_build_allParams() {
        // Given
        GetEncodingPresetsRequest.Builder builder = new GetEncodingPresetsRequest.Builder()
                .setOrder("test_param_1")
                .setPage(2)
                .setPageSize(3)
                .setSort("test_param_4");

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getBody().entrySet().size(), is(0));

        assertThat(request.getQueryParams().size(), is(4));
        assertThat(request.getQueryParams().get("order"), is("test_param_1"));
        assertThat(request.getQueryParams().get("page"), is("2"));
        assertThat(request.getQueryParams().get("per_page"), is("3"));
        assertThat(request.getQueryParams().get("sort"), is("test_param_4"));

        assertThat(request.getPathParams().size(), is(0));
    }

    @Test
    public void testCreateSingleUploadSignatureBuilder_endpoint() {
        // Given
        CreateSingleUploadSignatureRequest.Builder builder = new CreateSingleUploadSignatureRequest.Builder(
                "test_uploader");

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getEndpoint(), is("api/v2/signature/single"));
        assertThat(request.getMethod(), is(VzaarRequest.Method.POST));
    }

    @Test
    public void testCreateSingleUploadSignatureRequestBuilder_build_minParams() {
        // Given
        String uploader = "test_uploader";

        CreateSingleUploadSignatureRequest.Builder builder = new CreateSingleUploadSignatureRequest.Builder(
                "test_uploader");

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getBody().entrySet().size(), is(1));
        assertThat(request.getBody().get("uploader").getAsString(), is(uploader));
        assertThat(request.getQueryParams().size(), is(0));
        assertThat(request.getPathParams().size(), is(0));
    }

    @Test
    public void testCreateSingleUploadSignatureRequestBuilder_build_allParams() {
        // Given
        String uploader = "test_uploader";

        CreateSingleUploadSignatureRequest.Builder builder = new CreateSingleUploadSignatureRequest.Builder(
                uploader)
                .setFileName("test_val2")
                .setFileSize("test_val3")
                .setDesiredPartSize("test_val4");

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getBody().entrySet().size(), is(4));
        assertThat(request.getBody().get("uploader").getAsString(), is(uploader));
        assertThat(request.getBody().get("filename").getAsString(), is("test_val2"));
        assertThat(request.getBody().get("filesize").getAsString(), is("test_val3"));
        assertThat(request.getBody().get("desired_part_size").getAsString(), is("test_val4"));
        assertThat(request.getQueryParams().size(), is(0));
        assertThat(request.getPathParams().size(), is(0));
    }

    @Test
    public void testCreateMultipartUploadSignatureBuilder_endpoint() {
        // Given
        CreateMultipartUploadSignatureRequest.Builder builder = new CreateMultipartUploadSignatureRequest.Builder(
                "test_val1", "test_val2", 3);

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getEndpoint(), is("api/v2/signature/multipart"));
        assertThat(request.getMethod(), is(VzaarRequest.Method.POST));
    }

    @Test
    public void testCreateMultipartUploadSignatureRequestBuilder_build_minParams() {
        // Given
        CreateMultipartUploadSignatureRequest.Builder builder = new CreateMultipartUploadSignatureRequest.Builder(
                "test_val1", "test_val2", 3);

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getBody().entrySet().size(), is(3));
        assertThat(request.getBody().get("uploader").getAsString(), is("test_val1"));
        assertThat(request.getBody().get("filename").getAsString(), is("test_val2"));
        assertThat(request.getBody().get("filesize").getAsLong(), is(3L));
        assertThat(request.getQueryParams().size(), is(0));
        assertThat(request.getPathParams().size(), is(0));
    }

    @Test
    public void testCreateMultipartUploadSignatureRequestBuilder_build_allParams() {
        // Given
        CreateMultipartUploadSignatureRequest.Builder builder = new CreateMultipartUploadSignatureRequest.Builder(
                "test_val1", "test_val2", 3)
                .setDesiredPartSize("test_val4");

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getBody().entrySet().size(), is(4));
        assertThat(request.getBody().get("uploader").getAsString(), is("test_val1"));
        assertThat(request.getBody().get("filename").getAsString(), is("test_val2"));
        assertThat(request.getBody().get("filesize").getAsLong(), is(3L));
        assertThat(request.getBody().get("desired_part_size").getAsString(), is("test_val4"));
        assertThat(request.getQueryParams().size(), is(0));
        assertThat(request.getPathParams().size(), is(0));
    }


    @Test
    public void testCreateLinkUploadBuilder_endpoint() {
        // Given
        CreateLinkUploadRequest.Builder builder = new CreateLinkUploadRequest.Builder(
                "test_val1", "test_val2");

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getEndpoint(), is("api/v2/link_uploads"));
        assertThat(request.getMethod(), is(VzaarRequest.Method.POST));
    }

    @Test
    public void testCreateLinkUploadRequestBuilder_build_minParams() {
        // Given
        CreateLinkUploadRequest.Builder builder = new CreateLinkUploadRequest.Builder(
                "test_val1", "test_val2");

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getBody().entrySet().size(), is(2));
        assertThat(request.getBody().get("uploader").getAsString(), is("test_val1"));
        assertThat(request.getBody().get("url").getAsString(), is("test_val2"));
        assertThat(request.getQueryParams().size(), is(0));
        assertThat(request.getPathParams().size(), is(0));
    }

    @Test
    public void testCreateLinkUploadRequestBuilder_build_allParams() {
        // Given
        IngestRecipe mockRecipe = mock(IngestRecipe.class);
        when(mockRecipe.getId()).thenReturn(4);
        CreateLinkUploadRequest.Builder builder = new CreateLinkUploadRequest.Builder(
                "test_val1", "test_val2")
                .setDescription("test_val3")
                .setIngestRecipe(mockRecipe)
                .setTitle("test_val5");

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getBody().entrySet().size(), is(5));
        assertThat(request.getBody().get("uploader").getAsString(), is("test_val1"));
        assertThat(request.getBody().get("url").getAsString(), is("test_val2"));
        assertThat(request.getBody().get("description").getAsString(), is("test_val3"));
        assertThat(request.getBody().get("ingest_recipe").getAsInt(), is(4));
        assertThat(request.getBody().get("title").getAsString(), is("test_val5"));
        assertThat(request.getQueryParams().size(), is(0));
        assertThat(request.getPathParams().size(), is(0));
    }

    @Test
    public void testCreatePlaylistRequestBuilder_endpoint() {
        // Given
        CreatePlaylistRequest.Builder builder = new CreatePlaylistRequest.Builder("test_val1", 2);

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getEndpoint(), is("api/v2/playlists"));
        assertThat(request.getMethod(), is(VzaarRequest.Method.POST));
    }

    @Test
    public void testCreatePlaylistRequestBuilder_build_minParams() {
        // Given
        CreatePlaylistRequest.Builder builder = new CreatePlaylistRequest.Builder("test_val1", 2);

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getBody().entrySet().size(), is(2));
        assertThat(request.getBody().get("title").getAsString(), is("test_val1"));
        assertThat(request.getBody().get("category_id").getAsInt(), is(2));
        assertThat(request.getQueryParams().size(), is(0));
        assertThat(request.getPathParams().size(), is(0));
    }

    @Test
    public void testCreatePlaylistRequestBuilder_build_allParams() {
        // Given
        CreatePlaylistRequest.Builder builder = new CreatePlaylistRequest.Builder("test_val1", 2)
                .setPrivate(true)
                .setAutoplay(true)
                .setContinuousPlay(true)
                .setDimensions(123, 456)
                .setMaxVids(7)
                .setPosition("test_val8")
                .setSortBy("test_val9")
                .setSortOrder("test_val10");


        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getBody().entrySet().size(), is(10));
        assertThat(request.getBody().get("title").getAsString(), is("test_val1"));
        assertThat(request.getBody().get("category_id").getAsInt(), is(2));
        assertThat(request.getBody().get("private").getAsBoolean(), is(true));
        assertThat(request.getBody().get("autoplay").getAsBoolean(), is(true));
        assertThat(request.getBody().get("continuous_play").getAsBoolean(), is(true));
        assertThat(request.getBody().get("dimensions").getAsString(), is("123x456"));
        assertThat(request.getBody().get("max_vids").getAsInt(), is(7));
        assertThat(request.getBody().get("position").getAsString(), is("test_val8"));
        assertThat(request.getBody().get("sort_by").getAsString(), is("test_val9"));
        assertThat(request.getBody().get("sort_order").getAsString(), is("test_val10"));
        assertThat(request.getQueryParams().size(), is(0));
        assertThat(request.getPathParams().size(), is(0));
    }

    @Test
    public void testGetPlaylistsRequestBuilder_endpoint() {
        // Given
        GetPlaylistsRequest.Builder builder = new GetPlaylistsRequest.Builder();

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getEndpoint(), is("api/v2/playlists"));
        assertThat(request.getMethod(), is(VzaarRequest.Method.GET));
    }

    @Test
    public void testGetPlaylistsRequestBuilder_build_minParams() {
        // Given
        GetPlaylistsRequest.Builder builder = new GetPlaylistsRequest.Builder();

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getBody().entrySet().size(), is(0));
        assertThat(request.getQueryParams().size(), is(0));
        assertThat(request.getPathParams().size(), is(0));
    }

    @Test
    public void testGetPlaylistsRequestBuilder_build_allParams() {
        // Given
        GetPlaylistsRequest.Builder builder = new GetPlaylistsRequest.Builder()
                .setSort("test_val1")
                .setOrder("test_val2")
                .setPage(3)
                .setPerPage(4);

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getBody().entrySet().size(), is(0));
        assertThat(request.getQueryParams().size(), is(4));
        assertThat(request.getQueryParams().get("sort"), is("test_val1"));
        assertThat(request.getQueryParams().get("order"), is("test_val2"));
        assertThat(request.getQueryParams().get("page"), is("3"));
        assertThat(request.getQueryParams().get("per_page"), is("4"));
        assertThat(request.getPathParams().size(), is(0));
    }

    @Test
    public void testGetPlaylistRequestBuilder_endpoint() {
        // Given
        GetPlaylistRequest.Builder builder = new GetPlaylistRequest.Builder(1);

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getEndpoint(), startsWith("api/v2/playlists/"));
        assertThat(request.getMethod(), is(VzaarRequest.Method.GET));
    }

    @Test
    public void testGetPlaylistRequestBuilder_build() {
        // Given
        GetPlaylistRequest.Builder builder = new GetPlaylistRequest.Builder(1);

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getBody().entrySet().size(), is(0));
        assertThat(request.getQueryParams().size(), is(0));
        assertThat(request.getPathParams().size(), is(1));
        assertThat(request.getPathParams().get("id"), is("1"));
    }

    @Test
    public void testDeletePlaylistRequestBuilder_endpoint() {
        // Given
        DeletePlaylistRequest.Builder builder = new DeletePlaylistRequest.Builder(1);

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getEndpoint(), is("api/v2/playlists/1"));
        assertThat(request.getMethod(), is(VzaarRequest.Method.DELETE));
    }

    @Test
    public void testDeletePlaylistRequestBuilder_build_allParams() {
        // Given
        DeletePlaylistRequest.Builder builder = new DeletePlaylistRequest.Builder(1);

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getBody().entrySet().size(), is(0));
        assertThat(request.getQueryParams().size(), is(0));
        assertThat(request.getPathParams().size(), is(1));
    }

    @Test
    public void testUpdatePlaylistRequestBuilder_endpoint() {
        // Given
        UpdatePlaylistRequest.Builder builder = new UpdatePlaylistRequest.Builder(1);

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getEndpoint(), is("api/v2/playlists/1"));
        assertThat(request.getMethod(), is(VzaarRequest.Method.PATCH));
    }

    @Test
    public void testUpdatePlaylistRequestBuilder_build_minParams() {
        // Given
        UpdatePlaylistRequest.Builder builder = new UpdatePlaylistRequest.Builder(1);

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getBody().entrySet().size(), is(0));
        assertThat(request.getQueryParams().size(), is(0));
        assertThat(request.getPathParams().size(), is(1));
    }

    @Test
    public void testUpdatePlaylistRequestBuilder_build_allParams() {
        // Given
        UpdatePlaylistRequest.Builder builder = new UpdatePlaylistRequest.Builder(1)
                .setTitle("test_val2")
                .setPrivate(true)
                .setAutoplay(true)
                .setContinuousPlay(true)
                .setDimensions(123, 456)
                .setMaxVids(7)
                .setPosition("test_val8")
                .setSortBy("test_val9")
                .setSortOrder("test_val10");

        // When
        VzaarRequest request = builder.build();

        // Then
        assertThat(request.getBody().entrySet().size(), is(9));
        assertThat(request.getBody().get("title").getAsString(), is("test_val2"));
        assertThat(request.getBody().get("private").getAsBoolean(), is(true));
        assertThat(request.getBody().get("autoplay").getAsBoolean(), is(true));
        assertThat(request.getBody().get("continuous_play").getAsBoolean(), is(true));
        assertThat(request.getBody().get("dimensions").getAsString(), is("123x456"));
        assertThat(request.getBody().get("max_vids").getAsInt(), is(7));
        assertThat(request.getBody().get("position").getAsString(), is("test_val8"));
        assertThat(request.getBody().get("sort_by").getAsString(), is("test_val9"));
        assertThat(request.getBody().get("sort_order").getAsString(), is("test_val10"));
        assertThat(request.getQueryParams().size(), is(0));
        assertThat(request.getPathParams().size(), is(1));
    }
}
