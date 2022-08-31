package com.chy.service.impl;

import com.chy.dao.SeatDao;
import com.chy.model.Result;
import com.chy.pojo.Seat;
import com.chy.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SeatServiceImpl implements SeatService {
    @Autowired
    private SeatDao seatDao;



    // @Override
    // public Result add(Seat seat) {
    //     Result result=new Result();
    //     if(seatDao.add(seat)!=1){
    //         result.setResultFailed("添加座位失败!");
    //         return result;
    //     }
    //     result.setResultSuccess("添加座位成功!");
    //     return result;
    // }

    /**
     * 获得座位列表
     * @return Result<List<Seat>>
     */
    @Override
    public Result<List<Seat>> getSeatList() {
        Result result=new Result();
        List<Seat> seatList=null;
        try {
            seatList = seatDao.findAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        if(seatList==null){
            result.setResultFailed("获取座位表失败");
            return result;
        }
        if(seatList.size()==0){
            result.setResultSuccess("座位表为空");
            return result;
        }
        result.setResultSuccess("获取座位表成功",seatList);
        return result;
    }

}
