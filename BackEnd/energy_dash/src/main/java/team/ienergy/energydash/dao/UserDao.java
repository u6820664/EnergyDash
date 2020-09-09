package team.ienergy.energydash.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import team.ienergy.energydash.beans.User;

import java.util.List;
import java.util.Map;

/**
 * @desc: A DAO for user information handling
 * @author: Hao Cao
 * @date: 27 August 2020
 * @throws
 */

@Repository
public interface UserDao {

    //test user table
    List<User> findAll();

    //sign up
    void signUp(User user);

    //sign in
    User signIn(@Param(value = "email")String email, @Param(value = "password")String password);

    //get specific user according to unique email
    User getUser(@Param(value = "email")String email);

    //User getUser(@Param("userName") String userName, @Param("password") String password);

}

