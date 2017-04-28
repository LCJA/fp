package com.fp.dao;

import org.apache.ibatis.annotations.Param;

import com.fp.model.StaticData;

public interface StaticDataDao {

	public int insertStaticData(StaticData staticdata);
	
	public StaticData queryStaticDataByKey(@Param("dataKey")String dataKey);
	
}
