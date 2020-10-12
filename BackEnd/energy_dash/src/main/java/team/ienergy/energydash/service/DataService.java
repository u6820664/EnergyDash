package team.ienergy.energydash.service;

import team.ienergy.energydash.beans.HistoricalData;
import team.ienergy.energydash.beans.Plan;
import team.ienergy.energydash.beans.RealtimeData;

import java.util.List;
import java.util.Map;


/**
 * @desc: The service for energy plan handling
 * @author: Hao Cao
 * @date: 27 September 2020
 * @throws
 */

public interface DataService {

    List<RealtimeData> getRealtimeData(int userId);

    List<HistoricalData> getHistoricalYearlyData(int userId);

    List<HistoricalData> getHistoricalMonthlyData(int userId);

    List<HistoricalData> getHistoricalDailyData(int userId);

    HistoricalData getCumulativeData(int userId);

}

