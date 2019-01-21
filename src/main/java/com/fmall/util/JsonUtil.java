package com.fmall.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;

import java.text.SimpleDateFormat;

/**
 * Created by 冯晓 on 2019/1/13.
 */

@Slf4j
public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();
    static {
        /**
         * 序列化相关配置
         */
        // 对象的全部字段全部列入
        objectMapper.setSerializationInclusion(Inclusion.ALWAYS);

        // 取消默认转换timestamps形式
        objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);

        // 忽略空Bean转json的错误
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);

        // 所有的日期格式都统一为以下的格式，即yyyy-MM-dd HH:mm:ss
        objectMapper.setDateFormat(new SimpleDateFormat(DateTimeUtil.STANDARD_FORMAT));

        /**
         * 反序列化相关配置
         */
        // 忽略 在json字符串中存在，但是在java对象中不存在对应属性的情况，防止错误
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> String obj2String(T obj){
        if (obj == null){
            return null;
        }
        try{
            return obj instanceof String ? (String)obj : objectMapper.writeValueAsString(obj);
        } catch (Exception e){
            log.warn("Parse object to String error", e);
            return null;
        }
    }

    public static <T> String obj2StringPretty(T obj){
        if (obj == null){
            return null;
        }
        try{
            return obj instanceof String ? (String)obj : objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e){
            log.warn("Parse object to String error", e);
            return null;
        }
    }

    // <T> 泛型方法
    public static <T> T string2Obj(String str, Class clazz){
        if (StringUtils.isEmpty(str) || clazz == null){
            return null;
        }

        try {
            return clazz.equals(String.class) ? (T)str : (T)objectMapper.readValue(str, clazz);
        } catch (Exception e) {
            log.warn("Parse str to obj error", e);
            return null;
        }
    }

    /**
     * 多类型反序列化，对于List<User>这种
     * @param str
     * @param typeReference
     * @param <T>
     * @return
     */
    public static <T> T string2Obj(String str, TypeReference<T> typeReference){
        if (StringUtils.isEmpty(str) || typeReference == null){
            return null;
        }

        try {
            return (T)(typeReference.getType().equals(String.class) ? str : (T)objectMapper.readValue(str, typeReference));
        } catch (Exception e) {
            log.warn("Parse str to obj error", e);
            return null;
        }
    }

    /**
     * 多类型反序列化，对于List<User>，Map<User, Cart>, Set<User>
     * @param str
     * @param collectionClass
     * @param elementClasses
     * @param <T>
     * @return
     */
    public static <T> T string2Obj(String str, Class<?> collectionClass, Class<?>... elementClasses){
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
        try {
            return objectMapper.readValue(str, javaType);
        } catch (Exception e) {
            log.warn("Parse str to obj error", e);
            return null;
        }
    }

    public static void main(String[] args) {

        /**
         * 【Jackson封装JsonUtil及调试】的测试代码
         */
        /*
        User u1 = new User();
        u1.setId(1);
        u1.setEmail("dev_fengxiao@163.com");

        User u2 = new User();
        u2.setId(2);
        u2.setEmail("dev_fengxiao2@163.com");


        String user1Json = JsonUtil.obj2String(u1);
        String user1JsonPretty = JsonUtil.obj2StringPretty(u1);
        log.info("user1Json:{}", user1Json);
        log.info("user1JsonPretty:{}", user1JsonPretty);

        User user = JsonUtil.string2Obj(user1Json, User.class);

        List<User> userList = Lists.newArrayList();
        userList.add(u1);
        userList.add(u2);
        String userListStr = JsonUtil.obj2StringPretty(userList);
        log.info("___________________________");
        log.info(userListStr);

        List<User> userList1 = JsonUtil.string2Obj(userListStr, new TypeReference<List<User>>() {
        });

        List<User> userList2 = JsonUtil.string2Obj(userListStr, List.class, User.class);

        System.out.println("program is end");
        */


        /**
         * 【Jackson ObjectMapper源码封装】的测试代码
         */
        /*
        User user = new User();
        user.setId(1);
        user.setCreateTime(new Date());
        user.setEmail("dev_fengxiao@163.com");
        String userPretty = JsonUtil.obj2StringPretty(user);
        log.info("user1Json:{}", userPretty);
        */

        /*
        TestPojo testPojo = new TestPojo();
        String testPojoJson = JsonUtil.obj2StringPretty(testPojo);
        log.info("testPojoJson:{}", testPojoJson);
        */

        /*
        TestPojo testPojo2 = new TestPojo();
        testPojo2.setId(666);
        testPojo2.setName("fulin");
        // {"name" : "fulin","id" : 666}

        String json = "{\"name\" : \"fulin\",\"color\" : \"red\",\"id\" : 666}";
        TestPojo testPojoObj = JsonUtil.string2Obj(json, TestPojo.class);
        log.info("end");
        */

    }
}
