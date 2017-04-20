package com.fp.dao;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fp.model.FpUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-db.xml"})
public class ZBDaoTest {

	@Autowired
	private ZBDao zbdao;
	
	@Test
	public  void saveTest(){
		Map paramMap = new HashMap();
		paramMap.put("i_openid", "dfg");
		paramMap.put("v_avg", null);
		paramMap.put("v_max", null);
		paramMap.put("v_min", null);
		paramMap.put("v_all", null);
		zbdao.qActiveProcedure(paramMap);
		System.out.println(paramMap.get("v_avg"));
		System.out.println(paramMap.get("v_max"));
		System.out.println(paramMap.get("v_min"));
		System.out.println(paramMap.get("v_all"));
		
		
	}

}
