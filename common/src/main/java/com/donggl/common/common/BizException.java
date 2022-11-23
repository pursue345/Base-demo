package com.donggl.common.common;


import java.io.Serializable;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 业务异常
 * @author donggl
 * @date 2022/11/10 14:28
 * @version 1.0
 */
public class BizException extends RuntimeException {

    private IErrorCode errorCode;
    private Map<Serializable, Serializable> data;
    /**
     * 异常参数，用于传参给最终的错误说明
     */
    private Object[] args;

    public BizException(IErrorCode errorCode) {
        this(errorCode, new HashMap<>());
    }

    public BizException(IErrorCode errorCode, Map<Serializable, Serializable> data) {
        this(null, errorCode, data);
    }

    public BizException(Throwable throwable, IErrorCode errorCode) {
        this(throwable, errorCode, null);
    }


    public BizException(Throwable throwable, IErrorCode errorCode, Map<Serializable, Serializable> data) {
        super(errorCode == null ? "" : errorCode.getMessage(), throwable);
        if (null != data) {
            this.data = data;
        } else {
            this.data = new HashMap<>();
        }
        this.errorCode = errorCode;
    }


    /**
     * 带说明 异常
     *
     * @param errorCode
     * @param args
     */
    public BizException(IErrorCode errorCode, Object... args) {
        this(null, errorCode, new HashMap<>(), args);
    }

    public BizException(Throwable throwable, IErrorCode errorCode, Map<Serializable, Serializable> data, Object... args) {
        super(errorCode == null ? "" : (args != null ? MessageFormat.format(errorCode.getMessage(), args) : errorCode.getMessage()),
                throwable);
        this.args = args;
        if (null != data) {
            this.data = data;
        } else {
            this.data = new HashMap<>();
        }
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        if (args != null) {
            return MessageFormat.format(errorCode.getMessage(), args);
        }
        return errorCode.getMessage();
    }

    public static BizException wrap(Throwable exception, IErrorCode errorCode) {
        if (exception instanceof BizException) {
            BizException se = (BizException) exception;
            if (errorCode != null && errorCode != se.getErrorCode()) {
                return new BizException(errorCode, se.getData());
            }
            return se;
        } else {
            return new BizException(exception, errorCode);
        }
    }

    public static BizException wrap(Throwable exception) {
        return wrap(exception, null);
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }

    public Map<Serializable, Serializable> getData() {
        return data;
    }

    public void setData(Map<Serializable, Serializable> data) {
        this.data = data;
    }

    public BizException set(Serializable key, Serializable value) {
        this.data.put(key, value);
        return this;
    }
}
