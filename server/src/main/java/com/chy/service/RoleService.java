package com.chy.service;

import com.chy.pojo.Role;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface RoleService {

    /**
     * 根据用户名获取角色
     * @return Set<Role>
     */
    public Set<Role> getRolesByUserName();

}



