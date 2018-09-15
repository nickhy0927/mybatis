package com.core.utils;

public class JSONResult {

    private int status;
    private String msg;
    private Object data;

    public JSONResult(Object data, int status) {
        this.data = data;
        this.status = status;
        this.msg = "ok";
    }

    private static class ResultCode {
        public static int SUCCESS = 200;
        public static int ERROR = 500;
        public static int EXCEPTION = 501;
        public static int NO_LOGIN = 501;

    }

    public static JSONResult success(Object data) {
        return new JSONResult(data, ResultCode.SUCCESS);
    }

    public static JSONResult error(Object data) {
        return new JSONResult(data, ResultCode.ERROR);
    }

    public static JSONResult exception(Object data) {
        return new JSONResult(data, ResultCode.EXCEPTION);
    }

    public static JSONResult noLogin(Object data) {
        return new JSONResult(data, ResultCode.NO_LOGIN);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
