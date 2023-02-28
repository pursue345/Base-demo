package com.donggl.common.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName TestUtil
 * @Description TODO
 * @Author donggl
 * @Date 2023/2/27 22:37
 * @Version 1.0
 */
public class TestUtil {

    /**
     * @description: 异步线程执行
     * @author donggl
     * @date 2023/2/27 22:38
     * @version 1.0
     */
    public static void test() throws InterruptedException {
        HashMap<String, List<String>> map = new HashMap<>();
        int i= map.entrySet().size();
        CountDownLatch countDownLatch=new CountDownLatch(i);
        for (String str:map.keySet()) {
            Map<String, List<String>> paramMap=new ConcurrentHashMap<>();
            paramMap.put(str,map.get(str));
            // 启用多线程
            CompletableFuture.runAsync(() -> {
//                asyncExecuteControl(paramMap, taskType);
                // 线程执行结束则countDown，倒数一次
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
    }
}
