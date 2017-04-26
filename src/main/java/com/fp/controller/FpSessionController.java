package com.fp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fp.core.ErrorCode;
import com.fp.dto.ApiResult;
import com.fp.service.IFpUserService;
import com.fp.service.impl.FpUserServiceImpl;
import com.fp.util.StringUtil;

@Controller
@RequestMapping("/session")//url:/模块/资源/{id}/细分/
public class FpSessionController {
	private static Logger logger = LoggerFactory.getLogger(FpSessionController.class);
	@Autowired
	private IFpUserService fpUserservice;
	
	//登录
	@ResponseBody
	@RequestMapping(value = "",
	method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	public ApiResult Login(@RequestParam("userName")String userName,
			@RequestParam("passWord")String passWord){
		logger.debug("Login-----userName:{},passWord:{}",userName,passWord);
		if(!StringUtil.isEmpty(userName)&&!StringUtil.isEmpty(passWord)){
			return  fpUserservice.login(userName, passWord);
		}
		
		return new ApiResult(ErrorCode.ERR_SYS_REQUEST_MISSING_PARAMETER);
	}
}
