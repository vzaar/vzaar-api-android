package com.vzaar.apiclient.request;

import com.google.gson.JsonArray;
import com.vzaar.apiclient.model.EncodingPreset;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JsonUtilTest {

    @Test
    public void testCreateEncodingPresetIdsArray() {
        // Given
        EncodingPreset encodingPreset1 = mock(EncodingPreset.class);
        when(encodingPreset1.getId()).thenReturn(1);

        EncodingPreset encodingPreset2 = mock(EncodingPreset.class);
        when(encodingPreset2.getId()).thenReturn(2);

        EncodingPreset encodingPreset3 = mock(EncodingPreset.class);
        when(encodingPreset3.getId()).thenReturn(3);

        List<EncodingPreset> input = Arrays.asList(
                encodingPreset1,
                encodingPreset2,
                encodingPreset3
        );


        // When
        JsonArray jsonArray = JsonUtil.createIdsArray(input);

        // Then
        assertThat(jsonArray.size(), is(3));
        for (int i = 0; i < input.size(); i++) {
            assertThat(jsonArray.get(i).getAsInt(), is(input.get(i).getId()));
        }
    }

    @Test
    public void testCreateEncodingPresetIdsArray_emptyInput() {
        // Given
        List<EncodingPreset> input = new ArrayList<>();

        // When
        JsonArray jsonArray = JsonUtil.createIdsArray(input);

        // Then
        assertThat(jsonArray.size(), is(0));
    }
}
