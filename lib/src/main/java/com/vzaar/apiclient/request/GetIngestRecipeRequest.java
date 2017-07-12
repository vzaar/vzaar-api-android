package com.vzaar.apiclient.request;

import com.vzaar.apiclient.VzaarCallback;

/**
 * A request to look up an ingest recipe.
 * <p/>
 * {@inheritDoc}
 *
 * @see com.vzaar.apiclient.Vzaar#getIngestRecipe(GetIngestRecipeRequest, VzaarCallback)
 */
public class GetIngestRecipeRequest extends VzaarRequest {

    private GetIngestRecipeRequest() {
        super("api/v2/ingest_recipes/{id}", VzaarRequest.Method.GET);
    }

    public static class Builder {
        private final GetIngestRecipeRequest obj;

        public Builder(int id) {
            obj = new GetIngestRecipeRequest();
            obj.addPathParam("id", Integer.toString(id));
        }

        public GetIngestRecipeRequest build() {
            return obj;
        }
    }
}
