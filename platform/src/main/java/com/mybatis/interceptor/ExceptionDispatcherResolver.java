package com.mybatis.interceptor;

import com.mybatis.common.utils.MessageObject;
import com.mybatis.core.orm.core.exception.ServiceException;
import com.mybatis.system.exceptionlog.entity.ExceptionLog;
import com.mybatis.system.exceptionlog.service.ExceptionLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Method;

/**
 * 错误日志监控拦截器
 *
 * @author Hyuan
 */
public class ExceptionDispatcherResolver implements HandlerExceptionResolver, Ordered {

    final private static Logger LOGGER = LoggerFactory.getLogger(ExceptionDispatcherResolver.class);

    private int order = Ordered.HIGHEST_PRECEDENCE;

    @Autowired
    private ExceptionLogService exceptionLogService;

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) {
        ModelAndView view = null;
        MessageObject messageObject = MessageObject.getDefaultMessageObjectInstance();
        String uri = request.getRequestURI().toLowerCase();
        String packageMethodName;
        if (object instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) object;
            Method method = handlerMethod.getMethod();
            String name = method.getDeclaringClass().getName();
            packageMethodName = name + "." + method.getName();
            LOGGER.info(packageMethodName);
            OperateLog log = method.getAnnotation(OperateLog.class);
            ExceptionLog exceptionLog = new ExceptionLog();
            if (exception instanceof ServiceException)
                exceptionLog.setExceptionType(ExceptionLog.ExceptionType.BUSINESS);
            else exceptionLog.setExceptionType(ExceptionLog.ExceptionType.SYSTEM);
            exceptionLog.setException(exception.getMessage());
            if (exception instanceof ServiceException)
                exceptionLog.setExceptionType(ExceptionLog.ExceptionType.BUSINESS);
            if (log != null)
                exceptionLog.setMessage(log.message());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            exception.printStackTrace(new PrintStream(baos));
            String exceptionStr = baos.toString();
            exceptionLog.setExceptionStr(exceptionStr);
            exceptionLog.setMethodName(name + "." + method.getName());
            exceptionLogService.insert(exceptionLog);
        }
        System.out.println("请求的uri地址是：" + uri);
        if (uri.lastIndexOf(".json") > 0) {
            exception.printStackTrace();
            messageObject.error("系统出现异常，操作失败");
            try {
                messageObject.returnData(response, messageObject);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("你发送的是.do的请求");
            System.out.println(exception.getMessage());
            exception.printStackTrace();
            messageObject.error("系统产生异常：" + exception.getMessage());
            view = new ModelAndView("error/error-page");
        }
        return view;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public int getOrder() {
        return order;
    }

}
