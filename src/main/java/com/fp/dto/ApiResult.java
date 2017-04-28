package com.fp.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fp.core.ErrorCode;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ApiResult {
	private String errCode;
	private String errMsg;
	private String date;
	private long timestamp;
	private Object data;

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public ApiResult() {
		this.errCode = ErrorCode.SUCCESS.getErrCode();
		this.timestamp = new Date().getTime();
		this.date=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}
	
	public ApiResult(ErrorCode errorCode) {
		this.errCode = errorCode.getErrCode();
		this.errMsg = errorCode.getErrMsg();
		this.timestamp = new Date().getTime();
		this.date=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}
	
	public ApiResult(ErrorCode errorCode, String errMsg) {
		this.errCode = errorCode.getErrCode();
		this.errMsg = errMsg;
		this.timestamp = new Date().getTime();
		this.date=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}
	
	public ApiResult(String errCode, String errMsg) {
		this.errCode = errCode;
		this.errMsg = errMsg;
		this.timestamp = new Date().getTime();
		this.date=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
}
