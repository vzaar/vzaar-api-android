package com.vzaar.apiclient.request;

import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;
import com.vzaar.apiclient.model.VzaarModel;

import java.util.List;

class JsonUtil {

    private JsonUtil() {

    }

    static <T extends VzaarModel> JsonArray createIdsArray(List<T> models) {
        JsonArray encodingPresetIds = new JsonArray();
        for (int i = 0; i < models.size(); i++) {
            encodingPresetIds.add(new JsonPrimitive(models.get(i).getId()));
        }
        return encodingPresetIds;
    }
}
