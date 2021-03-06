package team.ienergy.energydash.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import team.ienergy.energydash.beans.Usage;

import java.util.Map;

/**
 * @desc: A DAO for usage habit
 * @author: Mingchao Sima
 * @date: 17 April 2021
 * @throws
 */
@Repository
public interface UsageDao {
    Usage getUsage(@Param(value =  "email") String email,
                   @Param(value = "password") String password);

    void updateUsageProfile(Map paramMap);
}
