package com.fp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fp.core.ErrorCode;
import com.fp.service.IFpUserService;
import com.fp.service.impl.FpUserServiceImpl;
import com.fp.util.StringUtil;
import com.fp.vo.ApiResult;

@Controller
@RequestMapping("/user")//url:/模块/资源/{id}/细分/
public class FpUSerController {
	private static Logger logger = LoggerFactory.getLogger(FpUserServiceImpl.class);
	@Autowired
	private IFpUserService fpUserservice;
	//注册
	@ResponseBody
	@RequestMapping(value = "/",
	method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	public ApiResult Register(@RequestParam("userName")String userName,
			@RequestParam("passWord")String PassWord){
		logger.debug("Register-----userName:{},Password:{}",userName,PassWord);
		if(!StringUtil.isEmpty(userName)&&!StringUtil.isEmpty(PassWord)){
			return fpUserservice.register(userName, PassWord, 0);
		}
		return new ApiResult(ErrorCode.ERR_SYS_REQUEST_MISSING_PARAMETER);
	}
	
	//修改
	@RequestMapping(value = "/",
			method = RequestMethod.PUT,produces = {"application/json;charset=UTF-8"})
	public ApiResult modifyPassword(@RequestParam("userName")String userName,
			@RequestParam("oldPassWord")String oldPassWord,
			@RequestParam("newPassWord")String newPassWord){
			if(StringUtil.isEmpty(userName)&&StringUtil.isEmpty(oldPassWord)&&StringUtil.isEmpty(newPassWord)){
				return fpUserservice.Modpasswd(userName, oldPassWord, newPassWord);
			}
		return new ApiResult(ErrorCode.ERR_SYS_REQUEST_MISSING_PARAMETER);
	}

}
