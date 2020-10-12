package team.ienergy.energydash.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import team.ienergy.energydash.beans.HistoricalData;
import team.ienergy.energydash.beans.RealtimeData;
import team.ienergy.energydash.beans.User;
import team.ienergy.energydash.beans.ResultBean;
import team.ienergy.energydash.exception.NormalException;
import team.ienergy.energydash.service.DataService;
import team.ienergy.energydash.service.UserService;

import javax.annotation.Resource;
import java.util.*;

@Controller
@RequestMapping("/data")
public class DataController {
    @Resource
    private DataService dataService;

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

        List<RealtimeData> dataList = new ArrayList();
        try {
            dataList = dataService.getRealtimeData(userId);
            for (RealtimeData realtimeData:dataList) {
                realtimeData.setDatetime(realtimeData.getDatetime());
            }

        }
        catch (NullPointerException e){
            throw new NormalException("2002"+NormalException.ERROR_CODE_NO_RESULT, "No realtime data for now");
        }

        ResultBean resultBean = new ResultBean();
        resultBean.setData(dataList);
        return JSON.toJSON(resultBean);
    }

    /**
     * @param
     * @return java.lang.Object
     * @desc Interface 2003：get historical data
     * @author Hao Cao
     * @date 5 October 2020
     * @func_name getHistoricalData
     */
    @ResponseBody
    @RequestMapping(value = "/get_historical_data", method = RequestMethod.GET)
    public Object getHistoricalData(@RequestParam(value = "email", required = true) String email,
                                    @RequestParam(value = "userName", required = false) String userName) {

        HistoricalData historicalData = new HistoricalData();

        User user = userService.getUser(email);
        int userId = user.getUserId();

        Map resultMap = new HashMap();

        //yearly
        List<HistoricalData> yearlyData = new ArrayList<>();
        try {
            yearlyData = dataService.getHistoricalYearlyData(userId);
            for (HistoricalData data:yearlyData) {
                data.setDatetime(data.getDatetime());
                data.setDatetimeStr(data.getDatetimeStr().substring(6,11));
            }
        }
        catch (NullPointerException e){
            throw new NormalException("2003"+NormalException.ERROR_CODE_NO_RESULT, "No historical data for now");
        }
        resultMap.put("yearly", yearlyData);

        //monthly
        List<HistoricalData> monthlyData = new ArrayList<>();
        try {
            monthlyData = dataService.getHistoricalMonthlyData(userId);
            for (HistoricalData data:monthlyData) {
                data.setDatetime(data.getDatetime());
                data.setDatetimeStr(data.getDatetimeStr().substring(3,11));
            }
        }
        catch (NullPointerException e){
            throw new NormalException("2003"+NormalException.ERROR_CODE_NO_RESULT, "No historical data for now");
        }
        resultMap.put("monthly", monthlyData);

        //daily
        List<HistoricalData> dailyData = new ArrayList<>();
        try {
            dailyData = dataService.getHistoricalDailyData(userId);
            for (HistoricalData data:dailyData) {
                data.setDatetime(data.getDatetime());
                data.setDatetimeStr(data.getDatetimeStr().substring(0,11));
            }
        }
        catch (NullPointerException e){
            throw new NormalException("2003"+NormalException.ERROR_CODE_NO_RESULT, "No historical data for now");
        }
        resultMap.put("daily", dailyData);

        ResultBean resultBean = new ResultBean();
        resultBean.setData(resultMap);
        return JSON.toJSON(resultBean);
    }

    /**
     * @param
     * @return java.lang.Object
     * @desc Interface 2004：get cumulative data
     * @author Hao Cao
     * @date 6 October 2020
     * @func_name getCumulativeData
     */
    @ResponseBody
    @RequestMapping(value = "/get_cumulative_data", method = RequestMethod.GET)
    public Object getCumulativeData(@RequestParam(value = "email", required = true) String email,
                                    @RequestParam(value = "userName", required = false) String userName) {


        User user = userService.getUser(email);
        int userId = user.getUserId();

        HistoricalData cummulativeData = new HistoricalData();

        try {
            cummulativeData = dataService.getCumulativeData(userId);
            cummulativeData.setDatetime(cummulativeData.getDatetime());
        }
        catch (NullPointerException e){
            throw new NormalException("2004"+NormalException.ERROR_CODE_NO_RESULT, "No cumulative data for now");
        }

        ResultBean resultBean = new ResultBean();
        resultBean.setData(cummulativeData);
        return JSON.toJSON(resultBean);
    }


}
