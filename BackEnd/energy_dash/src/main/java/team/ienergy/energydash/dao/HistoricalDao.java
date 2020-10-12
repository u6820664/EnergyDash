package team.ienergy.energydash.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import team.ienergy.energydash.beans.HistoricalData;
import team.ienergy.energydash.beans.RealtimeData;

import java.util.List;
import java.util.Map;

/**
 * @desc: A DAO for historical data handling
 * @author: Hao Cao
 * @date: 10 October 2020
 * @throws
 */

@Repository
public interface HistoricalDao {

    List<HistoricalData> getHistoricalYearlyData(@Param(value = "userId") Integer userId);

    List<HistoricalData> getHistoricalYearlyPrice(@Param(value = "userId") Integer userId);

    List<HistoricalData> getHistoricalMonthlyData(@Param(value = "userId") Integer userId);

    List<HistoricalData> getHistoricalMonthlyPrice(@Param(value = "userId") Integer userId);

    List<HistoricalData> getHistoricalDailyData(@Param(value = "userId") Integer userId);

    List<HistoricalData> getHistoricalDailyPrice(@Param(value = "userId") Integer userId);

    HistoricalData getCumulativeData(@Param(value = "userId") Integer userId);

    HistoricalData getCumulativePrice(@Param(value = "userId") Integer userId);
}

