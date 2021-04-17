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
import org.apache.commons.codec.binary.Base64;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Blob;
import java.sql.SQLException;
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
     * @author Mingchao Sima
     * @date 7 April 2021
     * @func_name getConsumption
     */
    @ResponseBody
    @RequestMapping(value = "/get_consumption", method = RequestMethod.GET)
    public Object getConsumption(@RequestParam(value = "email") String email,
                                 @RequestParam(value = "userName", required = false) String userName){
        User user = userService.getUser(email);
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("uid", String.valueOf(user.getUserId()));

        List<Consumption> consumptions = planService.getConsumption();
        Plan targetPlan = new Plan();
        Consumption targetConsumption = new Consumption();
        for (Consumption consumption : consumptions) {
            if (consumption.getUid() == (user.getUserId())) {
                targetConsumption = consumption;
                break;
            }
        }
        resultMap.put("pid", user.getPlanId());

        List<Plan> plans = planService.findAllPlan();
        List<RecommendPlan> recommendPlans = new ArrayList<>();
        float[] originCost = new float[3];
        for (Plan plan : plans) {
            if (plan.getPid().equals(user.getPlanId())) {
                originCost = planCal(plan, targetConsumption);
                break;
            }
        }

        resultMap.put("cost", String.valueOf(originCost[0]));

        ResultBean resultBean = new ResultBean();
        resultBean.setData(resultMap);
        return JSON.toJSON(resultBean);
    }

    public static String byteArr2String(byte[] byteArr) {
        String stringBase64 = null;
        try {
            Base64 encoder = new Base64();
            stringBase64 =(byteArr != null ? encoder.encodeToString(byteArr) : "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBase64;
    }

    @ResponseBody
    @RequestMapping(value = "/get_image", method = RequestMethod.GET)
    public Object getImage(@RequestParam(value = "companyName") String companyName){
        Map companyImages = planService.findImage(companyName);
        ResultBean resultBean = new ResultBean();
        resultBean.setData(byteArr2String((byte[]) companyImages.get("picture")));
        return JSON.toJSON(resultBean);
    }

    public String findImage(String companyName){
        Map companyImages = planService.findImage(companyName);
        return byteArr2String((byte[]) companyImages.get("picture"));
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

        User user = userService.getUser(email);
        String targetPlanID = user.getPlanId();
        List<Consumption> consumptions = planService.getConsumption();
        Map<String, String> mapImage = new HashMap<>();

        Plan targetPlan = new Plan();
        Consumption targetConsumption = new Consumption();
        for (Consumption consumption : consumptions) {
            if (consumption.getUid() == (user.getUserId())) {
                targetConsumption = consumption;
                break;
            }
        }
        boolean isLegal = false;
        List<Plan> plans = planService.findAllPlan();
        List<RecommendPlan> recommendPlans = new ArrayList<>();
        float[] originCost = new float[3];
        for (Plan plan : plans) {
            if (plan.getPid().equals(targetPlanID)) {
                originCost = planCal(plan, targetConsumption);
                targetPlan = plan;
                isLegal = true;
                break;
            }
        }

        if(!isLegal){
            throw new NormalException("2003"+NormalException.ERROR_CODE_NO_RESULT, "Illegal plan");
        }

        for (Plan plan : plans) {
            if (plan.getPid().equals(targetPlanID)) continue;

            float[] totalCost = planCal(plan, targetConsumption);
            if (totalCost[0] < originCost[0]) {
                RecommendPlan rp = new RecommendPlan();
                rp.setPid(plan.getPid());
                rp.setPlanName(plan.getPlanName());
                rp.setCompanyName(plan.getCompanyName());
                rp.setTariff(plan.getTariffType());
                rp.setTotalCost(totalCost[0]);
                rp.setSaveMoney(originCost[0] - totalCost[0]);
                rp.setEnergyPer(totalCost[1]);
                rp.setSupplyPer(totalCost[2]);
                if(mapImage.isEmpty() || !mapImage.containsKey(plan.getCompanyName())){
                    mapImage.put(plan.getCompanyName(), findImage(plan.getCompanyName()));
                }
                rp.setImage(mapImage.get(plan.getCompanyName()));
                rp.setFlag("0");
                recommendPlans.add(rp);
            }
        }

        if(recommendPlans.isEmpty()){
            throw new NormalException("2003"+NormalException.ERROR_CODE_NO_RESULT, "No better plans recommended");

        }

        recommendPlans.sort((o1, o2) -> {
            if(o1.getTotalCost() == o2.getTotalCost()){
                return Integer.parseInt(o1.getPid()) - Integer.parseInt(o2.getPid());
            }else {
                if(o1.getTotalCost() > o2.getTotalCost()) return 1;
                else return -1;
            }
        });

        RecommendPlan rp = new RecommendPlan();
        rp.setPid(targetPlan.getPid());
        rp.setPlanName(targetPlan.getPlanName());
        rp.setCompanyName(targetPlan.getCompanyName());
        rp.setTariff(targetPlan.getTariffType());
        rp.setTotalCost(originCost[0]);
        rp.setSaveMoney(0);
        rp.setEnergyPer(originCost[1]);
        rp.setSupplyPer(originCost[2]);
        rp.setImage(findImage(targetPlan.getCompanyName()));
        rp.setFlag("1");
        recommendPlans.add(rp);

        ResultBean resultBean = new ResultBean();
        resultBean.setData(recommendPlans);
        return JSON.toJSON(resultBean);
    }

    public static float[] planCal(Plan plan, Consumption consumption){
        List<String> res = consumption.getList();
        if(plan.getTariffType().equals("single")){
            return singleCal(Float.parseFloat(plan.getSupplyPrice()),
                    Float.parseFloat(plan.getSinglePrice()),res, consumption);
        }
        if(plan.getTariffType().equals("demand")){
            if(plan.getDemandStart()==null || plan.getDemandEnd()==null) return new float[]{Float.MAX_VALUE};
            return demandCal(Float.parseFloat(plan.getSupplyPrice()),Float.parseFloat(plan.getSinglePrice()),
                    Integer.parseInt(plan.getDemandStart()),Integer.parseInt(plan.getDemandEnd()),
                    Float.parseFloat(plan.getDemandPrice()),res, consumption);
        }
        if(plan.getTariffType().equals("timeofuse")){
            int[] tou = new int[6];
            if(plan.getTou1() == null || plan.getTou2() == null || plan.getTou3() == null ||
                    plan.getTou4() == null || plan.getTou5() == null || plan.getTou6() == null){
                return new float[]{Float.MAX_VALUE};
            }
            tou[0] = Integer.parseInt(plan.getTou1());
            tou[1] = Integer.parseInt(plan.getTou2());
            tou[2] = Integer.parseInt(plan.getTou3());
            tou[3] = Integer.parseInt(plan.getTou4());
            tou[4] = Integer.parseInt(plan.getTou5());
            tou[5] = Integer.parseInt(plan.getTou6());
            return timeofuseCal(Float.parseFloat(plan.getSupplyPrice()),Float.parseFloat(plan.getPeakPrice()),
                    Float.parseFloat(plan.getOffPeakPrice()),Float.parseFloat(plan.getShoulderPrice()),
                    tou, res, consumption);
        }
        return new float[]{Float.MAX_VALUE};
    }

    private static float[] singleCal( float supply_price, float single_price,
                                    List<String> res, Consumption consumption){
        float[] resCal = new float[3];
        float single = single_price * consumption.getUsage(0, 24, res);
        float total = single + supply_price;
        resCal[0] = total;
        resCal[1] = single / total;
        resCal[2] = supply_price / total;
        return resCal;
    }

    private static float[] demandCal( float supply_price,float single_price, int demand_start,
                                    int demand_end, float demand_price, List<String> res, Consumption consumption){
        float demand_total_price = demand_price * demandMaxUsage(demand_start,demand_end,res);
        float demand_normal_price = single_price * (consumption.getUsage(0, demand_start,res) +
                consumption.getUsage(demand_end, 24, res));
        float[] resCal = new float[3];
        float single = demand_normal_price + demand_total_price;
        float total = single + supply_price;
        resCal[0] = total;
        resCal[1] = single / total;
        resCal[2] = supply_price / total;
        return resCal;
    }

    private static float demandMaxUsage(int startTime, int endTime, List<String> res){
        float max_usage = 0.0f;
        for(int i = startTime; i < endTime; i++){
            if(Float.parseFloat(res.get(i)) > max_usage)    max_usage = Float.parseFloat(res.get(i));
        }
        return max_usage*(endTime-startTime);
    }

    private static float[] timeofuseCal(float supply_price, float peak_price, float off_peak_price,
                                      float shoulder_price, int[] tou, List<String> res, Consumption consumption){
        float peak_total_price = peak_price * consumption.getUsage(tou[2], tou[3], res);
        float shoulder_total_price = shoulder_price * (consumption.getUsage(tou[0], tou[1], res) +
                consumption.getUsage(tou[4], tou[5], res));
        float off_peak_total_price = off_peak_price * (consumption.getUsage(0, tou[0],res) +
                consumption.getUsage(tou[1], tou[2], res) +
                consumption.getUsage(tou[3], tou[4], res) +
                consumption.getUsage(tou[5], 24, res));

        float[] resCal = new float[3];
        float single = peak_total_price + shoulder_total_price + off_peak_total_price;
        float total = single + supply_price;
        resCal[0] = total;
        resCal[1] = single / total;
        resCal[2] = supply_price / total;
        return resCal;
    }


}
