package com.infitry.base.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.infitry.base.component.BlogComponent;


/**
 * @since 2020. 4. 7.
 * @author leesw
 * @mail leesw504@gmail.com
 * @description : 메인 인터셉터
 */
public class MainInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(MainInterceptor.class);
	
	@Autowired
	private BlogComponent blogComponent;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//세션에서 로그인아이디 추출
//		String loginId = (String) request.getSession().getAttribute("loginId");
//		
//		//로그인 되지 않은 사용자는 로그인페이지로 이동
//		if (StringUtils.isEmpty(loginId)) {
//			response.sendRedirect("/login");
//			return false;
//		}
		
		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (!isAjax(request)) {
			//카테고리 목록을 불러온다.
			modelAndView.addObject("categoryList", blogComponent.getCategoryList());
		}
		
//		super.postHandle(request, response, handler, modelAndView);
	}
	
	
	/**
	 * @since 2020. 4. 8.
	 * @author leesw
	 * @description : Axios, ajax 요청 구분
	 */
	private boolean isAjax(HttpServletRequest request) {
		boolean isAjax = false;
		//Ajax요청 판별
		if ("XMLHttpRequest".equals(request.getHeader("x-requested-with"))) {
			isAjax = true;
		}
		
		//Axios요청 판별
		/*
		 * Axios 요청은 ajax와 다르게 구분할 수 있는 header 값이 존재하지 않음. 
		 * Axios 요청 시 헤더 값에 requestType: axios라는 값을 추가 하여 해결.
		 */
		if ("axios".equals(request.getHeader("requestType"))) {
			isAjax = true;
		}
		
		return isAjax;
	}
}
