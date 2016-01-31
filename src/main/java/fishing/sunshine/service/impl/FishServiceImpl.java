package fishing.sunshine.service.impl;

import fishing.sunshine.dao.FishDao;
import fishing.sunshine.model.Fish;
import fishing.sunshine.service.FishService;
import fishing.sunshine.pagination.DataTableParam;
import fishing.sunshine.util.IDGenerator;
import fishing.sunshine.util.ResponseCode;
import fishing.sunshine.util.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sunshine on 11/28/15.
 */
@Service
public class FishServiceImpl implements FishService {
    private Logger logger = LoggerFactory.getLogger(FishServiceImpl.class);

    @Autowired
    private FishDao fishDao;

    @Override
    public ResultData addFishType(Fish fish) {
        ResultData result = new ResultData();
        fish.setFishId(IDGenerator.generate("FIS"));
        ResultData insert = fishDao.insertFish(fish);
        result.setResponseCode(insert.getResponseCode());
        if (insert.getResponseCode() == ResponseCode.RESPONSE_OK) {
            result.setData(fish);
        } else {
            result.setDescription(insert.getDescription());
        }
        return result;
    }

    @Override
    public ResultData queryFishType(Fish fish) {
        ResultData result = new ResultData();
        ResultData query = fishDao.queryFish(fish);
        result.setResponseCode(query.getResponseCode());
        if (query.getResponseCode() == ResponseCode.RESPONSE_OK) {
            result.setData(query.getData());
            if (((List<Fish>) result.getData()).size() == 0) {
                result.setResponseCode(ResponseCode.RESPONSE_NULL);
            }
        } else {
            result.setDescription(query.getDescription());
        }
        return result;
    }

    @Override
    public ResultData queryFishTypeByPage(DataTableParam param) {
        ResultData result = new ResultData();
        ResultData query = fishDao.queryFishByPage(param);
        result.setResponseCode(query.getResponseCode());
        if (query.getResponseCode() == ResponseCode.RESPONSE_OK) {
            result.setData(query.getData());
        } else {
            result.setDescription(query.getDescription());
        }
        return result;
    }

    @Override
    public ResultData updateFishType(Fish previous, Fish updated) {
        ResultData result = new ResultData();
        updated.setFishId(previous.getFishId());
        ResultData update = fishDao.updateFish(updated);
        result.setResponseCode(update.getResponseCode());
        if (update.getResponseCode() == ResponseCode.RESPONSE_OK) {
            result.setData(update.getData());
        } else {
            result.setDescription(update.getDescription());
        }
        return result;
    }
}
