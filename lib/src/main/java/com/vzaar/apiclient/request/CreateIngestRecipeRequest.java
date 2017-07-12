package com.vzaar.apiclient.request;

import com.vzaar.apiclient.VzaarCallback;
import com.vzaar.apiclient.model.EncodingPreset;

import java.util.List;

/**
 * A request to create an Ingest Recipe.
 * <p/>
 * {@inheritDoc}
 *
 * @see com.vzaar.apiclient.Vzaar#createIngestRecipe(CreateIngestRecipeRequest, VzaarCallback)
 */
public class CreateIngestRecipeRequest extends VzaarRequest {
    private CreateIngestRecipeRequest() {
        super("api/v2/ingest_recipes", Method.POST);
    }

    public static class Builder {
        private final CreateIngestRecipeRequest obj;

        public Builder(String name, List<EncodingPreset> encodingPresets) {
            obj = new CreateIngestRecipeRequest();
            obj.addBodyParam("name", name);
            obj.addBodyParam("encoding_preset_ids",
                    JsonUtil.createIdsArray(encodingPresets));
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

        public CreateIngestRecipeRequest build() {
            return obj;
        }
    }

}
