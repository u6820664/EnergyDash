package team.ienergy.energydash.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import team.ienergy.energydash.beans.*;
import team.ienergy.energydash.exception.NormalException;
import team.ienergy.energydash.service.DataService;
import team.ienergy.energydash.service.PlanService;
import team.ienergy.energydash.service.UserService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

@Controller
@RequestMapping("/data")
public class DataController {
    @Resource
    private DataService dataService;

    @Resource
    private UserService userService;

    @Resource
    private PlanService planService;


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
    public Object getNotification(@RequestParam(value = "email", required = true) String email) {

        User user = userService.getUser(email);
        int userId = user.getUserId();
        String planId = user.getPlanId();
        // get the user's energy plan
        EnergyPlan energyPlan = planService.getEnergyPlan(planId);

        String average = dataService.getAverageDailyCost(userId);

        HistoricalData todayData = dataService.getTodayDailyCost(userId);

        Map resultmMap=new HashMap();

        if (todayData == null){
            todayData = new HistoricalData();
            todayData.setUserId(String.valueOf(userId));
            todayData.setPrice("0");
            todayData.setDatetimeStr("xx-xx-2020 24:00:00");
        }

        String tempTime = todayData.getDatetimeStr().substring(11);
        double rate = (Double.parseDouble(tempTime.substring(0,2))+Double.parseDouble(tempTime.substring(3,5))/60+Double.parseDouble(tempTime.substring(6,8))/3600)/24;
        double b = 0;
        if (rate!=0){
            b = Double.parseDouble(todayData.getPrice())/rate;
        }
        else{
            b = Double.parseDouble(todayData.getPrice());
        }
        double a = Double.parseDouble(average);

        // Single tariff type
        if (energyPlan.getTariffType().equals("single")){

            resultmMap = singleTariff(a,b);
        }

        double d =0;
        double c =0;
        // Usage tariff type
        if (energyPlan.getTariffType().equals("usage")){

            d = Double.parseDouble(dataService.getCurrentMonthCost(userId).getPrice()) - Double.parseDouble(todayData.getPrice()) + b;

            c = Double.parseDouble(energyPlan.getSupplyPrice())*30+Double.parseDouble(energyPlan.getUsagePrice1())*energyPlan.getUsageLimit();

            resultmMap = usageTariff(a,b,c,d);
        }
        // Time of use tariff type
        if (energyPlan.getTariffType().equals("timeofuse")){
            List<RealtimeData> dataList = dataService.getRealtimeData(userId);
            RealtimeData currentData = dataList.get(0);

            int currentHour = Integer.parseInt(currentData.getDatetimeStr().substring(11,13));

            String tempt = currentData.getDatetimeStr().substring(11);

            double currentTime = (Double.parseDouble(tempt.substring(0,2))+Double.parseDouble(tempt.substring(3,5))/60+Double.parseDouble(tempt.substring(6,8))/3600);

            String value = "";
            int flag = 0;
            int flagNext = 0;
            if (currentHour < energyPlan.getTou1()){
                flag = 4- energyPlan.getP1id();
                flagNext = 4- energyPlan.getP2id();
                value = cal(flag, flagNext, currentTime, 0,energyPlan.getTou1());

            }else if (currentHour >= energyPlan.getTou1() && currentHour < energyPlan.getTou2()){
                flag = 4- energyPlan.getP2id();
                if (energyPlan.getTou5()!=0){
                    flagNext = 4- energyPlan.getP3id();
                }else{
                    flagNext = 4- energyPlan.getP1id();
                }
                value = cal(flag, flagNext, currentTime, energyPlan.getTou1(),energyPlan.getTou2());


            }else if (currentHour >= energyPlan.getTou2() && currentHour < energyPlan.getTou3()){
                flag = 4- energyPlan.getP3id();
                if (energyPlan.getTou5()!=0){
                    flagNext = 4- energyPlan.getP4id();
                }else{
                    flagNext = 4- energyPlan.getP1id();
                }
                value = cal(flag, flagNext, currentTime, energyPlan.getTou2(),energyPlan.getTou3());


            }else if (currentHour >= energyPlan.getTou3() && currentHour < energyPlan.getTou4()){
                flag = 4- energyPlan.getP4id();
                if (energyPlan.getTou5()!=0){
                    flagNext = 4- energyPlan.getP5id();
                }else{
                    flagNext = 4- energyPlan.getP1id();
                }
                value = cal(flag, flagNext, currentTime, energyPlan.getTou3(),energyPlan.getTou4());

            }else if (currentHour >= energyPlan.getTou4() && currentHour < energyPlan.getTou5()){
                flag = 4- energyPlan.getP5id();
                if (energyPlan.getTou5()!=0){
                    flagNext = 4- energyPlan.getP6id();
                }else{
                    flagNext = 4- energyPlan.getP1id();
                }
                value = cal(flag, flagNext, currentTime, energyPlan.getTou4(),energyPlan.getTou5());
            }else if (currentHour >= energyPlan.getTou5() && currentHour < energyPlan.getTou6()){
                flag = 4- energyPlan.getP6id();
                flagNext = 4- energyPlan.getP1id();
                value = cal(flag, flagNext, currentTime, energyPlan.getTou5(),energyPlan.getTou6());
            }

            resultmMap.put("level", flag);
            resultmMap.put("value", value);
        }


        ResultBean resultBean = new ResultBean();
        resultBean.setData(resultmMap);
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
        List<HistoricalData> yearlyData = dataService.getHistoricalYearlyData(userId);
        if (yearlyData.size() == 0){
            throw new NormalException("2003"+NormalException.ERROR_CODE_NO_RESULT, "No historical data for now");
        }
        resultMap.put("yearly", yearlyData);

        //monthly
        List<HistoricalData> monthlyData = dataService.getHistoricalMonthlyData(userId);
       if (monthlyData.size() == 0){
            throw new NormalException("2003"+NormalException.ERROR_CODE_NO_RESULT, "No historical data for now");
        }
        resultMap.put("monthly", monthlyData);

        //daily
        List<HistoricalData> dailyData = dataService.getHistoricalDailyData(userId);
        if (dailyData.size()==0){
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

        HistoricalData cummulativeData = dataService.getCumulativeData(userId);

        ResultBean resultBean = new ResultBean();
        resultBean.setData(cummulativeData);
        return JSON.toJSON(resultBean);
    }



    /**
     * @param A,B
     * @return HashMap
     * @desc Method of getting notification of single tariff
     * @author Mingchao Sima
     * @date 1 October 2020
     * @func_name singleTariff
     */
    private static Map singleTariff(double A, double B){
        BigDecimal doubleA = new BigDecimal(Double.toString(A));
        BigDecimal doubleB = new BigDecimal(Double.toString(B));
        String level;
        BigDecimal value;
        int flag = 0;
        if(B >= 1.05*A){
            level = "red";
            flag = 3;
            if(B > 1.5*A){
                value = new BigDecimal(Double.toString(1));
            }else{
                value = (doubleB.subtract(doubleA.multiply(new BigDecimal(Double.toString(1.05))))).divide(doubleA.multiply(new BigDecimal(Double.toString(0.45))), 4, RoundingMode.HALF_DOWN);
            }
        }else if(B >= 0.95*A && B < 1.05*A){
            level = "yellow";
            flag = 2;
            value = (doubleB.subtract(doubleA.multiply(new BigDecimal(Double.toString(0.95))))).divide(doubleA.multiply(new BigDecimal(Double.toString(0.1))), 4, RoundingMode.HALF_DOWN);
        }else{
            level = "green";
            flag = 1;
            if(B < 0.75*A){
                value = new BigDecimal(Double.toString(1));
            }else{
                value = ((doubleA.multiply(new BigDecimal(Double.toString(0.95)))).subtract(doubleB)).divide(doubleA.multiply(new BigDecimal(Double.toString(0.2))), 4, RoundingMode.HALF_DOWN);
            }
        }
        DecimalFormat df = new DecimalFormat("0.0%");
        String s = df.format(value.doubleValue());
        Map resultmMap=new HashMap();
        resultmMap.put("level", flag);
        resultmMap.put("value", s);

        return resultmMap;
    }

    /**
     * @param A,B,C,D
     * @return HashMap
     * @desc Method of getting notification of usage tariff
     * @author Mingchao Sima
     * @date 1 October 2020
     * @func_name singleTariff
     */
    private static Map usageTariff(double A, double B, double C, double D){
        BigDecimal doubleA = new BigDecimal(Double.toString(A));
        BigDecimal doubleC = new BigDecimal(Double.toString(C));
        BigDecimal doubleD = new BigDecimal(Double.toString(D));
        if (A*30 <= C) return singleTariff(A,B);
        else{
            String level;
            BigDecimal value;
            int flag = 0;
            if(D <= C){
                level = "green";
                flag = 1;
                if(D < 0.8*C) value = new BigDecimal(Double.toString(1));
                else{
                    value = (doubleC.subtract(doubleD)).divide(doubleC.multiply(new BigDecimal(Double.toString(0.2))),4,RoundingMode.HALF_DOWN);
                }
            }else if(D > C && D <= 1.05*A*30){
                level = "yellow";
                flag = 2;
                value = (doubleD.subtract(doubleC)).divide((doubleA.multiply(new BigDecimal(Double.toString(30*1.05)))).subtract(doubleC),4,RoundingMode.HALF_DOWN);
            }else{
                level = "red";
                flag = 3;
                if(D>=1.5*A*30) value = new BigDecimal(Double.toString(1));
                else {
                    value = (doubleD.subtract((doubleA.multiply(new BigDecimal(Double.toString(1.05*30)))))).divide((doubleA.multiply(new BigDecimal(Double.toString(0.45*30)))),4,RoundingMode.HALF_DOWN);
                }
            }
            DecimalFormat df = new DecimalFormat("0.0%");
            String s = df.format(value.doubleValue());

            Map resultmMap=new HashMap();
            resultmMap.put("level", flag);
            resultmMap.put("value", s);

            return  resultmMap;
        }
    }

    private static String cal(int nowFlag, int nextFlag, double currentTime,int nowTou, int nextTou){
        double value = 0;
        DecimalFormat percent = new DecimalFormat("0.0%");

        double rate = (nextTou - currentTime)/(nextTou - nowTou);

        if (nowFlag == 1){
            value = rate;
        }else if(nowFlag == 3){
            value = 1-rate;
        }else if (nowFlag ==2 && nextFlag ==1){
            value = rate;
        }else if (nowFlag ==2 && nextFlag ==3){
            value = 1-rate;
        }
        return percent.format(value);
    }

    /**
     * @param
     * @return java.lang.Object
     * @desc Interface 2006：get recommend energy plan
     * @author Mingchao Sima
     * @date 5 April 2021
     * @func_name getRecommend
     */
    @ResponseBody
    @RequestMapping(value = "/user_get_recommend", method = RequestMethod.GET)
    public Object getRecommend(@RequestParam(value = "email") String email,
                               @RequestParam(value = "userName", required = false) String userName){
//        User user = userService.getUser(email);
        List<Plan> plans = planService.findAllPlan();
//        plans.sort(Comparator.comparingInt(o -> Integer.valueOf(o.getPid())));

        ResultBean resultBean = new ResultBean();
        resultBean.setData(plans);
        return JSON.toJSON(resultBean);
    }



}
