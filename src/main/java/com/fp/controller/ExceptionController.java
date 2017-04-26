package com.fp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fp.core.ErrorCode;
import com.fp.dto.ApiResult;
import com.fp.exception.ApplicationException;
import com.fp.exception.BaseException;

@ControllerAdvice
public class ExceptionController {
	private static Logger logger = LoggerFactory.getLogger(ExceptionController.class);

	@ExceptionHandler
	@RequestMapping(method={RequestMethod.POST,RequestMethod.GET},headers = "Accept=application/json;charset=UTF-8", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ApiResult handleGeneralException(HttpServletRequest request,HttpServletResponse response, Exception ex) {
		request.setAttribute("ex", ex.getMessage());
		if (ex instanceof ApplicationException) {
			logger.warn("Application exception!", ex);
			return new ApiResult(((BaseException) ex).getErrorCode(), ((BaseException) ex).getErrorMessage());
		} else if(ex instanceof InvalidPropertyException){
			logger.warn("Invalid Property Exception!", ex);
			return new ApiResult(ErrorCode.ERR_SYS_WRONG_PARAMETER);
		} else if(ex instanceof BadSqlGrammarException) {
			logger.error("Bad Sql Grammer exception: {}", ex.getCause().getMessage());
			String rootCause = ex.getCause().getMessage();
			if(rootCause.indexOf("Unknown column") > -1) {
				return new ApiResult(ErrorCode.ERR_SYS_WRONG_PARAMETER);
			}
		} else {
			logger.error("Unknown exception!", ex);
		}
		return new ApiResult(ErrorCode.ERR_SYS_INTERNAL_ERROR);
	}
}
