package com.cdcompany.service;

import com.cdcompany.domain.User;

public interface UserService {

    //登录用户
    public boolean findUser(User user);

    //查询用户名是否存在
    public boolean findName(User user);

    //注册用户
    public boolean addUser(User user);

    //注销用户
    public boolean deleteUser(User user);
}
