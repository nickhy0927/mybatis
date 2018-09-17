package com.news.index;

import com.news.SystemConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    private SystemConfig systemConfig;

    private static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @GetMapping(value = "/index.do")
    public String index() {
        logger.info("登录地址是：" + systemConfig.getLoginPage());
        return "index";
    }
}
