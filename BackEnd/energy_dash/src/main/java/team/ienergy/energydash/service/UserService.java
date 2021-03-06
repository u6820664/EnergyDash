package team.ienergy.energydash.service;
import org.springframework.stereotype.Service;
import team.ienergy.energydash.beans.Usage;
import team.ienergy.energydash.beans.User;
import team.ienergy.energydash.dao.UsageDao;

import java.util.List;
import java.util.Map;


/**
 * @desc: The service for user information handling
 * @author: Hao Cao
 * @date: 27 August 2020
 * @throws
 */

public interface UserService {
    List<User> findAll();

    void signUp(User user);

    User signIn(String email, String password);

    User getUser(String email);

    void updateUserProfile(Map paramMap);

    void updateUsageProfile(Map paramMap);

    Usage getUsage(String email, String password);

}

