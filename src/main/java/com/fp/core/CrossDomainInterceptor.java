/**
 * 
 */
package com.fp.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CrossDomainInterceptor extends HandlerInterceptorAdapter {
	private static Logger logger = LoggerFactory.getLogger(CrossDomainInterceptor.class);
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {		
		String domain = request.getHeader("ORIGIN");
		logger.info("ORIGIN: {}", domain);
		response.setHeader("Access-Control-Allow-Origin", domain);
		response.setHeader("Access-Control-Allow-Credentials", "true");		
		
		return true;
	}
}
