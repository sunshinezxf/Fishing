package fishing.sunshine.service.impl;

import fishing.sunshine.dao.PondTypeDao;
import fishing.sunshine.model.PondType;
import fishing.sunshine.service.FishPondService;
import fishing.sunshine.util.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sunshine on 12/3/15.
 */
@Service("FishPondService")
public class FishPondServiceImpl implements FishPondService {
    @Autowired
    private PondTypeDao pondTypeDao;

    @Override
    public ResultData addFishPondType(PondType type) {
        ResultData result = new ResultData();
        pondTypeDao.insertPondType(type);
        return result;
    }

    public ResultData queryFishPondType(PondType type) {
        ResultData result = new ResultData();
        
        return result;
    }
}
