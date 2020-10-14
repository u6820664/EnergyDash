package team.ienergy.energydash.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.ienergy.energydash.beans.HistoricalData;
import team.ienergy.energydash.dao.HistoricalDao;
import team.ienergy.energydash.dao.RealtimeDao;
import team.ienergy.energydash.exception.NormalException;
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
        List<HistoricalData> data1 = historicalDao.getHistoricalYearlyData(userId);
        List<HistoricalData> data2 = historicalDao.getHistoricalYearlyPrice(userId);
        if (data1.size()!= 0) {
            for (HistoricalData data : data1) {
                data.setDatetime(data.getDatetime());
                data.setDatetimeStr(data.getDatetimeStr().substring(6, 11));
            }
        }
        if (data2.size()!=0) {
            for (HistoricalData data : data2) {
                data.setDatetime(data.getDatetime());
                data.setDatetimeStr(data.getDatetimeStr().substring(6, 11));
            }
        }

        if (data1.size()!= 0 && data2.size()!=0){
            for (int i = 0; i < data1.size(); i++) {
                for (int j = 0; j < data2.size(); j++) {
                    if (data1.get(i).getDatetimeStr().equals(data2.get(j).getDatetimeStr())) {
                        data1.get(i).setPrice(data2.get(j).getPrice());
                    }
                }
            }
        }
        return data1;
    }

    @Override
    public List<HistoricalData> getHistoricalMonthlyData(int userId) {
        List<HistoricalData> data1 = historicalDao.getHistoricalMonthlyData(userId);
        List<HistoricalData> data2 = historicalDao.getHistoricalMonthlyPrice(userId);
        if (data1.size()!= 0) {
            for (HistoricalData data : data1) {
                data.setDatetime(data.getDatetime());
                data.setDatetimeStr(data.getDatetimeStr().substring(3, 11));
            }
        }
        if (data2.size()!=0) {
            for (HistoricalData data : data2) {
                data.setDatetime(data.getDatetime());
                data.setDatetimeStr(data.getDatetimeStr().substring(3, 11));
            }
        }

        if (data1.size()!= 0 && data2.size()!=0){
            for (int i = 0; i < data1.size(); i++) {
                for (int j = 0; j < data2.size(); j++) {
                    if (data1.get(i).getDatetimeStr().equals(data2.get(j).getDatetimeStr())) {
                        data1.get(i).setPrice(data2.get(j).getPrice());
                    }
                }
            }
        }
        return data1;
    }

    @Override
    public List<HistoricalData> getHistoricalDailyData(int userId) {
        List<HistoricalData> data1 = historicalDao.getHistoricalDailyData(userId);
        List<HistoricalData> data2 = historicalDao.getHistoricalDailyPrice(userId);
        if (data1.size()!= 0) {
            for (HistoricalData data : data1) {
                data.setDatetime(data.getDatetime());
                data.setDatetimeStr(data.getDatetimeStr().substring(0, 11));
            }
        }
        if (data2.size()!=0) {
            for (HistoricalData data : data2) {
                data.setDatetime(data.getDatetime());
                data.setDatetimeStr(data.getDatetimeStr().substring(0, 11));
            }
        }

        if (data1.size()!= 0 && data2.size()!=0){
            for (int i = 0; i < data1.size(); i++) {
                for (int j = 0; j < data2.size(); j++) {
                    if (data1.get(i).getDatetimeStr().equals(data2.get(j).getDatetimeStr())) {
                        data1.get(i).setPrice(data2.get(j).getPrice());
                    }
                }
            }
        }
        return data1;
    }


    @Override
    public HistoricalData getCumulativeData(int userId) {
        HistoricalData data1 = historicalDao.getCumulativeData(userId);
        HistoricalData data2 = historicalDao.getCumulativePrice(userId);

        try {
            data1.setDatetime(data1.getDatetime());
            data1.setPrice(data2.getPrice());
        }
        catch (NullPointerException e){
            throw new NormalException("2004"+NormalException.ERROR_CODE_NO_RESULT, "No cumulative data for now");
        }

        return data1;
    }

    @Override
    public String getAverageDailyCost(int userId) {
        return historicalDao.getAverageDailyCost(userId);
    }

    @Override
    public HistoricalData getTodayDailyCost(int userId) {
        return historicalDao.getTodayDailyCost(userId);
    }

    @Override
    public HistoricalData getCurrentMonthCost(int userId) {
        return historicalDao.getCurrentMonthCost(userId);
    }


}