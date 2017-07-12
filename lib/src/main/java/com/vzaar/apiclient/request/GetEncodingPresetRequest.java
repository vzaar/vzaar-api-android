package com.vzaar.apiclient.request;

import com.vzaar.apiclient.VzaarCallback;

/**
 * A request to look up an encoding preset.
 * <p/>
 * {@inheritDoc}
 *
 * @see com.vzaar.apiclient.Vzaar#getEncodingPreset(GetEncodingPresetRequest, VzaarCallback)
 */
public class GetEncodingPresetRequest extends VzaarRequest {

    private GetEncodingPresetRequest() {
        super("api/v2/encoding_presets/{id}", Method.GET);
    }

    public static class Builder {

        final GetEncodingPresetRequest obj;

        public Builder(int id) {
            obj = new GetEncodingPresetRequest();
            obj.addPathParam("id", Integer.toString(id));
        }

        public GetEncodingPresetRequest build() {
            return obj;
        }
    }
}
