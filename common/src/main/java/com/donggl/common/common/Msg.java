package com.donggl.common.common;

import java.io.Serializable;
/**
 * @description: 返回对象Msg
 * @author donggl
 * @date 2022/11/10 14:30
 * @version 1.0
 */
public class Msg<T extends Serializable> implements Serializable {
    private int code;
    private String msg;
    private T data;
    public int getCode() {
        return this.code;
    }
    public String getMsg() {
        return this.msg;
    }

    public T getData() {
        return data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <S extends Serializable> Builder<S> success(S data) {
        return new Builder<S>().data(data).error(ErrorCode.SYSTEM.SUCCESS);
    }

    public static <S extends Serializable> Builder<S> error(IErrorCode errorCode) {
        return new Builder<S>().error(errorCode);
    }

    /**
     * 是否成功
     *
     * @return boolean
     */
    public boolean isSuccess() {
        return this.code == 0;
    }

    public Msg(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private Msg(Builder<T> b) {
        this.msg = b.msg;
        this.data = b.data;
        this.code = b.code;
    }

    private Msg() {
    }

    public static class Builder<K extends Serializable> {
        private K data;
        private int code;
        private String msg;

        public Builder<K> data(K data) {
            this.data = data;
            return this;
        }

        public Builder<K> error(IErrorCode errorCode) {
            this.code = errorCode.getCode();
            this.msg = errorCode.getMessage();
            return this;
        }

        public Builder<K> message(String message) {
            this.msg = message;
            return this;
        }

        public Msg<K> build() {
            return new Msg<>(this);
        }
    }
}