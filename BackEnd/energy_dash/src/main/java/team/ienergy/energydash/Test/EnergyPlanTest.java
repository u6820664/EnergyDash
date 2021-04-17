package team.ienergy.energydash.Test;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import team.ienergy.energydash.beans.Consumption;
import team.ienergy.energydash.beans.Plan;
import team.ienergy.energydash.controller.DataController;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Qinzhuo Li
 * @date 14 April 2021
 * @func_name singleTariff
 */

public class EnergyPlanTest {
    File planCsv=new File("src\\main\\java\\team\\ienergy\\energydash\\Test\\energy_plan.csv");
    File consumptionCsv=new File("src\\main\\java\\team\\ienergy\\energydash\\Test\\user_consumption.csv");
    //Lists save the plan and consumption
    List<Plan> planList=new ArrayList<>();
    List<Consumption> consumptionList=new ArrayList<>();
    //


    @Before
    public void loadPlan_Consumption(){
        //load plan.csv and consumption.csv
        BufferedReader pr=null;
        BufferedReader cr=null;
        try {
            pr=new BufferedReader(new FileReader(planCsv));
            cr=new BufferedReader(new FileReader(consumptionCsv));
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        String line="";
        try {
            while ((line = pr.readLine()) != null) // 读取到的内容给line变量
            {
                Plan plan=new Plan();
                String[] str=line.split(",");
                if (str[0].equals("pid"))
                    continue;
                else {
                    plan.setPid(str[0]);
                    plan.setPlanName(str[1]);
                    plan.setCompanyName(str[2]);
                    plan.setCustomerType(str[3]);
                    plan.setTariffType(str[4]);
                    plan.setSupplyPrice(str[5]);
                    plan.setSinglePrice(str[6]);
                    plan.setPeakPrice(str[7]);
                    plan.setOffPeakPrice(str[8]);
                    plan.setShoulderPrice(str[9]);
                    plan.setTou1(str[10]);
                    plan.setTou2(str[11]);
                    plan.setTou3(str[12]);
                    plan.setTou4(str[13]);
                    plan.setTou5(str[14]);
                    plan.setTou6(str[15]);
                    plan.setDemandStart(str[16]);
                    plan.setDemandEnd(str[17]);
                    plan.setDemandPrice(str[18]);
                    planList.add(plan);
                }
                System.out.println(line);
            }
            line="";
            while ((line = cr.readLine()) != null) // 读取到的内容给line变量
            {
                Consumption consumption=new Consumption();
                String[] str=line.split(",");
                if (str[0].equals("uid"))
                    continue;
                else {
                    consumption.setUid(Integer.parseInt(str[0]));
                    consumption.setPid(str[1]);
                    consumption.setP0(str[2]);
                    consumption.setP1(str[3]);
                    consumption.setP2(str[4]);
                    consumption.setP3(str[5]);
                    consumption.setP4(str[6]);
                    consumption.setP5(str[7]);
                    consumption.setP6(str[8]);
                    consumption.setP7(str[9]);
                    consumption.setP8(str[10]);
                    consumption.setP9(str[11]);
                    consumption.setP10(str[12]);
                    consumption.setP11(str[13]);
                    consumption.setP12(str[14]);
                    consumption.setP13(str[15]);
                    consumption.setP14(str[16]);
                    consumption.setP15(str[17]);
                    consumption.setP16(str[18]);
                    consumption.setP17(str[19]);
                    consumption.setP18(str[20]);
                    consumption.setP19(str[21]);
                    consumption.setP20(str[22]);
                    consumption.setP21(str[23]);
                    consumption.setP22(str[24]);
                    consumption.setP23(str[25]);
                    consumptionList.add(consumption);
                }
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //Test the single price is satisfied the "total_price = supply_price + single_price * daily_usage"
    @Test
    public void singlePriceTest(){
        List<Integer> list1= new LinkedList<>();
        List<Integer> list2= new LinkedList<>();
        float[] func;
        float tt;
        for (Consumption consumption : consumptionList){
            Plan plan=getPlan(consumption.getPid());
            DataController controller=new DataController();
            if (plan.getTariffType().equals("single")) {
                func = controller.planCal(plan, consumption);
                list1.add((int) func[0]);
                tt = Float.parseFloat(plan.getSupplyPrice()) + Float.parseFloat(plan.getSinglePrice())*consumption.getUsage(0,24,consumption.getList());
                list2.add((int)tt);
            }
        }
        Assert.assertTrue(list1.equals(list2));
    }

    //Test the demand price is satisfied the "total_price = supply_price + single_price * normal_time_usage + max_usage * (demand_end - demand_start) * demand_price"
    @Test
    public void demandPriceTest(){
        List<Integer> list1= new LinkedList<>();
        List<Integer> list2= new LinkedList<>();
        float[] func;
        float tt;
        for (Consumption consumption : consumptionList){
            Plan plan=getPlan(consumption.getPid());
            DataController controller=new DataController();
            if (plan.getTariffType().equals("demand")) {
                func = controller.planCal(plan, consumption);
                list1.add((int) func[0]);
                tt = Float.parseFloat(plan.getSupplyPrice()) +
                        Float.parseFloat(plan.getSinglePrice())*consumption.getUsage(0,Integer.parseInt(plan.getDemandStart()),consumption.getList())
                        +Float.parseFloat(plan.getDemandPrice())*maxUsage(Integer.parseInt(plan.getDemandStart()),Integer.parseInt(plan.getDemandEnd()),consumption.getList())
                        +Float.parseFloat(plan.getSinglePrice())*consumption.getUsage(Integer.parseInt(plan.getDemandEnd()),24,consumption.getList()) ;
                list2.add((int)tt);
            }
        }
        Assert.assertTrue(list1.equals(list2));
    }

    //Test the time of use price is satisfied the "total_price = supply_price + peak_price * peak_usage + off_peak_price * off_peak_usage + shoulder_price * shoulder_usage"
    //tou1-2 and 5-6 is shoulder
    //tou4-5 is peak
    //rest time is off peak
    @Test
    public void timeOfUseTest(){
        List<Integer> list1= new LinkedList<>();
        List<Integer> list2= new LinkedList<>();
        float[] func;
        float tt;
        for (Consumption consumption : consumptionList){
            Plan plan=getPlan(consumption.getPid());
            DataController controller=new DataController();
            if (plan.getTariffType().equals("timeofuse")) {
                func = controller.planCal(plan, consumption);
                list1.add((int) func[0]);
                tt = Float.parseFloat(plan.getSupplyPrice()) +
                        Float.parseFloat(plan.getOffPeakPrice())*consumption.getUsage(0,Integer.parseInt(plan.getTou1()),consumption.getList())
                        +Float.parseFloat(plan.getShoulderPrice())*consumption.getUsage(Integer.parseInt(plan.getTou1()),Integer.parseInt(plan.getTou2()),consumption.getList())
                        +Float.parseFloat(plan.getOffPeakPrice())*consumption.getUsage(Integer.parseInt(plan.getTou2()),Integer.parseInt(plan.getTou4()),consumption.getList())
                        +Float.parseFloat(plan.getPeakPrice())*consumption.getUsage(Integer.parseInt(plan.getTou4()),Integer.parseInt(plan.getTou5()),consumption.getList())
                        +Float.parseFloat(plan.getShoulderPrice())*consumption.getUsage(Integer.parseInt(plan.getTou5()),Integer.parseInt(plan.getTou6()),consumption.getList())
                        +Float.parseFloat(plan.getOffPeakPrice())*consumption.getUsage(Integer.parseInt(plan.getTou6()),24,consumption.getList());

                list2.add((int)tt);
            }
        }
        Assert.assertTrue(list1.equals(list2));
    }


    //get the detail of the certain user_consumption's pid
    private Plan getPlan(String pid){
        for (Plan plan : planList){
            if (plan.getPid().equals(pid))
                return plan;
        }
        return null;
    }

    //get the max_usage
    private float maxUsage(int start,int end,List<String> res){
        float max=0;
        for (int i=start;i<end;i++){
            if (Float.parseFloat(res.get(i))>max)
                max=Float.parseFloat(res.get(i));
        }
        max=max*(end-start);
        return max;
    }


}
