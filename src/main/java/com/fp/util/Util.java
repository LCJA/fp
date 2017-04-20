package com.fp.util;

import java.util.Map;

public class Util {
	
	public static  void put(String key ,String value,Map map){
		if(!StringUtil.isEmpty(value)){
			map.put(key, value);
		}
}
}
