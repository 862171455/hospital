package org.java.dao;

import org.apache.ibatis.annotations.Mapper;
import org.java.entity.Room;

import java.util.List;
import java.util.Map;

@Mapper
public interface RoomMapper {
    int deleteByPrimaryKey(Integer roomId);

    int insert(Room record);

    int insertSelective(Room record);

    Room selectByPrimaryKey(Integer roomId);

    int updateByPrimaryKeySelective(Room record);

    int updateByPrimaryKey(Room record);
    
    List<Map<String,Object>> findAllroom(Map<String,Object> map);//分页查询全部房间
    int findroomcount();//聚合
    void delroom(int id);//按主键删除
    void updateroom(Map<String,Object> map);
    void addroom(Map<String,Object> map);
    List<Map<String,Object>> findroom();
}