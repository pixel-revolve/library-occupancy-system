package com.chy.api;

import com.chy.model.Result;
import com.chy.pojo.Seat;
import com.chy.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SeatApi {

    @Autowired
    private SeatService seatService;

    /**
     * 添加座位
     * @param seat
     * @return Result
     */
    // @RequestMapping(value = "/admin/addSeat",method = RequestMethod.POST)
    // public Result addSeat(@RequestBody Seat seat){
    //     return seatService.add(seat);
    // }

    /**
     * 获得座位表
     * @return Result
     */
    @RequestMapping(value = "/user/getSeatList",method = RequestMethod.GET)
    public Result getSeatList(){
        return seatService.getSeatList();
    }


}
