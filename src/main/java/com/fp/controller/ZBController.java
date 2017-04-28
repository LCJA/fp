package com.fp.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fp.core.ErrorCode;
import com.fp.dto.ApiResult;
import com.fp.service.IZBService;
import com.fp.util.StringUtil;

@Controller
@RequestMapping("/zb")//url:/模块/资源/{id}/细分/
public class ZBController {
	@Autowired
	private IZBService zbService;
	private static Logger logger = LoggerFactory.getLogger(ZBController.class);
	
	@ResponseBody
	@RequestMapping(value = "/count",
	method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	public ApiResult count(@RequestParam("openid")String openid,@RequestParam("type")String type){
		logger.debug("type:{},openid:{}",type,openid);
		if(!StringUtil.isEmpty(openid)&&!StringUtil.isEmpty(type)){
			return zbService.insertFpCount(openid, type);
		}
		return new ApiResult(ErrorCode.ERR_SYS_REQUEST_MISSING_PARAMETER);
	} 
	
	
	@ResponseBody
	@RequestMapping(value = "/count",
	method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
	public ApiResult countq(@RequestParam(value="type",required=false)String type,@RequestParam(value="openid",required=false)String openid){
		logger.debug("type:{},openid:{}",type,openid);
		if(!StringUtil.isEmpty(openid)||!StringUtil.isEmpty(type)){
			return zbService.getFpCount(openid, type);
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
	

	@ResponseBody
	@RequestMapping(value = "/live",
	method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
	public ApiResult liveq(@RequestParam("openid")String openid){
		logger.debug("openid:{}",openid);
		if(!StringUtil.isEmpty(openid)){
			return zbService.getFpActive(openid);
		}
		return new ApiResult(ErrorCode.ERR_SYS_REQUEST_MISSING_PARAMETER);
	}
	@ResponseBody
	@RequestMapping(value = "/vension",
	method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
	public ApiResult getVension(){
			return zbService.getNewVension();
	}
	
	@ResponseBody
	@RequestMapping(value = "/vension",
	method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	public ApiResult saveVensionContext(@RequestParam("vension")String vension,@RequestParam("context")String context){
		if(!StringUtil.isEmpty(vension)&&!StringUtil.isEmpty(context)){
			return zbService.saveVension(vension, context);
		}
		return new ApiResult(ErrorCode.ERR_SYS_REQUEST_MISSING_PARAMETER);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/apk",
	method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
	public ApiResult getDownLoad(){
		return zbService.getDownLoad();
	}
	
	@RequestMapping(value = "/down",
	method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
	public ResponseEntity<byte[]>  getDownUrl(){
		byte[] body = zbService.downUrl();
		HttpHeaders headers = new HttpHeaders();
	    //headers.add("Content-Disposition", "attchement;filename=zhuangbidashi.apk");
	    headers.add("content-type", "application/vnd.android.package-archive");
	    HttpStatus statusCode = HttpStatus.OK;
	    ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, statusCode);
	    return entity;
	}

	
}
