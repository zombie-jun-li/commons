package toddler.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.io.UncheckedIOException;

/**
 * Created by jun.li
 */
public class Json {
    private static final ObjectMapper OBJECTMAPPER = new ObjectMapper();

    static {
        OBJECTMAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OBJECTMAPPER.configure(SerializationFeature.INDENT_OUTPUT, false);
        OBJECTMAPPER.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        OBJECTMAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }


    public static String toJson(Object value) {
        try {
            return OBJECTMAPPER.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static <T> T fromJson(String json, Class<T> type) {
        try {
            return OBJECTMAPPER.readValue(json, type);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
