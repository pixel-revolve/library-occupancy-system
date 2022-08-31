package com.chy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    /**
     * 主键
     */
    private int id;

    /**
     * 用户
     */
    private Set<User> users;

    /**
     * 角色描述
     */
    private String description;

    /**
     * 角色名
     */
    private String roleName;

}
