package com.fp.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fp.controller.ZBController;
import com.fp.core.ErrorCode;
import com.fp.dao.ZBDao;
import com.fp.dto.ZBActDto;
import com.fp.dto.ZBBtnDto;
import com.fp.service.IZBService;
import com.fp.vo.ApiResult;

@Service
public class ZBServiceImpl implements IZBService{
	@Autowired
	private ZBDao zbDao;
	private static Logger logger = LoggerFactory.getLogger(ZBController.class);
	@Override
	public ApiResult insertFpCount(String openid, String type) {
		int rtint = zbDao.insertFpCount(openid, type);
		if(rtint>0){
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
		long count = zbDao.queryFpCount(openid, type);
		 ZBBtnDto	zbdto = new ZBBtnDto();
		zbdto.setOpenid(openid);
		zbdto.setType(type);
		zbdto.setCount(count);
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

}
