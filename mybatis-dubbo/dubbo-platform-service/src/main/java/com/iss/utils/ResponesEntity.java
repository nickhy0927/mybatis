package com.iss.utils;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ResponesEntity<T> implements Serializable{
    private int code;
    private String msg;

    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public static class ResultCode {
        public static final int SUCCESS = 200;
        public static final int FAIL = 201;
    }
}
