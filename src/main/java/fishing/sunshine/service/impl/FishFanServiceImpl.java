package fishing.sunshine.service.impl;

import fishing.sunshine.dao.FishFanDao;
import fishing.sunshine.model.FishFan;
import fishing.sunshine.service.FishFanService;
import fishing.sunshine.util.ResponseCode;
import fishing.sunshine.util.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sunshine on 12/20/15.
 */
@Service
public class FishFanServiceImpl implements FishFanService {
    private Logger logger = LoggerFactory.getLogger(FishFanServiceImpl.class);

    @Autowired
    private FishFanDao fishFanDao;

    @Override
    public ResultData addFishFan(FishFan fishFan) {
        ResultData result = new ResultData();
        ResultData insert = fishFanDao.insertFishFan(fishFan);
        result.setResponseCode(insert.getResponseCode());
        if (result.getResponseCode() == ResponseCode.RESPONSE_OK) {
            result.setData(fishFan);
        } else {
            result.setDescription(insert.getDescription());
        }
        return result;
    }

    @Override
    public ResultData queryFishFan(FishFan fishFan) {
        ResultData result = new ResultData();
        ResultData query = fishFanDao.queryFishFan(fishFan);
        result.setResponseCode(query.getResponseCode());
        if (query.getResponseCode() == ResponseCode.RESPONSE_OK) {
            result.setData(query.getData());
            if (((List<FishFan>) query.getData()).size() == 0) {
                result.setResponseCode(ResponseCode.RESPONSE_NULL);
            }
        } else {
            result.setDescription(query.getDescription());
        }
        return result;
    }

    @Override
    public ResultData updateFishFan(FishFan fishFan) {
        ResultData result = new ResultData();
        ResultData update = fishFanDao.updateFishFan(fishFan);
        result.setResponseCode(update.getResponseCode());
        if (result.getResponseCode() == ResponseCode.RESPONSE_OK) {
            result.setData(update.getData());
        } else {
            result.setDescription(update.getDescription());
        }
        return result;
    }
}
