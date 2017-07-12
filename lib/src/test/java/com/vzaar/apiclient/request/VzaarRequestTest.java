package com.vzaar.apiclient.request;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class VzaarRequestTest {

    @Test
    public void testGetEndpoint() {
        // Given
        VzaarRequest request = new VzaarRequest(
                "endpoint/with/{path_param_1_key}/and/{path_param_2_key}",
                VzaarRequest.Method.GET);

        request.addPathParam("path_param_1_key", "path_param_1_val");
        request.addPathParam("path_param_2_key", "path_param_2_val");

        // When
        String endPoint = request.getEndpoint();

        // Then
        assertThat(endPoint, is("endpoint/with/path_param_1_val/and/path_param_2_val"));

    }
}
