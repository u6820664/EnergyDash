package team.ienergy.energydash.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import team.ienergy.energydash.dao.UserDao;
import team.ienergy.energydash.beans.User;
import team.ienergy.energydash.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @desc: Service implementation for user information handling
 * @author: Hao Cao
 * @date: 27 August 2020
 * @throws
 */

@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> findAll() {
        List<User> users  = userDao.findAll();
        return users;
    }
}