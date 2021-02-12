package jeebbs.restful.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * Created by ztwang on 2017/6/30 0030.
 */
public final class JacksonUtil {
    private static final Logger LOG = LoggerFactory.getLogger(JacksonUtil.class);
    private static ObjectMapper objectMapper = new ObjectMapper();

    private JacksonUtil() {
        throw new AssertionError();
    }

    /**
     * json数据转pojo
     *
     * @param jsonStr json字符串
     * @param cls     映射类型
     * @param <T>     推导类型
     * @return 推导类型json对象
     */
    public static <T> T json2Pojo(String jsonStr, Class<T> cls) {
        T object = null;
        try {
            object = objectMapper.readValue(jsonStr, cls);
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        return object;
    }


    /**
     * json数据转PojoList
     *
     * @param jsonStr json数据
     * @param cls     类型
     * @param <T>     推导类型
     * @return pojoList
     */
    public static <T> List<T> jsonArray2PojoList(String jsonStr, Class<T> cls) {
        List<T> pojoList = null;
        try {
            CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, cls);
            pojoList = objectMapper.readValue(jsonStr, listType);
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        return pojoList;
    }


    /**
     * pojo转json
     *
     * @param obj pojo
     * @return json字符串
     */
    public static String pojo2Json(Object obj) {
        String jsonStr = "";
        try {
            jsonStr = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            LOG.error(e.getMessage());
        }
        return jsonStr;
    }

    /**
     * json转listMap
     *
     * @param jsonArray jsonArray字符串
     * @return Listmap对象
     */
    public static List<Map<String, Object>> jsonArray2ListMap(String jsonArray) {
        List<Map<String, Object>> convertedListMap = null;
        try {
            convertedListMap = objectMapper.readValue(jsonArray, new TypeReference<List<Map<String, Object>>>() {
            });
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        return convertedListMap;
    }

    /**
     * json转map
     *
     * @param json json字符串
     * @return map对象
     */
    public static Map<String, Object> json2Map(String json) {
        Map<String, Object> convertedMap = null;
        try {
            convertedMap = objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {
            });
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        return convertedMap;
    }


    /**
     * listMap转json
     *
     * @param listMap listMap
     * @return
     */
    public static String listMap2JsonArray(List<Map<String, Object>> listMap) {
        String jsonStr = "";
        try {
            jsonStr = objectMapper.writeValueAsString(listMap);
        } catch (JsonProcessingException e) {
            LOG.error(e.getMessage());
        }
        return jsonStr;
    }
}
