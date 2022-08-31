package com.chy.dao;

import com.chy.pojo.ReservationTable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReservationTableDao {

    /**
     * 获取预约表列表
     * @return
     */
    public List<ReservationTable> findAll();

    /**
     * 根据id查询预约表
     * @param id
     * @return ReservationTable
     */
    public ReservationTable findByTableId(@Param("id") int id);
}
