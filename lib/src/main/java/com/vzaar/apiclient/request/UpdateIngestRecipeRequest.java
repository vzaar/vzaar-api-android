package com.vzaar.apiclient.request;

import com.vzaar.apiclient.VzaarCallback;
import com.vzaar.apiclient.model.EncodingPreset;

import java.util.List;

/**
 * A request to update an ingest recipe.
 * <p/>
 * {@inheritDoc}
 *
 * @see com.vzaar.apiclient.Vzaar#updateIngestRecipe(UpdateIngestRecipeRequest, VzaarCallback)
 */
public class UpdateIngestRecipeRequest extends VzaarRequest {

    private UpdateIngestRecipeRequest() {
        super("api/v2/ingest_recipes/{id}", Method.PATCH);
    }

    public static class Builder {
        private final UpdateIngestRecipeRequest obj;

        public Builder(int id) {
            obj = new UpdateIngestRecipeRequest();
            obj.addPathParam("id", Integer.toString(id));
        }

        public Builder setName(String value) {
            obj.addBodyParam("name", value);
            return this;
        }

        public Builder setEncodingPresets(List<EncodingPreset> encodingPresets) {
            obj.addBodyParam("encoding_preset_ids",
                    JsonUtil.createIdsArray(encodingPresets));
            return this;
        }

        public Builder setDefault(boolean value) {
            obj.addBodyParam("default", value);
            return this;
        }

        public Builder setMultipass(boolean value) {
            obj.addBodyParam("multipass", value);
            return this;
        }

        public Builder setGenerateAnimatedThumb(boolean value) {
            obj.addBodyParam("generate_animated_thumb", value);
            return this;
        }

        public Builder setGenerateSprite(boolean value) {
            obj.addBodyParam("generate_sprite", value);
            return this;
        }

        public Builder setUseWatermark(boolean value) {
            obj.addBodyParam("use_watermark", value);
            return this;
        }

        public Builder setSendToYoutube(boolean value) {
            obj.addBodyParam("send_to_youtube", value);
            return this;
        }

        public UpdateIngestRecipeRequest build() {
            return obj;
        }
    }
}
