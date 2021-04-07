package team.ienergy.energydash.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.ienergy.energydash.beans.Consumption;
import team.ienergy.energydash.beans.EnergyPlan;
import team.ienergy.energydash.beans.Plan;
import team.ienergy.energydash.beans.User;
import team.ienergy.energydash.dao.ConsumptionDao;
import team.ienergy.energydash.dao.EnergyPlanDao;
import team.ienergy.energydash.dao.PlanDao;
import team.ienergy.energydash.dao.UserDao;
import team.ienergy.energydash.service.PlanService;
import team.ienergy.energydash.service.UserService;

import java.util.List;
import java.util.Map;


/**
 * @desc: Service implementation for energy plan handling
 * @author: Hao Cao
 * @date: 27 September 2020
 * @throws
 */

@Service("PlanService")
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanDao planDao;

    @Autowired
    private EnergyPlanDao energyPlanDao;

    @Autowired
    private ConsumptionDao consumptionDao;

    @Override
    public List<Consumption> getConsumption() {
        return consumptionDao.getConsumption();
    }

    @Override
    public List<Plan> findAllPlan() {
        return planDao.findAllPlan();
    }

    @Override
    public List<Plan> findPlanId(Map paramMap) {
        return planDao.findPlanId(paramMap);
    }

    @Override
    public EnergyPlan getEnergyPlan(String pid) {
        return energyPlanDao.getEnergyPlan(pid);
    }
}