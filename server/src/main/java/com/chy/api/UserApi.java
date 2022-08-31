package com.chy.api;

import com.chy.dao.UserDao;
import com.chy.model.Result;
import com.chy.pojo.User;
import com.chy.service.UserService;
import com.chy.service.impl.MyUserDetailServiceImpl;
import com.chy.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class UserApi {

    /**
     * session的字段名
     */
    public static final String SESSION_NAME = "userInfo";

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailServiceImpl userDetailService;

    /**
     * 添加用户
     * @param user
     * @return Result
     */
    @RequestMapping(value = "/admin/addUser",method = RequestMethod.POST)
    public Result addUser(@RequestBody User user){
        return userService.add(user);
    }

    /**
     * 用户注册
     * @param user
     * @param request
     * @return Result
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public Result studentRegister(@RequestBody User user, HttpServletRequest request){
        Result<User> result;
        // // 如果校验有错，返回注册失败以及错误信息
        // if (errors.hasErrors()) {
        //     result = new Result<>();
        //     result.setResultFailed(errors.getFieldError().getDefaultMessage());
        //     return result;
        // }
        // 调用注册服务
        result = userService.register(user);
        // 如果注册成功，则设定session
        if (result.isSuccess()) {
            request.getSession().setAttribute(SESSION_NAME, result.getData());
        }
        return result;
    }

    /**
     * 判断用户登录，这个api会被加入Spring Security拦截器，若用户没有登录会返回无权访问403
     *
     * @return 用户信息
     */
    @GetMapping("/user/isLogin")
    public Result<User> isLogin(Authentication authentication) { // 在Controller类方法中加上Spring Security的Authentication类型参数，可以获取当前登录的用户信息例如用户名等等
        Result<User> result = new Result<>();
        User myUser = null;
        // 判断是否登录
        if (!authentication.isAuthenticated()) {
            result.setResultFailed("用户未登录！");
            return result;
        }
        // 获取当前登录的用户信息
        System.out.println(authentication.getPrincipal());
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        try {
            // 获取用户名并查询用户
            myUser = userDao.getUserByName(userDetails.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (myUser == null) {
            result.setResultFailed("用户无效！");
            return result;
        }
        result.setResultSuccess("用户已经登录！", myUser);
        return result;
    }

    @CrossOrigin(origins = "http://localhost:8850")
    @RequestMapping(value = "/user/updateUserInfo",method = RequestMethod.PATCH)
    public Result<User> updateUserInfo(@RequestBody User user, Authentication authentication, HttpServletResponse response){
        Result<User> result;

        //我们必须想办法通过token找出我们的当前真实用户
        //找到当前登录的用户信息
        String username =  ((UserDetails)authentication.getPrincipal()).getUsername();
        //找到数据库中的用户信息
        User getUser = userDao.getUserByName(username);
        //将数据库中的id赋给修改的user让它可以定位
        user.setId(getUser.getId());

        result = userService.updateUser(user);
        if(result.isSuccess()){//结果成功
            User userDemo= result.getData();
            UserDetails userDetails=userDetailService.loadUserByUsername(userDemo.getUserName());
            //更新认证的token
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails,null,authentication.getAuthorities()));

        }

        return result;
    }

    @RequestMapping(value = "/user/modifyUserToken",method = RequestMethod.POST)
    public Result modifyUserToken(@RequestParam String username,Authentication authentication,HttpServletResponse response){
        Result result=new Result();

        User getUser =null;
        //从数据库获得用户
        try {
            getUser = userDao.getUserByName(username);
        }catch(Exception e){
            e.printStackTrace();
        }
        if(getUser==null){
            result.setResultFailed("不存在该用户!");
            return result;
        }
        // 将原有的token覆盖，再将修改后的用户信息重新生成token
        // 生成jwt，并放置到请求头中
        String jwt = jwtTokenUtil.generateToken(getUser.getUserName());
        System.out.println(jwt);
        response.setHeader(jwtTokenUtil.getHeader(), jwt);

        //将用户名和权限封装成我们的token信息
        UsernamePasswordAuthenticationToken token
                = new UsernamePasswordAuthenticationToken(getUser.getUserName(), null, userDetailService.getUserAuthority(getUser.getId()));

        //交给我们的security的上下文中去进行设置,将这个新的user的token放入到SecurityContextHolder的上下文中
        SecurityContextHolder.getContext().setAuthentication(token);

        System.out.println(authentication.getPrincipal());
        result.setResultSuccess("覆盖token成功!");
        return result;
    }




}
