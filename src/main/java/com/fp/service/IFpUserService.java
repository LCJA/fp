package com.fp.service;

import com.fp.vo.ApiResult;

public interface IFpUserService {
	
	
	public  ApiResult register(String userName,String passWord,long roleId);
	
	public  ApiResult login(String userName,String passWord);
	
	public 	ApiResult Modpasswd(String userName,String oldPassWord,String newPassWord);
}
