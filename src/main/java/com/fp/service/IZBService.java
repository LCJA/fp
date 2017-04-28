package com.fp.service;

import java.io.InputStream;

import com.fp.dto.ApiResult;

public interface IZBService {
	
		public  ApiResult insertFpCount(String openid,String type);
		
		public  ApiResult insertFpActive(String openid,long active);
		
		public  ApiResult getFpCount(String openid,String type);
		
		public  ApiResult getFpActive(String openid);
		
		public  ApiResult getNewVension();
		
		public  ApiResult saveVension(String vension,String context);
		
		public  ApiResult getDownLoad();
		
		public byte[] downUrl();
}
