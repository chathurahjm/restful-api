package org.amused.utils;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.amused.model.ProductData;

public class JsonToObjectJacsonUtil {
     private static final ObjectMapper objectMapper;

        static {
            objectMapper = new ObjectMapper();
            objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        }

        public static <T> T fromJson(String json, Class<T> valueType) {
            try {
                return objectMapper.readValue(json, valueType);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }


}
