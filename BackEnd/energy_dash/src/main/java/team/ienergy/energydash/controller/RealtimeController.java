package team.ienergy.energydash.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import team.ienergy.energydash.beans.RealtimeData;
import team.ienergy.energydash.beans.User;
import team.ienergy.energydash.beans.ResultBean;
import team.ienergy.energydash.exception.NormalException;
import team.ienergy.energydash.service.PlanService;
import team.ienergy.energydash.service.RealtimeService;
import team.ienergy.energydash.service.UserService;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/realtime")
public class RealtimeController {
    @Resource
    private RealtimeService realtimeService;

    @Resource
    private UserService userService;


    /**
     * @param
     * @return java.lang.Object
     * @desc Interface 2001：get notification
     * @author Hao Cao
     * @date 27 September 2020
     * @func_name getNotification
     */
    @ResponseBody
    @RequestMapping(value = "/get_notification", method = RequestMethod.GET)
    public Object findEnergyPlan(@RequestParam(value = "userId", required = true) String userId) {

        Map paramMap=new HashMap();
        ResultBean resultBean = new ResultBean();
        resultBean.setData(paramMap);
        return JSON.toJSON(resultBean);
    }


    /**
     * @param
     * @return java.lang.Object
     * @desc Interface 2002：get real time data
     * @author Hao Cao
     * @date 31 September 2020
     * @func_name getRealtimeData
     */
    @ResponseBody
    @RequestMapping(value = "/get_realtime_data", method = RequestMethod.GET)
    public Object getRealtimeData(@RequestParam(value = "email", required = true) String email,
                                  @RequestParam(value = "userName", required = false) String userName) {

        User user = userService.getUser(email);
        int userId = user.getUserId();
        System.out.println(userId);
        RealtimeData realtimeData = new RealtimeData();
        try {
            realtimeData = realtimeService.getRealtimeData(userId);
            realtimeData.setDatetime(realtimeData.getDatetime());
        }
        catch (NullPointerException e){
            throw new NormalException("2002"+NormalException.ERROR_CODE_NO_RESULT, "No realtime data for now");
        }

        ResultBean resultBean = new ResultBean();
        resultBean.setData(realtimeData);
        return JSON.toJSON(resultBean);
    }




}
