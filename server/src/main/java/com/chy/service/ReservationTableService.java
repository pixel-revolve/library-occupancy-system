package com.chy.service;


import com.chy.model.Paging;
import com.chy.model.Result;
import com.chy.pojo.ReservationTable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReservationTableService {

    /**
     * 增加预约表
     * @param reservationTable
     * @return Result
     */
    // public Result add(ReservationTable reservationTable);

    /**
     * 获得预约表列表
     * @param currentPage
     * @param pageSize
     * @return Paging<ReservationTable>
     */
    public Paging<ReservationTable> getReservationList(int currentPage, int pageSize) ;

}
