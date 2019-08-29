package org.java.dao;

import org.apache.ibatis.annotations.Mapper;
import org.java.entity.Yaocar;

import java.util.List;
import java.util.Map;

@Mapper
public interface YaocarMapper {
    int deleteByPrimaryKey(Integer yaocarId);

    int insert(Yaocar record);

    int insertSelective(Yaocar record);

    Yaocar selectByPrimaryKey(Integer yaocarId);

    int updateByPrimaryKeySelective(Yaocar record);

    int updateByPrimaryKey(Yaocar record);
    void addyaocar(Map map);//购物车添加
    List<Map> showcar(Map map);//查看
    Map findcf(Map map);//查看是否重复
    void updatenum(Map map);//添加
    void delyao(Map map);//删除单个
    void delallyao(Map map);//删除全部
     void updatetype(Map map);//生成订单编号
}