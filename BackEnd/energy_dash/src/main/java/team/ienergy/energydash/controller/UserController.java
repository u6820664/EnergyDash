package team.ienergy.energydash.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import team.ienergy.energydash.beans.ResultBean;
import team.ienergy.energydash.beans.User;
import team.ienergy.energydash.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2020/08/31.
*/
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

//    @PostMapping("/list")
//    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
//        PageHelper.startPage(page, size);
//        List<User> list = userService.findAll();
//        PageInfo pageInfo = new PageInfo(list);
//        return ResultGenerator.genSuccessResult(pageInfo);
//    }

    @PostMapping("/list")
    public Object list() {
        List<User> userList = userService.findAll();
        JSONObject data = new JSONObject();
        data.put("rows", userList);
        data.put("total", userList.size());
        ResultBean resultBean = new ResultBean();
        resultBean.setData(data);
        return JSON.toJSON(resultBean);
    }
}
