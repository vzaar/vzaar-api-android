package com.vzaar.apiclient.request;

import com.vzaar.apiclient.VzaarCallback;

/**
 * A request to create an ingest recipe.
 * <p/>
 * {@inheritDoc}
 *
 * @see com.vzaar.apiclient.Vzaar#createIngestRecipe(CreateIngestRecipeRequest, VzaarCallback)
 */
public class DeleteIngestRecipeRequest extends VzaarRequest {

    private DeleteIngestRecipeRequest() {
        super("api/v2/ingest_recipes/{id}", Method.DELETE);
    }

    public static class Builder {
        private final DeleteIngestRecipeRequest obj;

        public Builder(int id) {
            obj = new DeleteIngestRecipeRequest();
            obj.addPathParam("id", Integer.toString(id));
        }

        public DeleteIngestRecipeRequest build() {
            return obj;
        }
    }
}
