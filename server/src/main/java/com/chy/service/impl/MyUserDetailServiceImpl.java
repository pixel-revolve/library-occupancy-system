package com.chy.service.impl;

import com.chy.dao.UserDao;
import com.chy.model.Result;
import com.chy.pojo.Role;
import com.chy.service.RoleService;
import com.chy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自行实现Spring Security的登录逻辑
 */
@Component
public class MyUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao myUserDAO;

    @Autowired
    private UserService myUserService;

    /**
     * 取出用户
     *
     * @param username 用户名
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.chy.pojo.User myUser = null;
        try {
            myUser = myUserDAO.getUserByName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (myUser == null) {
            throw new UsernameNotFoundException("找不到用户！");
        }

        //查找角色并组装角色
        Set<Role> roles =  myUser.getRoles();
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        System.out.println("loadUserByUsername......user ===> " + myUser);
        return new User(myUser.getUserName(), myUser.getPassword(), authorities);

    }

    /**
     * 获取用户的权限信息
     * @param userId
     * @return
     */
    public List<GrantedAuthority> getUserAuthority(Integer userId){

        //角色，菜单操作权限
        //角色，菜单操作权限
        Result<Set<Role>> roleResult = myUserService.getRoleInfoByUserId(userId);
        Set<Role> roles=roleResult.getData();
        Set<String> roleNameSet=new HashSet<>();
        for (Role role:roles) {
            roleNameSet.add(role.getRoleName());
        }
        String authority = String.join(",", roleNameSet);

        return AuthorityUtils.commaSeparatedStringToAuthorityList(authority);
    }

}

