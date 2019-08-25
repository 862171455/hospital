package org.java.service;

import java.util.List;
import java.util.Map;

/**
 * Created by GD on 2019/8/23 0023 15:23
 */
public interface YaocarService {
	void addyaocar(Map map);//购物车添加
	List<Map> showcar(Map map);//查看
	Map findcf(Map map);//查看是否重复
	void updatenum(Map map);//添加
	void delyao(Map map);//删除单个
	void delallyao(Map map);//删除全部
}

