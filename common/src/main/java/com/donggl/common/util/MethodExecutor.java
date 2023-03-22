package com.donggl.common.util;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import java.io.Serializable;
import java.util.function.Function;

/**
 * @ClassName MethodExecutor
 * @Description 工具类
 * @Author donggl
 * @Date 2023/3/22 21:24
 * @Version 1.0
 */
@Component
public class MethodExecutor {

    public static final int BASE_EXPIRE_AFTER_WRITE = 5 * 60;

    private MethodExecutor() {
    }

    public static <T, R extends Serializable> R executeWithRemoteCache(T t, Function<T, R> function, RedisUtil redisUtil, String key) {
        R result;
        if (null != redisUtil && !StringUtils.isEmpty(key)) {
            Serializable obj = redisUtil.getObj(key);
            if (null != obj) {
                return (R) obj;
            }
        }
        result = function.apply(t);
        if (null != redisUtil && null != result && !StringUtils.isEmpty(key)) {
            redisUtil.set(key, result, BASE_EXPIRE_AFTER_WRITE);
        }
        return result;
    }

//    public static <T, R> R executeWithRemoteHashCache(T t, Function<T, R> function, RedisUtil redisUtil, String key, Class<R> clazz) {
//        R result;
//        if (null != redisUtil && !StringUtils.isEmpty(key)) {
//            R hgetRlt = redisUtil.hget(key, clazz);
//            if (null != hgetRlt) {
//                return hgetRlt;
//            }
//        }
//        result = function.apply(t);
//        return result;
//    }
}


