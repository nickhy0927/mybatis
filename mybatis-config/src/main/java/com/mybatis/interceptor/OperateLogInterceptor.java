package com.mybatis.interceptor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.mybatis.common.utils.JsonMapper;
import com.mybatis.common.utils.RequestData;
import com.mybatis.config.optlog.entity.OptLog;
import com.mybatis.config.optlog.service.OptLogService;
import com.mybatis.core.orm.config.ReflectionUtils;
import com.mybatis.core.orm.config.SpringContextHolder;

public class OperateLogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            OperateLog operateLog = handlerMethod.getMethodAnnotation(OperateLog.class);
            if (operateLog != null) {
            	OptLog log = new OptLog();
            	Map<String, Object> parameterMap = RequestData.getParameterMap(request);
            	log.setClazz(handlerMethod.getMethod().getDeclaringClass().getName() + "." + handlerMethod.getMethod().getName());
            	log.setMessage(operateLog.message());
            	log.setOptType(operateLog.optType());
            	String methodName = handlerMethod.getMethod().getName();
            	log.setMethod(methodName);
            	Class<?> service = operateLog.service();
            	Class<?> clazz= Class.forName(service.getName());
            	Object object = SpringContextHolder.getBean(clazz);
            	Object newInstance = object.getClass().newInstance();
            	if (handlerMethod.getMethod().getName().toLowerCase().contains("delete")) {
					String[] ids = request.getParameter("id").split(",");
					List<Object> objects = new ArrayList<>();
					Class<?>[] parameterTypes = {String.class};
					Method method = ReflectionUtils.getDeclaredMethod(newInstance, methodName, parameterTypes);
					for (String id : ids) {
//						Object object = method.invoke(clazz, id);
//						objects.add(object);
					}
				} else {
				}
            	log.setData(JsonMapper.toJson(parameterMap));
            	OptLogService optLogService = SpringContextHolder.getBean(OptLogService.class);
            	optLogService.insert(log);
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

}
