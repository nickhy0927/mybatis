package com.mybatis.core.orm.core.exception;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

public class OutputJson implements Serializable {

    /**
     * 返回客户端统一格式，包括状态码，提示信息，以及业务数据
     */
    private static final long serialVersionUID = 1L;
    //状态码
    private int code;
    //必要的提示信息
    private String msg;
    //业务数据
    private Object data;

    public OutputJson(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String toString() {
        if (null == this.data) {
            this.setData(new Object());
        }
        return JSON.toJSONString(this);
    }
}
