package com.fp.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;


public interface ZBDao {
	public  long queryFpCount(@Param("openid")String openid,@Param("type")String type);
	
	public  int insertFpCount(@Param("openid")String openid,@Param("type")String type);
	
	/*/**
	 * 总时长
	 * @param openid
	 * @return
	 
	public  long queryFpAllActive(@Param("openid")String openid);
	/**
	 * 平均时长
	 * @param openid
	 * @return
	 
	public  long queryFpAvgActive(@Param("openid")String openid);
	
	public  long queryFpMaxActive(@Param("openid")String openid);
	
	public  long queryFpMinActive(@Param("openid")String openid);*/
	
	public  int insertFpActive(@Param("openid")String openid,@Param("active")long active);
	
	/**
	 * 入参 openid
	 * 出参 avg
	 * max
	 * min
	 * all
	 * @param paramMap
	 */
	
	public void qActiveProcedure (Map<String,Object> paramMap);
}
