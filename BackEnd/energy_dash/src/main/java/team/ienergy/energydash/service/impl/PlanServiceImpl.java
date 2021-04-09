package team.ienergy.energydash.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.ienergy.energydash.beans.*;
import team.ienergy.energydash.dao.*;
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

    @Autowired
    private CompanyImageDao companyImageDao;


    @Override
    public Map findImage(String companyName) {
        return companyImageDao.findImage(companyName);
    }

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