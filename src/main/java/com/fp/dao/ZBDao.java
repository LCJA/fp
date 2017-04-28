package com.fp.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.fp.model.StaticData;
import com.fp.model.Vension;


public interface ZBDao {
	public  Long queryFpCount(@Param("openid")String openid,@Param("type")String type);
	
	public  int saveFpCount(@Param("openid")String openid,@Param("type")String type,@Param("count")long count);
	
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
	
	public int insertVension(Vension vension);
	
	public Vension queryNewVension();
	
}
