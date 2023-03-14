package group.bison.cucumber.common.utils;

import java.io.IOException;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <Description>
 *
 * @author yuan
 * @since 2019-03-03
 */
public class JsonUtil {
    static final ObjectMapper mapper = new ObjectMapper();

    public static String toJson(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj.getClass() == String.class) {
            return obj.toString();
        }
        
        String json;
        try {
            json = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }


    public static <T> T fromJson(String content, Class<T> classType) {
        if (StringUtils.isEmpty(content)) {
            return null;
        }
        if (classType == String.class) {
            return (T) content;
        }
        try {
            return mapper.readValue(content, classType);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
