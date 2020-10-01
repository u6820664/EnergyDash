package team.ienergy.energydash.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.ienergy.energydash.beans.RealtimeData;
import team.ienergy.energydash.dao.PlanDao;
import team.ienergy.energydash.dao.RealtimeDao;
import team.ienergy.energydash.service.RealtimeService;


/**
 * @desc: Service implementation for energy plan handling
 * @author: Hao Cao
 * @date: 27 September 2020
 * @throws
 */

@Service("RealtimeService")
public class RealtimeServiceImpl implements RealtimeService {

    @Autowired
    private RealtimeDao realtimeDao;


    @Override
    public RealtimeData getRealtimeData(int userId) {
        return realtimeDao.getRealtimeData(userId);
    }
}