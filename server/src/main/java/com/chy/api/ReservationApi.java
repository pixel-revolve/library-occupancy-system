package com.chy.api;

import com.chy.model.Paging;
import com.chy.model.Result;
import com.chy.pojo.ReservationTable;
import com.chy.service.ReservationTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReservationApi {

    @Autowired
    private ReservationTableService reservationTableService;

    /**
     * 获取分页的预约表列表
     * @param currentPage
     * @param pageSize
     * @return Paging<ReservationTable>
     */
    @RequestMapping(value = "/user/getReservationList/{currentPage}/{pageSize}",method = RequestMethod.GET)
    public Paging<ReservationTable> getReservationList(@PathVariable int currentPage,@PathVariable int pageSize){
        return reservationTableService.getReservationList(currentPage,pageSize);
    }

    /**
     * 添加预约表
     * @param reservationTable
     * @return Result
     */
    // @RequestMapping(value = "/admin/addReservation",method = RequestMethod.POST)
    // public Result addReservationList(@RequestBody ReservationTable reservationTable){
    //     return reservationTableService.add(reservationTable);
    // }





}
