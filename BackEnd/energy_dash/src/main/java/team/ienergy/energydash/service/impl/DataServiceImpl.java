package team.ienergy.energydash.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.ienergy.energydash.beans.HistoricalData;
import team.ienergy.energydash.dao.HistoricalDao;
import team.ienergy.energydash.dao.RealtimeDao;
import team.ienergy.energydash.service.DataService;

import java.util.List;
import java.util.Map;


/**
 * @desc: Service implementation for energy plan handling
 * @author: Hao Cao
 * @date: 27 September 2020
 * @throws
 */

@Service("RealtimeService")
public class DataServiceImpl implements DataService {

    @Autowired
    private RealtimeDao realtimeDao;

    @Autowired
    private HistoricalDao historicalDao;


    @Override
    public List getRealtimeData(int userId) {
        return realtimeDao.getRealtimeData(userId);
    }

    @Override
    public List<HistoricalData> getHistoricalYearlyData(int userId) {
        return historicalDao.getHistoricalYearlyData(userId);
    }

    @Override
    public List<HistoricalData> getHistoricalMonthlyData(int userId) {
        return historicalDao.getHistoricalMonthlyData(userId);
    }

    @Override
    public List<HistoricalData> getHistoricalDailyData(int userId) {
        return historicalDao.getHistoricalDailyData(userId);
    }

    @Override
    public HistoricalData getCumulativeData(int userId) {
        return historicalDao.getCumulativeData(userId);
    }


}