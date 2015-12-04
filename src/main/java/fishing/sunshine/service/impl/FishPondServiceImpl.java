package fishing.sunshine.service.impl;

import fishing.sunshine.dao.PondTypeDao;
import fishing.sunshine.model.PondType;
import fishing.sunshine.service.FishPondService;
import fishing.sunshine.util.ResponseCode;
import fishing.sunshine.util.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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

    @Override
    public ResultData queryFishPondType(PondType type) {
        ResultData result = new ResultData();
        ResultData query = pondTypeDao.queryPondType(type);
        result.setResponseCode(query.getResponseCode());
        if (query.getResponseCode() == ResponseCode.RESPONSE_OK) {
            result.setData(query.getData());
            if (((ArrayList<PondType>) result.getData()).size() == 0) {
                result.setResponseCode(ResponseCode.RESPONSE_NULL);
            }
        } else {
            result.setDescription(query.getDescription());
        }
        return result;
    }
}
