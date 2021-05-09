package team.ienergy.energydash.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import team.ienergy.energydash.beans.HistoricalData;
import team.ienergy.energydash.beans.PredictionData;

import java.util.List;

/**
 * @desc: A DAO for historical data handling
 * @author: Hao Cao
 * @date: 10 October 2020
 * @throws
 */

@Repository
public interface PredictionDao {
    List<PredictionData> getPredictionMonthlyData(@Param(value = "userId") Integer userId);
}

