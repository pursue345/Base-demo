package com.donggl.common.log;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
//import org.apache.dubbo.rpc.RpcContext;
import org.slf4j.MDC;

/**
 * @description: MDC日志链路工具类
 * @author donggl
 * @date 2022/11/10 14:41
 * @version 1.0
 */
public class TraceUtil {

    /**
     * 链路追踪MDC
     */
    public static final String TRACE_ID = "TRACE_ID";

    public static final String REQUEST_ID = "REQUEST_ID";

    public static void initTrace(String traceId) {
        if (StrUtil.isBlank(traceId)) {
            traceId = generateTraceId();
        }
        setTraceId(traceId);
    }

    public static String generateTraceId() {
        return UUID.fastUUID().toString();
    }

    private static void setTraceId(String traceId) {
        MDC.put(TRACE_ID, traceId);
    }

    public static void clearTrace() {
        MDC.clear();
    }

    /**
     * <p>dubbo-消费端</p>
     * <p>1、从系统上下文取，没有就生成，并设置在系统上下文中</p>
     * <p>2、设置在dubbo-content中，方便提供端获取</p>
     *
     * @param context dubbo上下文
     */
    /*public static void putTraceIntoToDubboContent(RpcContext context) {
        String traceId = MDC.get(TRACE_ID);
        if (StrUtil.isBlank(traceId)) {
            traceId = generateTraceId();
            setTraceId(traceId);
        }
        context.setAttachment(TRACE_ID, traceId);
    }*/

    /**
     * <p>dubbo-提供端</p>
     * <p>1、从dubbo-content取，没有就生成</p>
     * <p>2、设置在系统上下文中</p>
     *
     * @param context dubbo上下文
     */
    /*public static void getTraceFromDubboContent(RpcContext context) {
        String traceId = context.getAttachment(TRACE_ID);
        if (StrUtil.isBlank(traceId)) {
            traceId = generateTraceId();
        }
        setTraceId(traceId);
    }*/
}