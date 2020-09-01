package team.ienergy.energydash.service;
import org.springframework.stereotype.Service;
import team.ienergy.energydash.beans.User;

import java.util.List;


/**
 * @desc: The service for user information handling
 * @author: Hao Cao
 * @date: 27 August 2020
 * @throws
 */

public interface UserService {
    List<User> findAll();

    User signUp(String userName, String password);

    User signIn(String userName, String password);

}

