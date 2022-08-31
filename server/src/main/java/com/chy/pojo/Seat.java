package com.chy.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = {"handler"})
public class Seat implements Serializable {

    /**
     * 主键
     */
    private int id;

    /**
     * 座位是否为空
     */
    private boolean empty;

    /**
     * 座位id
     */
    private int seatId;

    /**
     * 座位所在的楼层
     */
    private String floor;

}
