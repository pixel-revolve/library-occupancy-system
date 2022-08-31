package com.chy.dao;

import com.chy.pojo.Seat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SeatDao {
    /**
     * 获得座位列表
     * @return
     */
    public List<Seat> findAll();

    /**
     * 根据id信息查询座位
     * @param id
     * @return Seat
     */
    public Seat getById(@Param("id")int id);


}
