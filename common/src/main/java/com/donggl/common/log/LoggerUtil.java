package com.donggl.common.log;

import org.slf4j.Logger;

/**
 * @description: 日志打印工具类
 * @author donggl
 * @date 2022/11/10 14:40
 * @version 1.0
 */
public class LoggerUtil {

    /**
     * @description 打印生产环境观察日志，仅在生产环境打印
     * @param logger 日志对象
     * @param format 需要打印的日志信息
     * @param obj 替换的内容
     */
    public static void info(Logger logger, String format, Object... obj) {
        if (logger.isInfoEnabled()) {
            logger.info(format, obj);
        }
    }

    /**
     * @description 打印生产环境可预期的错误日志，仅在生产环境打印
     * @param logger 日志对象
     * @param format 日志内容
     * @param obj 替换的内容
     */
    public static void warn(Logger logger, String format, Object... obj) {
        if (logger.isWarnEnabled()) {
            logger.warn(format, obj);
        }
    }

    /**
     * @description 打印异常日志，仅在生产环境打印
     * @param logger 日志对象
     * @param format 需要打印的日志信息
     * @param obj 替换的内容
     */
    public static void error(Logger logger, String format, Object... obj) {
        logger.error(format, obj);
    }

    /**
     * @description 打印调试日志，仅在开发环境打印
     * @param logger 日志对象
     * @param format 需要打印的日志信息
     * @param obj 替换的内容
     */
    public static void debug(Logger logger, String format, Object... obj) {
        if (logger.isDebugEnabled()) {
            logger.debug(format, obj);
        }
    }
}