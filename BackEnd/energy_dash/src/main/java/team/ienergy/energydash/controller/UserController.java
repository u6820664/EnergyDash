package team.ienergy.energydash.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team.ienergy.energydash.beans.ResultBean;
import team.ienergy.energydash.beans.User;
import team.ienergy.energydash.exception.NormalException;
import team.ienergy.energydash.service.UserService;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* Created by CodeGenerator on 2020/08/31.
*/
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * @param
     * @return java.lang.Object
     * @desc Interface 1001：list all the users' information (use for test)
     * @author Hao Cao
     * @date 30 August 2020
     * @func_name listAll
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object listAll() {
        List<User> userList = userService.findAll();

        //data.put(userList);
        ResultBean resultBean = new ResultBean();
        resultBean.setData(userList);
        return JSON.toJSON(resultBean);
    }

    /**
     * @param userName,email,password,postcode,planId,image
     * @return java.lang.Object
     * @desc interface 1002：sign up
     * @author Hao Cao
     * @date 1 September 2020
     * @func_name signUp
     * @Requirement:
     * 1.Check the input
     *      1.1 Check the username（not null, length）
     *      1.2 Check the email（not null, length and unique）
     *      1.3 Check the password（not null、length and format）
     *      1.4 Check the postcode（not null、length and format?）
     *
     * 2.Database operation（insert)
     * 3.Response
     */
    @ResponseBody
    @RequestMapping(value = "/sign_up", method = RequestMethod.POST)
    public Object signUp(@RequestParam(value = "userName", required = true) String userName,
                         @RequestParam(value = "email", required = true) String email,
                              @RequestParam(value = "password", required = true) String password,
                            @RequestParam(value = "postcode", required = true) String postcode,
                         @RequestParam(value = "planId", required = false) String planId,
                         @RequestParam(value = "image", required = false) MultipartFile file) throws IOException {


        if (userName == null || userName.length() == 0){
            throw new NormalException("1002"+NormalException.ERROR_CODE_NO_PARA, "UserName cannot be empty");
        }
        if (password == null || password.length() == 0){
            throw new NormalException("1002"+NormalException.ERROR_CODE_NO_PARA, "Password cannot be empty");
        }
        if (postcode == null || postcode.length() == 0){
            throw new NormalException("1002"+NormalException.ERROR_CODE_NO_PARA, "Postcode cannot be empty");
        }
        if (email == null || email.length() == 0){
            throw new NormalException("1002"+NormalException.ERROR_CODE_NO_PARA, "Email address cannot be empty");
        }
        //check existence of the user according to unique email address
        User testUser = userService.getUser(email);
        if (testUser != null){
            throw new NormalException("1002"+NormalException.ERROR_CODE_ILLEGALSTR, "This email address has already been registered");
        }

        User user = new User();
        user.setEmail(email);
        user.setUserName(userName);
        user.setPassword(password);
        user.setPostcode(postcode);
        if (planId != null && planId.length() != 0){
            user.setPlanId(planId);
        }

//        if (file!= null){
//            Base64.Encoder encoder = Base64.getEncoder();
//            String image = encoder.encodeToString(file.getBytes());
//            user.setImage(image);
//        }

        userService.signUp(user);

        ResultBean resultBean = new ResultBean();
        resultBean.setData(user);
        return JSONObject.toJSON(resultBean);
    }

    /**
     * @param email,password
     * @return java.lang.Object
     * @desc interface 1003: Allow user to sign in
     * @author Hao Cao
     * @date 1 September 2020
     * @func_name signIn
     */
    @ResponseBody
    @RequestMapping(value = "/sign_in", method = RequestMethod.POST)
    public Object signIn(@RequestParam(value = "email", required = true) String email,
                         @RequestParam(value = "password", required = true) String password) {

        if (email == null || email.length() == 0 || password == null || password.length() == 0){
            throw new NormalException("1003"+NormalException.ERROR_CODE_NO_PARA, "Email or password cannot be empty");
        }
        ResultBean resultBean = new ResultBean();
        User user = userService.signIn(email, password);
        if (user == null){
            throw new NormalException("1003"+NormalException.ERROR_CODE_NO_RESULT,"Wrong email or password!");
        }
        resultBean.setData(user);
        return JSON.toJSON(resultBean);
    }

}
