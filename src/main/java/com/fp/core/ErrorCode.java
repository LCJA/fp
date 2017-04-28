package com.fp.core;

public enum ErrorCode {
	SUCCESS("0000", "成功"),
	ERR_SYS_INTERNAL_ERROR("1000", "系统内部错误"),
	ERR_SYS_RESPONSE_MISSING_PARAMETER("1006", "缺少响应参数"),
	ERR_SYS_REQUEST_MISSING_PARAMETER("1007", "缺少输入参数"),
	ERR_SYS_WRONG_PARAMETER("1008", "参数错误"),
	ERR_USER_NOT_EXIST("2001", "用户不存在"),
	ERR_USER_WRONG_PWD("2002", "密码不正确");
	
	private String errCode;
	private String errMsg;
	
	private ErrorCode(String errCode, String errMsg) {
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

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
	
	
}
