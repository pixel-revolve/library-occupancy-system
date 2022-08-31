package com.chy.dao;

import com.chy.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {

    /**
     * 获得用户列表
     * @return List<User>
     */
    public List<User> findAll();

    /**
     * 增加用户
     * @param user
     * @return Integer
     */
    public Integer add(User user);

    /**
     * 根据id查询用户
     * @param id
     * @return User
     */
    public User getById(Integer id);

    /**
     * 根据用户名查询用户
     * @param name
     * @return User
     */
    User getUserByName(String name);

    /**
     * 根据用户id信息查询角色
     * @param id
     * @return User
     */
    public User getUserById(Integer id);

    /**
     * 修改用户的数据
     * @param user
     * @return Integer
     */
    public Integer modifyUser(User user);
}
