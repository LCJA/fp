package com.fp.service;

import com.fp.vo.ApiResult;

public interface IZBService {
	
		public  ApiResult insertFpCount(String openid,String type);
		
		public  ApiResult insertFpActive(String openid,long active);
		
		public  ApiResult getFpCount(String openid,String type);
		
		public  ApiResult getFpActive(String openid);
}
