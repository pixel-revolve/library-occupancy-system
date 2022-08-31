package com.chy.service.impl;

import com.chy.dao.ReservationTableDao;
import com.chy.model.Paging;
import com.chy.model.Result;
import com.chy.pojo.ReservationTable;
import com.chy.service.ReservationTableService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReservationTableServiceImpl implements ReservationTableService {
    @Autowired
    private ReservationTableDao reservationTableDao;

    /**
     * 获得预约表列表
     * @param currentPage
     * @param pageSize
     * @return Paging<ReservationTable>
     */
    @Override
    public Paging<ReservationTable> getReservationList(int currentPage, int pageSize) {
        Page<ReservationTable> reservationTablePage = PageHelper.startPage(currentPage, pageSize).doSelectPage(() -> reservationTableDao.findAll());
        Paging<ReservationTable> reservationTablePaging = new Paging<ReservationTable>(reservationTablePage);
        return reservationTablePaging;
    }

    /**
     * 增加预约表
     * @param reservationTable
     * @return Result
     */
    // @Override
    // public Result add(ReservationTable reservationTable) {
    //     Result result=new Result();
    //     if(reservationTableDao.add(reservationTable)==1){
    //         result.setResultSuccess("添加预约表成功!");
    //         return result;
    //     }
    //     result.setResultFailed("添加预约表失败！");
    //     return result;
    // }

}
