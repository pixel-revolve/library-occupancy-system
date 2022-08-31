package com.chy.service;

import com.chy.model.Result;
import com.chy.pojo.Seat;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SeatService {
    /**
     * 增加座位
     * @param seat
     * @return Result
     */
    // public Result add(Seat seat);

    /**
     * 增加座位表
     * @return Result<List<Seat>>
     */
    public Result<List<Seat>> getSeatList();

}
