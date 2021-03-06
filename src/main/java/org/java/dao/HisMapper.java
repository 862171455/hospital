package org.java.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by GD on 2019/8/3 0003 16:35
 */
@Mapper
public interface HisMapper {
	public Map<String,Object> findHis();//加载医院信息
	public void updateHis(Map<String,Object> map);//修改医院信息
	public void addyj(Map<String,Object> map);//给医院发意见
	public List<Map<String,Object>> findyjfk(Map<String,Object> map);//查询全部意见
	public int findyjfkcount();//聚合
	public void delyjfk(int id);//删除意见
	
	List<Map<String,Object>> findsy();
	void upmoney(Map map);//每日收益
	void addday();//定时添加时间
}
