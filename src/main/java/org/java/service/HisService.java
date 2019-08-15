package org.java.service;

import java.util.List;
import java.util.Map;

/**
 * Created by GD on 2019/8/3 0003 16:38
 */
public interface HisService {
	public Map<String,Object> findHis();//加载医院信息
	public void updateHis(Map<String,Object> map);//修改医院信息
	public void addyj(Map<String,Object> map);//给医院发意见
	public List<Map<String,Object>> findyjfk(Map<String,Object> map);//查询全部意见
	public int findyjfkcount();//聚合
	public void delyjfk(int id);//删除意见
	
}
