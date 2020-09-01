package team.ienergy.energydash.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import team.ienergy.energydash.beans.ResultBean;
import team.ienergy.energydash.beans.User;
import team.ienergy.energydash.exception.NormalException;
import team.ienergy.energydash.service.UserService;

import javax.annotation.Resource;
import java.util.List;

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
        JSONObject data = new JSONObject();
        data.put("rows", userList);
        data.put("total", userList.size());
        ResultBean resultBean = new ResultBean();
        resultBean.setData(data);
        return JSON.toJSON(resultBean);
    }

    /**
     * @param
     * @return java.lang.Object
     * @desc interface 1002：search for user information
     * @author Hao Cao
     * @date 1 September 2020
     * @func_name signUp
     */
    @ResponseBody
    @RequestMapping(value = "/sign_up", method = RequestMethod.PUT)
    public Object signUp(@RequestParam(value = "userName", required = true) String userName,
                              @RequestParam(value = "password", required = true) String password) {

        ResultBean resultBean = new ResultBean();

        User user = new User();

        user = userService.signUp(userName, password);
        resultBean.setData(user);
        return JSONObject.toJSON(resultBean);
    }

    /**
     * @param userName,password
     * @return java.lang.Object
     * @desc interface 1003: Allow user to sign in
     * @author Hao Cao
     * @date 1 September 2020
     * @func_name signIn
     */
    @ResponseBody
    @RequestMapping(value = "/sign_in", method = RequestMethod.POST)
    public Object signIn(@RequestParam(value = "userName", required = true) String userName,
                         @RequestParam(value = "password", required = true) String password) {

        if (userName.length() == 0 || password.length() == 0){
            throw new NormalException("1003"+NormalException.ERROR_CODE_NO_PARA,"userName and password cannot be empty");
        }
        ResultBean resultBean = new ResultBean();
        User user = new User();
        user = userService.signIn(userName, password);
        if (user == null){
            throw new NormalException("1003"+NormalException.ERROR_CODE_NO_RESULT,"wrong userName or password!");
        }
        resultBean.setData(user);
        return JSON.toJSON(resultBean);

    }


}
