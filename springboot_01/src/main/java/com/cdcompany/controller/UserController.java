package com.cdcompany.controller;

import com.auth0.jwt.JWT;
import com.cdcompany.domain.User;
import com.cdcompany.service.UserService;
import com.cdcompany.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;


    /**
     * 1.登录用户操作
     * @param user
     * @return
     */
    //2.2设置当前操作的访问路径
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    @CrossOrigin
    //2.3设置当前操作的返回值类型, @RequestBody User user@RequestParam("username") String loginName@RequestParam("password") String loginPsw+loginPsw
    public Result login(User user){
        //User user = new User(loginName,loginPsw);
        //flag判断是否登录成功
        boolean flag = userService.findUser(user);
        boolean flagName = userService.findName(user);
        String msg = "";
        if(flag == false){
            msg = flagName ? "密码错误" : "用户名不存在，请注册";
        }else {
            Map<String,String> payload = new HashMap<>();
            payload.put("username",user.getUsername());
            //生成JWT令牌
            String token = JWTUtils.getToken(payload);
//            String refreshToken = JWTUtils.getReFreshToken(payload);
            System.out.println(token);
//            System.out.println(refreshToken);
            /*long expireTime = JWT.decode(token)
                    .getExpiresAt().getTime();
            System.out.println("diff0====expireTime"+expireTime/1000);*/
            Object data = 0;
            user.setLoginTime(System.currentTimeMillis()/1000);
            System.out.println(user.getLoginTime());
            return new Result(data,msg,token);
        }
        System.out.println("user login success !!! username==="+user.getUsername()+"   password==="+user.getPassword());
        Object data = flag ? 0 : -1;
        return new Result(msg,data);
        //return new Result(flag ? Code.SAVE_OK : Code.SAVE_ERR);
    }

    /**
     * 2.注册用户操作
     * @param loginName
     * @param loginPsw
     * @return
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @CrossOrigin
    //2.3设置当前操作的返回值类型, @RequestBody User user@RequestParam("username") String loginName@RequestParam("password") String loginPsw+loginPsw
    public Result register(@RequestParam("username") String loginName,@RequestParam("password") String loginPsw){
        User user = new User(loginName,loginPsw);
        System.out.println("user register success !!! username==="+loginName+"   password==="+user.getPassword());
        boolean flagName = userService.findName(user);
        if(flagName){
            String msg = "用户名已经存在";
            return new Result(Code.USER_EXIST,msg);
        }else {
            boolean flag = userService.addUser(user);
            System.out.println("user login success !!! username==="+user.getUsername()+"   password==="+user.getPassword());
            if(flag){
                String msg = "注册成功";
                return new Result(Code.USER_ADD_OK,msg);
            }else {
                String msg = "注册失败";
                return new Result(Code.USER_ADD_ERR,msg);
            }
        }
    }

    /**
     * 3.注销用户操作
     * @param loginName
     * @param loginPsw
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    @CrossOrigin
    //2.3设置当前操作的返回值类型, @RequestBody User user@RequestParam("username") String loginName@RequestParam("password") String loginPsw+loginPsw
    public Result delete(@RequestParam("username") String loginName,@RequestParam("password") String loginPsw){
        User user = new User(loginName,loginPsw);
        System.out.println("user delete success !!! username==="+loginName+"   password==="+user.getPassword());
        boolean flag = userService.deleteUser(user);
        if(flag){
            String msg = "删除成功";
            System.out.println(msg);
            return new Result(Code.USER_ADD_OK,msg);
        }else {
            String msg = "删除失败";
            System.out.println(msg);
            return new Result(Code.USER_ADD_ERR,msg);
        }
    }
}
