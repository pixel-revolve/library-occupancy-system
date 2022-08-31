package com.chy.service;

import com.chy.model.Result;
import com.chy.pojo.Role;
import com.chy.pojo.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface UserService {

    /**
     * 增加用户
     * @param user
     * @return Result
     */
    public Result add(User user);

    /**
     * 注册
     * @param user
     * @return Result
     */
    public Result register(User user);

    /**
     * 根据用户id查询到角色信息
     * @param id
     * @return Result<Set<Role>>
     */
    public Result<Set<Role>> getRoleInfoByUserId(Integer id);

    /**
     * 修改用户信息
     * @param user
     * @return Result<User>
     */
    public Result<User> updateUser(User user);
}
