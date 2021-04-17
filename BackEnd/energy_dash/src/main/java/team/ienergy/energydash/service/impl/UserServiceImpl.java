package team.ienergy.energydash.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import team.ienergy.energydash.beans.Usage;
import team.ienergy.energydash.dao.UsageDao;
import team.ienergy.energydash.dao.UserDao;
import team.ienergy.energydash.beans.User;
import team.ienergy.energydash.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


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

    @Autowired
    private UsageDao usageDao;

    @Override
    public List<User> findAll() {
        List<User> users  = userDao.findAll();
        return users;
    }

    @Override
    public void signUp(User user) {
        userDao.signUp(user);
    }

    @Override
    public User signIn(String email, String password) {
        User user = userDao.signIn(email, password);
        return user;
    }

    @Override
    public User getUser(String email) {
        User user = userDao.getUser(email);
        return user;
    }

    @Override
    public void updateUserProfile(Map paramMap) {
        userDao.updateUserProfile(paramMap);
    }

    @Override
    public Usage getUsage(String email) {
        return usageDao.getUsage(email);
    }
}