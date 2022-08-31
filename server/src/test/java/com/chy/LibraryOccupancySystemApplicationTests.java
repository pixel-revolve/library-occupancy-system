package com.chy;

import com.chy.dao.UserDao;
import com.chy.model.Result;
import com.chy.pojo.Role;
import com.chy.pojo.User;
import com.chy.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
class LibraryOccupancySystemApplicationTests {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserService myUserService;

    @Autowired
    UserDao userDao;

    @Test
    void test(){
        String str="123456";
        System.out.println(passwordEncoder.encode(str));
    }


    @Test
    void test2() {
        Integer userId=1;
        //角色，菜单操作权限
        Result<Set<Role>> roleResult = myUserService.getRoleInfoByUserId(userId);
        Set<Role> roles=roleResult.getData();
        Set<String> roleNameSet=new HashSet<>();
        for (Role role:roles) {
            roleNameSet.add(role.getRoleName());
        }
        String str = String.join(",", roleNameSet);
        System.out.println(str);
    }

    @Test
    public void test4(){
        User user = userDao.getUserById(5);
        System.out.println(user);

    }

}
