package com.fp.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fp.controller.ZBController;
import com.fp.core.ErrorCode;
import com.fp.dao.StaticDataDao;
import com.fp.dao.ZBDao;
import com.fp.dto.ApiResult;
import com.fp.dto.ZBActDto;
import com.fp.dto.ZBBtnDto;
import com.fp.model.StaticData;
import com.fp.model.Vension;
import com.fp.service.IZBService;
import com.fp.util.StringUtil;

@Service
public class ZBServiceImpl implements IZBService{
	@Autowired
	private ZBDao zbDao;
	@Autowired
	private StaticDataDao staticdatadao; 
	
	private static Logger logger = LoggerFactory.getLogger(ZBController.class);
	@Override
	public ApiResult insertFpCount(String openid, String type) {
		/*int flag = 0;
		Map<String,String>typeMap = StringUtil.turnMap(type);
		if(typeMap==null){
			return new ApiResult(ErrorCode.ERR_SYS_WRONG_PARAMETER);
		}
		Set<String> set = typeMap.keySet();
		for(String s: set){
			flag+= zbDao.saveFpCount(openid, s,typeMap.get(s));
		}
		if(flag==typeMap.size()){
			return new ApiResult(ErrorCode.SUCCESS);
		}*/
		int flag = zbDao.saveFpCount(openid, type, 1L);
		if(flag>0){
			return new ApiResult(ErrorCode.SUCCESS);
		}
		return new ApiResult(ErrorCode.ERR_SYS_INTERNAL_ERROR);
	}

	@Override
	public ApiResult insertFpActive(String openid, long active) {
		int rtint = zbDao.insertFpActive(openid, active);
		if(rtint>0){
			return new ApiResult(ErrorCode.SUCCESS);
		}
		return new ApiResult(ErrorCode.ERR_SYS_INTERNAL_ERROR);
	}

	@Override
	public ApiResult getFpCount(String openid, String type) {
		Long count = zbDao.queryFpCount(openid, type);
		logger.debug("count:{}",count);
		 ZBBtnDto	zbdto = new ZBBtnDto();
		zbdto.setOpenid(openid);
		zbdto.setType(type);
		if(count!=null){
			zbdto.setCount(count);
		}else{
			zbdto.setCount(-1L);
		}
		ApiResult apiresult = new ApiResult(ErrorCode.SUCCESS);
		apiresult.setData(zbdto);
		return apiresult;
	}

	@Override
	public ApiResult getFpActive(String openid) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("i_openid", openid);
		param.put("v_avg", null);
		param.put("v_max", null);
		param.put("v_min", null);
		param.put("v_all", null);
		zbDao.qActiveProcedure(param);
		ZBActDto zbActDto = new ZBActDto();
		zbActDto.setOpenid(openid);
		zbActDto.setAvgactive(Long.valueOf(param.get("v_avg").toString()));
		zbActDto.setMaxactive(Long.valueOf(param.get("v_max").toString()));
		zbActDto.setMinactive(Long.valueOf(param.get("v_min").toString()));
		zbActDto.setAllactive(Long.valueOf(param.get("v_all").toString()));
		ApiResult apiresult = new ApiResult(ErrorCode.SUCCESS);
		apiresult.setData(zbActDto);
		return apiresult;
	}

	@Override
	public ApiResult getNewVension() {
		Vension vension =null;
		vension =zbDao.queryNewVension();
		if(vension!=null){
			ApiResult result = new ApiResult(ErrorCode.SUCCESS);
			result.setData(vension);
			return 	result;
		}
		return new ApiResult(ErrorCode.ERR_SYS_INTERNAL_ERROR);
	}

	@Override
	public ApiResult saveVension(String vension, String context) {
		Vension vensionbean =new Vension();
		vensionbean.setState(1);
		vensionbean.setvId(vension);
		vensionbean.setvContext(context);
		int flag = zbDao.insertVension(vensionbean);
		if(flag>0){
			return new ApiResult(ErrorCode.SUCCESS);	
		}
		return new ApiResult(ErrorCode.ERR_SYS_INTERNAL_ERROR);
	}

	@Override
	public ApiResult getDownLoad() {
		StaticData staticdata =null;
		staticdata = staticdatadao.queryStaticDataByKey("url");
		if(staticdata!=null){
			String url = staticdata.getDataValue();
			if(!StringUtil.isEmpty(url)){
				ApiResult result = new ApiResult(ErrorCode.SUCCESS);
				logger.debug("url:{}",url);
				result.setData(url);
				return 	result;
			}
		}
		return new ApiResult(ErrorCode.ERR_SYS_INTERNAL_ERROR);
	}

	@SuppressWarnings("finally")
	@Override
	public byte[] downUrl() {
		 byte[] body = null;
		 InputStream is =null;
		String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		File file = new File(path+"/download/apk.apk");
		 try {
			 is = new FileInputStream(file);
			 body = new byte[is.available()];
			 is.read(body);
			
		} catch (FileNotFoundException e) {
			logger.debug("{}",e);
			
		} catch (IOException e) {
			logger.debug("{}",e);
		}finally{
			try {
				is.close();
			} catch (IOException e) {
				logger.debug("{}",e);
			}
			return body;
		}
		
	}
	
}
