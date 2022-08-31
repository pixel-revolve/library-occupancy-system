package com.chy.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

    private boolean success;

    private String message;

    private T data;

    /**
     *设定成功返回信息(无数据)
     */
    public void setResultSuccess(String msg) {
        this.success = true;
        this.message = msg;
        this.data = data;
    }

    /**
     *设定成功返回信息（有数据）
     */
    public void setResultSuccess(String msg, T data) {
        this.success = true;
        this.message = msg;
        this.data = data;
    }

    /**
     *设定失败返回信息（无数据）
     */
    public void setResultFailed(String msg) {
        this.success = false;
        this.message = msg;
        this.data = null;
    }

    /**
     *设定失败返回信息（有数据）
     */
    public void setResultFailed(String msg, T data) {
        this.success = false;
        this.message = msg;
        this.data = null;
    }

}
