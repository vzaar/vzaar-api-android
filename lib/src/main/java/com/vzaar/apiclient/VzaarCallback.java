package com.vzaar.apiclient;

import com.vzaar.apiclient.response.VzaarResponse;

/**
 * Callback for Vzaar API requests. The Vzaar API may return a success or error response or it may
 * not return at all.
 * <p>
 * Implementations should expect
 * {@link VzaarCallback#onNoResponse(VzaarException)} to be called when the server returns HTTP code
 * 500 (an internal server error).
 */
public interface VzaarCallback<T extends VzaarResponse> {
    /**
     * Called when a success code is returned by the Vzaar API.
     */
    void onSuccessResponse(T response);

    /**
     * Called when a non-success code is returned by the Vzaar API.
     * <p />
     * Note that this is not called when the server returns HTTP status code 500 (internal server
     * error). In this case, {@link VzaarCallback#onNoResponse(VzaarException)} is called.
     * @param response The response object which contains details of the errors returned by the API.
     */
    void onErrorResponse(T response);

    /**
     * Called when there is some error which is not expected from the Vzaar API. For example, this
     * may be called when there is a connection timeout or an internal server error.
     * <p />
     * Expected error responses, for example authentication errors, will instead call
     * {@link VzaarCallback#onErrorResponse(VzaarResponse)}
     */
    void onNoResponse(VzaarException e);
}
