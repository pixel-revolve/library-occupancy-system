package com.chy.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = {"handler"})
public class User implements Serializable {

    /**
     * 主键
     */
    private int id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 是否在图书馆
     */
    private boolean atLibrary;

    /**
     * 角色信息
     */
    private Set<Role> roles;

}
