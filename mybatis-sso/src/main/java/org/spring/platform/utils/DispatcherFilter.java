package org.spring.platform.utils;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.common.utils.StaticResources;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import net.sf.json.JSONObject;

/**
 * 拦截所有的请求
 * 
 * @author yuanhuangd
 *
 */
public class DispatcherFilter extends HandlerInterceptorAdapter {

	private final Logger LOG = LoggerFactory.getLogger(DispatcherFilter.class);

	public static final String LAST_PAGE = "com.alibaba.lastPage";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Map<String, Object> dataToMap = RequestData.getRequestDataToMap(request);
		LOG.debug(JSONObject.fromObject(dataToMap).toString());
		StaticResources instance = StaticResources.getStaticResourcesInstance();
		List<String> urls = instance.getUrls();
		if (dataToMap.get("SESSIONID") != null) {
			dataToMap.remove("SESSIONID");
		}
		System.out.println(request.getRequestURL());
		if (dataToMap != null && !dataToMap.isEmpty()) {
			if (dataToMap.get("token") == null) {
				return validateUser(request, response, instance, urls, dataToMap);
			}
			return true;
		} else {
			return validateUser(request, response, instance, urls, dataToMap);
		}
	}

	private boolean validateUser(HttpServletRequest request, HttpServletResponse response, StaticResources instance,
			List<String> urls, Map<String, Object> dataToMap) throws IOException {
		String url = request.getRequestURI();
		for (String u : urls) {
			if (url.contains(u)) {
				return true;
			}
		}
		String SESSIONID = UUID.randomUUID().toString().replace("-", "").toUpperCase();
		String params = "SESSIONID=" + SESSIONID + "&url=" + dataToMap.get("url");
		HttpSession session = request.getSession();
		session.setAttribute("url", url);
		response.sendRedirect(instance.getProjectName() + "/user/login.do?" + params);
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		LOG.debug("你正在请求的地址是：" + request.getRequestURI());
		if (modelAndView != null) { // 加入当前时间
			modelAndView.addObject("SESSIONID", UUID.randomUUID().toString().replace("-", "").toUpperCase());
		}
	}

}
