package com.fp.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fp.model.FpUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-db.xml"})
public class FpUserDaoTest {

	
	@Autowired
	private FpUserDao fpUserDao;
	
	@Test
	public  void saveTest(){
		FpUser fpuser = new FpUser();
		fpuser.setUserName("abcd2");
		fpuser.setPassWord("123456");
		int rtflg = fpUserDao.saveFpUser(fpuser);
		System.out.println("rtflg"+rtflg);
		System.out.println(fpuser.getUserId());
		
	}
	
	@Test
	public  void queryTest(){
		FpUser fpuser = fpUserDao.queryFpuserByUserId("abcd2");
		System.out.println(fpuser.getPassWord());
		
	}
	
}
