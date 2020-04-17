package com.infitry.base.config;

import java.util.Collections;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.SessionTrackingMode;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.infitry.base.interceptor.MainInterceptor;

/**
 * @since 2020. 4. 7.
 * @author leesw
 * @mail leesw504@gmail.com
 * @description : WebMvc Configuration
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	
	@Bean
	public MainInterceptor mainInterceptor() {
		return new MainInterceptor();
	}
	
	/**
     * @since 2020. 4. 14.
     * @author leesw
     * @description : 인터셉터 추가.
     */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(mainInterceptor())
			.addPathPatterns("/**")
			.excludePathPatterns("/error/**")
			.excludePathPatterns("/login/**")
			.excludePathPatterns("/js/**")
			.excludePathPatterns("/css/**")
			.excludePathPatterns("/favicon/**")
			.excludePathPatterns("/images/**");
	}
	
    /**
     * @since 2020. 4. 14.
     * @author leesw
     * @description : JSESSION 제거, REDIS 세션을 사용하기 때문에 불필요
     */
    @Bean
    public ServletContextInitializer clearJsession() {
        return new ServletContextInitializer() {
            @Override
            public void onStartup(ServletContext servletContext) throws ServletException {
               servletContext.setSessionTrackingModes(Collections.singleton(SessionTrackingMode.COOKIE));
               SessionCookieConfig sessionCookieConfig = servletContext.getSessionCookieConfig();
               sessionCookieConfig.setHttpOnly(true);
            }
        };
    }
}
