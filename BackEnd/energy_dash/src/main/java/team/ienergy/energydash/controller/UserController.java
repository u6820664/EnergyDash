package team.ienergy.energydash.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team.ienergy.energydash.beans.*;
import team.ienergy.energydash.exception.NormalException;
import team.ienergy.energydash.service.PlanService;
import team.ienergy.energydash.service.UserService;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private PlanService planService;

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


    /**
     * @param verifiedEmail,originalEmail
     * @return java.lang.Object
     * @desc interface 1004: Verify email address existence
     * @author Hao Cao
     * @date 9 September 2020
     * @func_name verifyEmailAddress
     */
    @ResponseBody
    @RequestMapping(value = "/verify_email_address", method = RequestMethod.GET)
    public Object verifyEmailAddress(@RequestParam(value = "verifiedEmail", required = true) String verifiedEmail,
                                     @RequestParam(value = "originalEmail", required = true) String originalEmail) {

        if (verifiedEmail == null || verifiedEmail.length() == 0 || originalEmail == null || originalEmail.length() == 0){
            throw new NormalException("1003"+NormalException.ERROR_CODE_NO_PARA, "Email cannot be empty");
        }
        ResultBean resultBean = new ResultBean();
        Map params = new HashMap();
        params.put("message", null);

        //check existence of the user according to the email address
        if (!verifiedEmail.equals(originalEmail)){
            User testUser = userService.getUser(verifiedEmail);

            if (testUser != null){
                params.put("message", "The email address has been registered, please change to another one.");
            }
            else{
                params.put("message", "Congratulations, the email address is available.");
            }
        }
        resultBean.setData(params);
        return JSON.toJSON(resultBean);
    }

    /**
     * @param email,userName,password
     * @return java.lang.Object
     * @desc interface 1005: load user profile
     * @author Hao Cao
     * @date 12 September 2020
     * @func_name loadUserProfile
     */
    @ResponseBody
    @RequestMapping(value = "/load_user_profile", method = RequestMethod.GET)
    public Object loadUserProfile(@RequestParam(value = "email", required = true) String email,
                                  @RequestParam(value = "userName", required = false) String userName,
                                  @RequestParam(value = "password", required = true) String password) {

        ResultBean resultBean = new ResultBean();
        User user = userService.signIn(email, password);
        resultBean.setData(user);
        return JSON.toJSON(resultBean);

    }

    /**
     * @param
     * @return java.lang.Object
     * @desc interface 1006: update user profile
     * @author Hao Cao
     * @date 13 September 2020
     * @func_name updateUserProfile
     * @Requirement:
     * 1. Have already check the existence of the updated email address.
     * 2. All the values need to be received.
     */
    @ResponseBody
    @RequestMapping(value = "/update_user_profile", method = RequestMethod.POST)
    public Object updateUserProfile(@RequestParam(value = "userName", required = true) String userName,
                                    @RequestParam(value = "email", required = true) String email,
                                    @RequestParam(value = "password", required = true) String password,
                                    @RequestParam(value = "postcode", required = true) String postcode,
                                    @RequestParam(value = "planId", required = false) String planId,
                                    @RequestParam(value = "image", required = false) MultipartFile file,
                                    @RequestParam(value = "originalEmail", required = true) String originalEmail) {

        //check existence of the user's originalEmail
        User testUser = userService.getUser(originalEmail);
        if (testUser == null){
            throw new NormalException("1006"+NormalException.ERROR_CODE_NO_RESULT, "Cannot find the user!");
        }

        User updatedUser = new User();
        Map paramMap=new HashMap();
        paramMap.put("userName", userName);
        paramMap.put("email", email);
        paramMap.put("password", password);
        paramMap.put("postcode", postcode);
        paramMap.put("originalEmail", originalEmail);

        updatedUser.setUserId(testUser.getUserId());
        updatedUser.setUserName(userName);
        updatedUser.setEmail(email);
        updatedUser.setPassword(password);
        updatedUser.setPostcode(postcode);
        if (planId != null && planId.length() != 0){
            updatedUser.setPlanId(planId);
            paramMap.put("planId", planId);
        }

        ResultBean resultBean = new ResultBean();
        userService.updateUserProfile(paramMap);
        resultBean.setData(updatedUser);
        return JSON.toJSON(resultBean);
    }

    /**
     * @param
     * @return java.lang.Object
     * @desc Interface 1007：list all the planId and plan name to meet the users'condition
     * @author Hao Cao
     * @date 27 September 2020
     * @func_name findEnergyPlan
     */
    @ResponseBody
    @RequestMapping(value = "/find_energy_plan", method = RequestMethod.GET)
    public Object findEnergyPlan(@RequestParam(value = "companyName", required = true) String companyName,
                                 @RequestParam(value = "customerType", required = true) String customerType,
                                 @RequestParam(value = "postcode", required = true) String postcode,
                                 @RequestParam(value = "tariffType", required = true) String tariffType)  {

        Map paramMap=new HashMap();
        paramMap.put("companyName", companyName);
        paramMap.put("customerType", customerType);
        paramMap.put("postcode", postcode);
        paramMap.put("tariffType", tariffType);

        List<Plan> planList = planService.findPlanId(paramMap);


        if (planList.isEmpty()){
            throw new NormalException("1007"+NormalException.ERROR_CODE_NO_RESULT, "No corresponding energy plan");
        }

        ResultBean resultBean = new ResultBean();
        resultBean.setData(planList);
        return JSON.toJSON(resultBean);
    }

    /**
     * @param
     * @return java.lang.Object
     * @desc Interface 1008
     * @author Mingchao Sima
     * @date 17 April 2021
     * @func_name getUsageHabit
     */
    @ResponseBody
    @RequestMapping(value = "/get_usage_habit", method = RequestMethod.GET)
    public Object getUsageHabit(@RequestParam(value = "email", required = true) String email,
                                 @RequestParam(value = "userName", required = false) String userName){
        Usage usage = userService.getUsage(email);
        User user = userService.getUser(email);
        String targetPlanID = user.getPlanId();
        List<Plan> planList = planService.findAllPlan();
        List<Consumption> consumptions = planService.getConsumption();

        Consumption targetConsumption = new Consumption();
        for (Consumption consumption : consumptions) {
            if (consumption.getUid() == (user.getUserId())) {
                targetConsumption = consumption;
                break;
            }
        }


        for (Plan plan : planList) {
            if(plan.getPid().equals(targetPlanID)){
                usage.setCompanyName(plan.getCompanyName());
                usage.setPlanName(plan.getPlanName());
                usage.setPlanId(targetPlanID);
                usage.setTariffType(plan.getTariffType());
                usage.setEstimatedPrice(DataController.planCal(plan, targetConsumption)[0]);
                break;
            }
        }

        ResultBean resultBean = new ResultBean();
        resultBean.setData(usage);
        return JSON.toJSON(resultBean);
    }
}
