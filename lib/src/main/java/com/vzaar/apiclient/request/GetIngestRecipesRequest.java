package com.vzaar.apiclient.request;

import com.vzaar.apiclient.VzaarCallback;

/**
 * A request to get a list of ingest recipes.
 * <p/>
 * {@inheritDoc}
 *
 * @see com.vzaar.apiclient.Vzaar#getIngestRecipes(GetIngestRecipesRequest, VzaarCallback)
 */
public class GetIngestRecipesRequest extends VzaarRequest {
    private GetIngestRecipesRequest() {
        super("api/v2/ingest_recipes", VzaarRequest.Method.GET);
    }

    @SuppressWarnings("unused")
    public static class Builder {
        private final GetIngestRecipesRequest obj;

        public Builder() {
            obj = new GetIngestRecipesRequest();
        }

        public Builder setOrder(String value) {
            obj.addQueryParam("order", value);
            return this;
        }

        public Builder setPage(int value) {
            obj.addQueryParam("page", Integer.toString(value));
            return this;
        }

        public Builder setPerPage(int value) {
            obj.addQueryParam("per_page", Integer.toString(value));
            return this;
        }

        public GetIngestRecipesRequest build() {
            return obj;
        }
    }
}
