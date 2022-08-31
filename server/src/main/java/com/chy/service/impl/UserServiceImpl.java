package com.chy.service.impl;

import com.chy.dao.UserDao;
import com.chy.model.Result;
import com.chy.param.CommonValue;
import com.chy.pojo.Role;
import com.chy.pojo.User;
import com.chy.service.UserService;
import com.chy.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.util.ExceptionTypeFilter;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * 添加用户
     * @param user
     * @return Result
     */
    @Override
    public Result add(User user){
        Result result=new Result();
        User getUser=new User();
        getUser.setUserName(user.getUserName());
        getUser.setAvatar(user.getAvatar());
        getUser.setAtLibrary(user.isAtLibrary());
        getUser.setMobile(user.getMobile());
        getUser.setPassword(user.getPassword());
        userDao.add(getUser);
        result.setResultSuccess("Success");
        return result;
    }

    /**
     * 注册
     * @param user
     * @return Result
     */
    @Override
    public Result register(User user){
        Result<User> result = new Result<>();
        // 先去数据库找用户名是否存在
        User getUser = null;
        try {
            getUser = userDao.getUserByName(user.getUserName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (getUser != null) {
            result.setResultFailed("该用户名已存在！");
            return result;
        }
        // 加密储存用户的密码
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        //组装角色身份
        Set<Role> roles=new HashSet<>();
        Role role=new Role(1,null,"学生","ROLE_STUDENT");
        roles.add(role);
        user.setRoles(roles);

        // 存入数据库
        userDao.add(user);
        // 返回用户数据，成功消息
        result.setResultSuccess("注册用户成功！", user);
        return result;

    }

    /**
     * 根据用户id查询到角色信息
     * @param id
     * @return
     */
    @Override
    public Result<Set<Role>> getRoleInfoByUserId(Integer id) {
        Result<Set<Role>> result=new Result();

        User getUser=null;
        try {
            getUser = userDao.getUserById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(getUser==null){
            result.setResultFailed("该用户不存在");
            return result;
        }
        Set<Role> roles=getUser.getRoles();
        if(roles==null){
            result.setResultFailed("该用户无角色");
            return result;
        }
        result.setResultSuccess("角色查询成功!",roles);
        return result;
    }

    @Override
    public Result<User> updateUser(User user) {
        Result<User> result=new Result<>();
        User getUser=null;
        try{
            getUser=userDao.getUserById(user.getId());
        }catch(Exception e){
            e.printStackTrace();
        }
        // 数据库中得到的完整的getUser,将user中的修改字段加入到getUser中去

        // 如果用户更换了头像且上个头像非默认头像，则删除原头像
        if (user.getAvatar()!=null&&!user.getAvatar().equals(getUser.getAvatar()) && !getUser.getAvatar().contains("default")) {
            String originAvatarName = getUser.getAvatar().substring(getUser.getAvatar().lastIndexOf("/") + 1);
            new File(CommonValue.AVATAR_USER_PATH + File.separator + originAvatarName).delete();
        }
        // 如果用户更换了头像,则在数据库中存储
        if(user.getAvatar()!=null&&!user.getAvatar().equals(getUser.getAvatar())){
            getUser.setAvatar(user.getAvatar());
        }

        // 如果用户修改了密码，则加密储存
        if (user.getPassword()!=null&&!user.getPassword().equals(getUser.getPassword())) {
            getUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        if(userDao.modifyUser(getUser)!=1) {
            result.setResultFailed("修改用户信息失败!");
            return result;
        }

        result.setResultSuccess("修改用户信息成功!",getUser);

        return result;
    }
}
