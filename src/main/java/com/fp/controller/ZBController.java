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
import com.fp.service.IZBService;
import com.fp.util.StringUtil;
import com.fp.vo.ApiResult;

@Controller
@RequestMapping("/zb")//url:/模块/资源/{id}/细分/
public class ZBController {
	@Autowired
	private IZBService zbService;
	private static Logger logger = LoggerFactory.getLogger(ZBController.class);
	
	@ResponseBody
	@RequestMapping(value = "/count",
	method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	public ApiResult count(@RequestParam("type")String type,@RequestParam("openid")String openid){
		logger.debug("type:{},openid:{}",type,openid);
		if(!StringUtil.isEmpty(openid)&&!StringUtil.isEmpty(type)){
			return zbService.insertFpCount(openid, type);
		}
		return new ApiResult(ErrorCode.ERR_SYS_REQUEST_MISSING_PARAMETER);
	} 
	
	
	@ResponseBody
	@RequestMapping(value = "/count/q",
	method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
	public ApiResult countq(@RequestParam("type")String type,@RequestParam("openid")String openid){
		logger.debug("type:{},openid:{}",type,openid);
		if(!StringUtil.isEmpty(openid)&&!StringUtil.isEmpty(type)){
			return zbService.insertFpCount(openid, type);
		}
		return new ApiResult(ErrorCode.ERR_SYS_REQUEST_MISSING_PARAMETER);
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/live",
	method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	public ApiResult count(@RequestParam("time")long time,@RequestParam("openid")String openid){
		logger.debug("time:{},openid:{}",time,openid);
		if(time<0){
			return new ApiResult(ErrorCode.ERR_SYS_WRONG_PARAMETER);
		}
		if(!StringUtil.isEmpty(openid)){
			return zbService.insertFpActive(openid, time);
		}
		return new ApiResult(ErrorCode.ERR_SYS_REQUEST_MISSING_PARAMETER);
	}
	
}
