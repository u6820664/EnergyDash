package team.ienergy.energydash.service;

import team.ienergy.energydash.beans.EnergyPlan;
import team.ienergy.energydash.beans.Plan;

import java.util.List;
import java.util.Map;


/**
 * @desc: The service for energy plan handling
 * @author: Hao Cao
 * @date: 27 September 2020
 * @throws
 */

public interface PlanService {

    List<Plan> findPlanId(Map paramMap);

    EnergyPlan getEnergyPlan(String pid);
}

