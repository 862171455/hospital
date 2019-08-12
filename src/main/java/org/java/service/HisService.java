package org.java.service;

import java.util.Map;

/**
 * Created by GD on 2019/8/3 0003 16:38
 */
public interface HisService {
	public Map<String,Object> findHis();//加载医院信息
	public void updateHis(Map<String,Object> map);//修改医院信息
	
}
