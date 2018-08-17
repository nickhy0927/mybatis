package com.mybatis.core.orm.controller;

import com.mybatis.core.orm.core.exception.ReturnFormat;

public abstract class BaseController {

    protected String retContent(int code, Object data) {
        return ReturnFormat.retParam(code, data);
    }
}
