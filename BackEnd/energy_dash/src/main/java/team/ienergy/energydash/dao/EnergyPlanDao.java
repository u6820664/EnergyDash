package team.ienergy.energydash.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import team.ienergy.energydash.beans.EnergyPlan;
import team.ienergy.energydash.beans.Plan;

import java.util.List;
import java.util.Map;

/**
 * @desc: A DAO for energy plan handling
 * @author: Hao Cao
 * @date: 12 October 2020
 * @throws
 */

@Repository
public interface EnergyPlanDao {

    EnergyPlan getEnergyPlan(@Param(value = "pid") String pid);

}

