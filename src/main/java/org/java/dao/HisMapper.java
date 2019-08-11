package org.java.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * Created by GD on 2019/8/3 0003 16:35
 */
@Mapper
public interface HisMapper {
	public Map<String,Object> findHis();//加载医院信息
}
