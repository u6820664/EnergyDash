package team.ienergy.energydash.dao;

import org.springframework.stereotype.Repository;
import team.ienergy.energydash.beans.Consumption;

import java.util.List;

/**
 * @desc: A DAO for user consumption
 * @author: Mingchao Sima
 * @date: 07 April 2021
 * @throws
 */

@Repository
public interface ConsumptionDao {
    List<Consumption> getConsumption();
}
