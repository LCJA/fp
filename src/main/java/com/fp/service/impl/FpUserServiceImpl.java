package com.fp.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fp.core.ErrorCode;
import com.fp.dao.FpUserDao;
import com.fp.model.FpUser;
import com.fp.service.IFpUserService;
import com.fp.util.CoderAndDecoder;
import com.fp.util.StringUtil;
import com.fp.vo.ApiResult;
/**
 * 常量
 * @author LCJA
 *
 */
@Service
public class FpUserServiceImpl implements IFpUserService{
	
	private static Logger logger = LoggerFactory.getLogger(FpUserServiceImpl.class);
	
	@Autowired
	private FpUserDao fpUserDao;

	@Override
	public ApiResult register(String userName, String passWord, long roleId) {
		FpUser fpuesrBean = new FpUser();
		userName=new String(CoderAndDecoder.decrypt(userName.getBytes(), "fp"));
		fpuesrBean.setUserName(userName);
		fpuesrBean.setPassWord(CoderAndDecoder.getMD5(passWord));
		fpuesrBean.setState(1);
		fpuesrBean.setRoleId(roleId);
		int rtint = fpUserDao.saveFpUser(fpuesrBean);
		if(rtint>0){
			return  new ApiResult(ErrorCode.SUCCESS);
		}
		return  new ApiResult(ErrorCode.ERR_SYS_INTERNAL_ERROR);
	}
	
	
	@Override
	public  ApiResult login(String userName,String passWord){
		userName=new String(CoderAndDecoder.decrypt(userName.getBytes(), "fp"));
		String md5Password= CoderAndDecoder.getMD5(passWord);
		FpUser fpuesrBean = fpUserDao.queryFpuserByUserId(userName);
		if(fpuesrBean!=null){
			String realPassWord = fpuesrBean.getPassWord();
			if(!StringUtil.isEmpty(realPassWord)){
				if(md5Password.equals(realPassWord)){
					return  new ApiResult(ErrorCode.SUCCESS);
				}else{
					return  new ApiResult(ErrorCode.ERR_USER_WRONG_PWD);
				}
			}
		}else{
			return  new ApiResult(ErrorCode.ERR_USER_NOT_EXIST);
		}
		return  new ApiResult(ErrorCode.ERR_SYS_INTERNAL_ERROR);
	}


	@Override
	public ApiResult Modpasswd(String userName, String oldPassWord, String newPassWord) {
		userName=new String(CoderAndDecoder.decrypt(userName.getBytes(), "fp"));
		String md5OldPassword= CoderAndDecoder.getMD5(oldPassWord);
		FpUser fpuesrBean = fpUserDao.queryFpuserByUserId(userName);
		if(fpuesrBean!=null){
			String realPassWord = fpuesrBean.getPassWord();
			if(!StringUtil.isEmpty(realPassWord)){
				if(md5OldPassword.equals(realPassWord)){
					//修改密码
					String md5NewPassword= CoderAndDecoder.getMD5(newPassWord);
					FpUser fpuser = new FpUser();
					fpuser.setUserName(userName);
					fpuser.setPassWord(md5NewPassword);
					int  rtint = fpUserDao.updateFpUser(fpuser);
						if(rtint>0){
							return  new ApiResult(ErrorCode.SUCCESS);
						}
						logger.debug("have not update password username:{},newpassword:{},oldpassword:{}",userName,oldPassWord,newPassWord);
						return  new ApiResult(ErrorCode.ERR_SYS_INTERNAL_ERROR);
				}else{
					return  new ApiResult(ErrorCode.ERR_USER_WRONG_PWD);
				}
			}
		}else{
			return  new ApiResult(ErrorCode.ERR_USER_NOT_EXIST);
		}
		return new ApiResult(ErrorCode.ERR_SYS_INTERNAL_ERROR);
	}
	
	
	
	
}
