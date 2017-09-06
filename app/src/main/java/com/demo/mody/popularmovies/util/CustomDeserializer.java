package com.demo.mody.popularmovies.util;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by Mahmoud El-Metainy on 16-Dec-15.
 */

/**
 * Custom Json deserializer to return the embedded Json objects
 *
 * @param <T> Generic data type
 */
public class CustomDeserializer<T> implements JsonDeserializer<T> {

    @Override
    public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        // Get the "DiscoverMovie" element from the parsed Json
        JsonElement content = json.getAsJsonObject().get("results");

        // Return the deserialized Json
        return new Gson().fromJson(content, typeOfT);
    }
}
