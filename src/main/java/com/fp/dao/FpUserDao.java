package com.fp.dao;

import org.apache.ibatis.annotations.Param;

import com.fp.model.FpUser;

public interface FpUserDao {

	/**
	 * 保存用户信息
	 * @param fpuser
	 * @return
	 */
	public int saveFpUser(FpUser fpuser);
	
	/**
	 * 根据userName查询用户信息
	 * @param userName
	 * @return
	 */
	public FpUser queryFpuserByUserId(@Param("userName")String userName);
	
	public int updateFpUser(FpUser fpuser);
}
