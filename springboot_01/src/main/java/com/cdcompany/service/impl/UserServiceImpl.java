package com.cdcompany.service.impl;

import com.cdcompany.dao.UserDao;
import com.cdcompany.domain.User;
import com.cdcompany.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public boolean findUser(User user) {
        boolean bl = userDao.selectUser(user);
        return bl;
    }

    @Override
    public boolean findName(User user) {
        boolean bl = userDao.selectName(user);
        return bl;
    }

    @Override
    public boolean addUser(User user) {
        boolean bl = userDao.insertUser(user);
        return bl;
    }

    @Override
    public boolean deleteUser(User user) {
        boolean bl = userDao.deleteUser(user);
        return bl;
    }
}
