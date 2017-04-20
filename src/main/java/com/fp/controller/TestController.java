package com.fp.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * localhost:8080/seekang/test
 *
 */
@Controller
@RequestMapping("/")
public class TestController {
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@ResponseBody
	@RequestMapping(value = "/test", method = RequestMethod.GET, headers = "Accept=application/json;charset=UTF-8", produces = { "application/json;charset=UTF-8" })
	public String test(HttpServletRequest request) {
		logger.info("Received the request from remote {}", request.getRemoteHost());
		return "Hello";
	}
}
