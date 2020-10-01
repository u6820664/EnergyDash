package team.ienergy.energydash.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import team.ienergy.energydash.beans.Plan;
import team.ienergy.energydash.beans.ResultBean;
import team.ienergy.energydash.exception.NormalException;
import team.ienergy.energydash.service.PlanService;
import team.ienergy.energydash.service.RealtimeService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/realtime")
public class RealtimeController {
    @Resource
    private RealtimeService realtimeService;

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


}
